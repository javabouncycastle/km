/**
 * 
 */
package cn.com.sure.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sun.misc.BASE64Decoder;

import cn.com.sure.admin.service.AdminService;
import cn.com.sure.common.KmConstants;

/**
 * @author Limin
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("digitalEnveService")
public class DigitalEnveServiceImpl implements DigitalEnveService{
	
	@Autowired 
	AdminService adminService;
	
	private static final Log LOG = LogFactory.getLog(DigitalEnveServiceImpl.class);
	
	
	@Override
	public byte[] digEve(KeyPair keypair) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException, IOException {
		LOG.debug("digEve - start");
		
		//生成随机数，作为md5的密钥
		String desKey=String.valueOf(Math.round(Math.random()*100000000));
		
		//用md5对keypair进行加密
		byte[] encryptedKeyPair = desEncrypt(keypair,desKey.getBytes());
		
		//用管理员证书对md5的随机数进行加密
		 String cert = adminService.obtAdmCert(KmConstants.ADMIN_AUTH_NUM);
		byte[] encryptedDesKey = rsaEncrypt(desKey.getBytes(),obtPubkey(cert));
		
		byte[] resultByte = addBytes(encryptedKeyPair, encryptedDesKey);
		
		LOG.debug("digEve - end");
		return resultByte;
	}

	
	//md5加密原文
	public byte[] desEncrypt(KeyPair keypair, byte[] keyBytes) {
		LOG.debug("DesEncrypt - start");
		
		 try {  
			 
			 byte[] kpByt = ObjectToByte(keypair);
			 
            DESKeySpec keySpec=new DESKeySpec(keyBytes);  
            SecretKeyFactory keyFactory=SecretKeyFactory.getInstance("DES");              
            SecretKey key=keyFactory.generateSecret(keySpec);         
              
            Cipher cipher=Cipher.getInstance("DES/CBC/PKCS5Padding");  
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(keySpec.getKey()));             
            byte[] result=cipher.doFinal(kpByt);  
            return result;  
	        } catch (Exception e) {  
	            System.out.println("exception:"+e.toString());  
	        }  
		 
		LOG.debug("DesEncrypt - end");
		return null;
	}

	
	
	public byte[] rsaEncrypt(byte[] content, PublicKey publicKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		LOG.debug("encrypt - start");
		
		Cipher cipher=Cipher.getInstance("RSA");//java默认"RSA"="RSA/ECB/PKCS1Padding"  
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
         
		LOG.debug("encrypt - end");
		return cipher.doFinal(content); 
	}
	
	public static byte[] ObjectToByte(KeyPair keypair) {  
        byte[] bytes = null;  
        try {  
            // object to bytearray  
            ByteArrayOutputStream bo = new ByteArrayOutputStream();  
            ObjectOutputStream oo = new ObjectOutputStream(bo);  
            oo.writeObject(keypair);  
      
            bytes = bo.toByteArray();  
      
            bo.close();  
            oo.close();  
        } catch (Exception e) {  
            System.out.println("translation" + e.getMessage());  
            e.printStackTrace();  
        }  
        return bytes;  
    }

	//string格式的公钥转为publickey格式的
	private PublicKey obtPubkey(String pubKey) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		LOG.debug("obtPubkey - start");
		 X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(new BASE64Decoder().decodeBuffer(pubKey));
	     KeyFactory pubKeyFactory = KeyFactory.getInstance("RSA");
	     PublicKey publicKey = pubKeyFactory.generatePublic(pubKeySpec);
		LOG.debug("obtPubkey - end");
		return publicKey;
		
	}
	
	
	public static byte[] addBytes(byte[] encryptedKeyPair, byte[] encrypted) {  
	    byte[] resultByte = new byte[encryptedKeyPair.length + encrypted.length];  
	    System.arraycopy(encryptedKeyPair, 0, resultByte, 0, encryptedKeyPair.length);  
	    System.arraycopy(encrypted, 0, resultByte, encryptedKeyPair.length, encrypted.length);  
	    return resultByte;  
	  
	}  


}
