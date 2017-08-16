/**
 * 
 */
package cn.com.sure.socket;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sun.misc.BASE64Decoder;
import cn.com.sure.algorthm.entry.KeyPairAlgorithm;
import cn.com.sure.common.KmConstants;
import cn.com.sure.km.KmApplicationexception;
import cn.com.sure.km.KmErrorMessageConstants;
import cn.com.sure.kpg.entry.KeypairArchive;
import cn.com.sure.kpg.entry.KeypairInuse;
import cn.com.sure.kpg.entry.KeypairStandby;
import cn.com.sure.kpg.service.KeypairArchiveService;
import cn.com.sure.kpg.service.KeypairInuseService;
import cn.com.sure.kpg.service.KeypairStandbyService;

/**
 * @author Limin
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("socketService")
public class SocketServiceImpl implements SocketService{
	
	private static final Log LOG = LogFactory.getLog(SocketServiceImpl.class);
	
	@Autowired
	private KeypairStandbyService keypairStandbyService;
	
	@Autowired
	private KeypairInuseService inuseService;
	
	@Autowired
	private KeypairArchiveService archiveService;
	
	@Autowired
	private DigitalEnveService digitalEnveService;
	

	@Override
	public byte[] handleSocket(byte[] requestInfoByte) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, ParseException, KmApplicationexception, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, CertificateException {
		
		LOG.debug("handleSocket - start");
		
		//0、验证签名
		
		//1.对requestInfo信息进行处理
		//1.1去掉requestInfo中的 '
		String requestInfo=new String(requestInfoByte);
		
		String certDN = null;
		String certSn = null;
		String certStartTime = null;
		String certEndTime = null;
		String algSize = null;
		String certOperType = null;
		
		requestInfo=requestInfo.replaceAll(" ","");
		
		if(requestInfo.contains("\'")){
			requestInfo=requestInfo.replace("\'", "");
		}
		String[] reqInfo = requestInfo.split(";");
		if(reqInfo.length!=6){
			if(reqInfo.length!=2){
				return "信息格式错误".getBytes();
			}
			
		}
		if(reqInfo.length==6){
			//1.2获取证书dn项，证书sn，证书开始时间，证书结束时间，证书
			certDN = reqInfo[0];
			certSn = reqInfo[1];
			certStartTime = reqInfo[2];
			certEndTime = reqInfo[3];
			algSize=reqInfo[4];
			certOperType=reqInfo[5];
		}if(reqInfo.length==2){
			certSn = reqInfo[0];
			certOperType = reqInfo[1];
		}
		
		Integer operType= Integer.parseInt(certOperType);
		
		KeyPairAlgorithm kpAlg = new KeyPairAlgorithm();
		KeypairInuse kpInuse = new KeypairInuse();
		KeypairArchive kpArchive = new KeypairArchive();
		
		switch (operType) {
		
		//2.新办或者更新
		case KmConstants.TYPE_ID_CERT_NEW_OR_UPDATE_STATUS:
			//2获取密钥对
			kpAlg.setAlgorithmName(algSize);
			KeypairStandby kpStandby = keypairStandbyService.obtKpStandby(kpAlg);
	        
			//3.1将获取到的密钥插入到密钥在用库中
	        DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
	        KeypairInuse inuse = new KeypairInuse();
	        inuse.setCertDn(certDN);
	        inuse.setCertSn(certSn);
	        inuse.setEndTime(dfm.parse(certEndTime));
	        inuse.setStartTime(dfm.parse(certStartTime));
	        inuse.setInuseTime(new Date());
	        inuse.setKeyPairAlgorithm(kpAlg);
	        inuse.setKpgTask(kpStandby.getKpgTask());
	        inuse.setPriKey(kpStandby.getPriKey());
	        inuse.setPubKey(kpStandby.getPubKey());
	        inuse.setGenTime(kpStandby.getGenTime());
	        
	        inuseService.insert(inuse);
	        
	        //3.2删除备用库中的那条记录
	        keypairStandbyService.delete(kpStandby.getId());
	        
	        KeyPair keyPair = new KeyPair(obtPubkey(kpStandby.getPubKey()), obtPrikey(kpStandby.getPriKey()));
	        
	        //3.3对keypair进行封装
	        byte[] resultByte = digitalEnveService.digEve(keyPair);
	       
	        //4返回密钥对信息
			return resultByte;

		//注销
		case KmConstants.TYPE_ID_CERT_REVOKE_STATUS:
			
			//5注销，将密钥归档
			
			//5.1根据sn查询密钥
			kpInuse.setCertSn(certSn);
			kpInuse = inuseService.findBySn(kpInuse);
			
			
			//5.2把查询出来的在用密钥移动到历史密钥库中
			kpArchive.setPubKey(kpInuse.getPubKey());
			kpArchive.setPriKey(kpInuse.getPriKey());
			kpArchive.setKeyPairAlgorithm(kpInuse.getKeyPairAlgorithm());
			kpArchive.setKpgTask(kpInuse.getKpgTask());
			kpArchive.setGenTime(kpInuse.getGenTime());
			kpArchive.setStartTime(kpInuse.getStartTime());
			kpArchive.setEndTime(kpInuse.getEndTime());
			kpArchive.setCertSn(kpInuse.getCertSn());
			kpArchive.setCertDn(kpInuse.getCertDn());
			kpArchive.setArchTime(new Date());
			
			archiveService.insert(kpArchive);
			
			//5.3删除在用库中的信息
			inuseService.delete(kpInuse.getId());
			
			//KmApplicationexception.throwException(KmErrorMessageConstants.revokeKpSuce, new String[]{kpArchive.getCertSn()});
			return "注销密钥成功".getBytes();
			
		//密钥恢复
		case KmConstants.TYPE_ID_CERT_RECOVER_STATUS:
			
			//6根据sn查询密钥
			kpArchive = archiveService.findBySn(certSn);
			byte[] resultRevoByte;
			if(kpArchive==null||"".equals(kpArchive)){
				//6.1找不到这条记录
				KmApplicationexception.throwException(KmErrorMessageConstants.snNotFound, new String[]{kpArchive.getCertSn()});
				return "找不到这条记录".getBytes();
			}else{
				//6.2找到这个keypair,封装并返回
				KeyPair keypair = new KeyPair(obtPubkey(kpArchive.getPubKey()), obtPrikey(kpArchive.getPriKey()));
				resultRevoByte = digitalEnveService.digEve(keypair);
				
			}
			
			return resultRevoByte;

		default:
			break;
		}
		
	
		LOG.debug("handleSocket - end");
		return null;
	}
	
	boolean verifySignature(String requestInfo){
		LOG.debug("verifySignature - start");
		
		LOG.debug("verifySignature - end");
		return false;
	}
	

    public PrivateKey obtPrikey(String prikey) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException{
    	LOG.debug("obtPubkey - start");
    	PKCS8EncodedKeySpec priKeySpec = new PKCS8EncodedKeySpec(new BASE64Decoder().decodeBuffer(prikey));
        KeyFactory priKeyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = priKeyFactory.generatePrivate(priKeySpec);
    	LOG.debug("obtPubkey - end");
		return privateKey;
    	
    }
    
	private PublicKey obtPubkey(String pubKey) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		LOG.debug("obtPubkey - start");
		 X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(new BASE64Decoder().decodeBuffer(pubKey));
	     KeyFactory pubKeyFactory = KeyFactory.getInstance("RSA");
	     PublicKey publicKey = pubKeyFactory.generatePublic(pubKeySpec);
		LOG.debug("obtPubkey - end");
		return publicKey;
		
	}
    
}
