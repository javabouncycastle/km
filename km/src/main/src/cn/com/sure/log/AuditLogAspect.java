package cn.com.sure.log;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.sure.common.KmConstants;
import cn.com.sure.keypair.dao.KeypairAlgorithmDAO;
import cn.com.sure.keypair.entry.KeypairAlgorithm;
import cn.com.sure.log.entry.AuditOpLog;
import cn.com.sure.log.service.AuditOpLogService;


@Service(value="auditLogAspect")
@Aspect
public class AuditLogAspect {
	
	private static final Log LOG = LogFactory.getLog(AuditLogAspect.class);

	@Autowired
	private AuditOpLogService auditOpLogService;
	
	@Autowired
	private KeypairAlgorithmDAO keypairAlgorithmDAO;
	
	//��ȡ��ǰʱ�䣬���Ұ�ʱ��ת��Ϊ�Զ����ʽ
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date date=new Date();
	String date1 = dateFormat.format(date);
	
	
    /** 
     * ���ҵ���߼���������� ,insert KeyPairAlg����
     */  
	@Pointcut("execution(* cn.com.sure.keypair.service.*.insert(..))")  
	 public void insert() {
		
	} 
	/** 
     * ���ҵ���߼���������� ,delete����
     */  
	@Pointcut("execution(* cn.com.sure.keypair.service.*.delete*(..))")
	public void delete(){
		
	}
	/** 
     * ���ҵ���߼���������� ,update����
     */  
	@Pointcut("execution(* cn.com.sure.keypair.service.*.update*(..))")
	public void update(){
		
	}
	
	@Around(value="insert()")
	public Object insertKeyPairAlgAroundsLogs(ProceedingJoinPoint pjp)throws Throwable{
		LOG.debug("insertKeyPairAlgAroundsLogs - start");
		KeypairAlgorithm keypairAlgorithm=(KeypairAlgorithm)pjp.getArgs()[0];
		AuditOpLog auditOpLog = new AuditOpLog();
		auditOpLog.setType(KmConstants.OPERATION_TYPE_INSERT);
		auditOpLog.setAction("������Կ�㷨");
		auditOpLog.setActionExt1(String.valueOf(keypairAlgorithm.getId()));
		auditOpLog.setActionExt2(keypairAlgorithm.getName());
		auditOpLog.setActionExt3("");
		auditOpLog.setActionExt4("");
		auditOpLog.setMessage("");
		Date nowDate=dateFormat.parse(date1);
		auditOpLog.setTimestamp(nowDate);
		auditOpLog.setId(InetAddress.getLocalHost().getHostAddress());
		auditOpLog.setOperator("");
		Object obj=pjp.proceed();
		int ifSuccess = 0;
		if(obj!=null&&!"".equals(obj)){
			 ifSuccess=Integer.parseInt(obj.toString());
		}
		if(ifSuccess==1){
			auditOpLog.setIsOpSucc(KmConstants.SUCCESS_OR_FAILD_OPTION_SUCCESS);
		}else{
			auditOpLog.setIsOpSucc(KmConstants.SUCCESS_OR_FAILD_OPTION_FAILD);
		}
		auditOpLogService.insert(auditOpLog);
		LOG.debug("insertKeyPairAlgAroundsLogs - end");
		return pjp;
	}
	
	@Around(value="delete()")
	public Object deleteKeyPairAlgAroundsLogs(ProceedingJoinPoint pjp)throws Throwable{
		LOG.debug("deleteKeyPairAlgAroundsLogs - start");
		Long id = (Long)pjp.getArgs()[0];
		Date nowDate = dateFormat.parse(date1);
		AuditOpLog auditOpLog = new AuditOpLog();
		auditOpLog.setType(KmConstants.OPERATION_TYPE_DELETE);
		auditOpLog.setAction("ɾ����Կ�㷨");
		auditOpLog.setActionExt1(id.toString());//����
		auditOpLog.setActionExt2("");//����
		auditOpLog.setActionExt3("");
		auditOpLog.setActionExt4("");
		auditOpLog.setMessage("");
		auditOpLog.setTimestamp(nowDate);//����ʱ��
		auditOpLog.setId(InetAddress.getLocalHost().getHostAddress());//��ȡ�ͻ��˵�ip
		auditOpLog.setOperator("");
		Object obj=pjp.proceed();
		int ifSuccess=1;
		if(obj!=null&&!"".equals(obj)){
			 ifSuccess=Integer.parseInt(obj.toString());
		}
		if(ifSuccess==1){
			auditOpLog.setIsOpSucc(KmConstants.SUCCESS_OR_FAILD_OPTION_SUCCESS);
		}else{
			auditOpLog.setIsOpSucc(KmConstants.SUCCESS_OR_FAILD_OPTION_FAILD);
		}
		auditOpLogService.insert(auditOpLog);
		LOG.debug("deleteKeyPairAlgAroundsLogs - end");
		return pjp;
	}
	
	@Around(value="update()")
	public Object updateKeyPairAlgAroundsLogs(ProceedingJoinPoint pjp)throws Throwable{
		LOG.debug("updateKeyPairAlgAroundsLogs - start");
		KeypairAlgorithm keypairAlgorithm = (KeypairAlgorithm)pjp.getArgs()[0];
		String attr=compairKPAlg(keypairAlgorithm);
		Date nowDate = dateFormat.parse(date1);
		AuditOpLog auditOpLog = new AuditOpLog();
		auditOpLog.setType(KmConstants.OPERATION_TYPE_UPDATE);
		auditOpLog.setAction("����");
		auditOpLog.setActionExt1(keypairAlgorithm.getId().toString());//����
		auditOpLog.setActionExt2(keypairAlgorithm.getAlgorithmName());//����
		auditOpLog.setActionExt3(attr);//�����������Щ�ֶ�
		auditOpLog.setActionExt4("");
		auditOpLog.setMessage("");
		auditOpLog.setTimestamp(nowDate);//����ʱ��
		auditOpLog.setId(InetAddress.getLocalHost().getHostAddress());//��ȡ�ͻ��˵�ip
		auditOpLog.setOperator("");
		Object obj=pjp.proceed();
		int ifSuccess=1;
		if(obj!=null&&!"".equals(obj)){
			 ifSuccess=Integer.parseInt(obj.toString());
		}
		if(ifSuccess==1){
			auditOpLog.setIsOpSucc(KmConstants.SUCCESS_OR_FAILD_OPTION_SUCCESS);
		}else{
			auditOpLog.setIsOpSucc(KmConstants.SUCCESS_OR_FAILD_OPTION_FAILD);
		}
		auditOpLogService.insert(auditOpLog);
		LOG.debug("updateKeyPairAlgAroundsLogs - end");
		return pjp;
	}
	
	/**
	 * �Ƚ�һ����Կ�㷨���¸�������Щ�ֶ�
	 */
	public String compairKPAlg(KeypairAlgorithm keypairAlgorithmNew){
		LOG.debug("compairKPAlg - start");
		String resultString="";
		if(keypairAlgorithmNew!=null&&!"".equals(keypairAlgorithmNew)){
			KeypairAlgorithm keypairAlgorithmDB=this.find(keypairAlgorithmNew.getId());
			if(StringUtils.isNotBlank(keypairAlgorithmDB.getAlgorithmName())&&StringUtils.isNotBlank(keypairAlgorithmNew.getAlgorithmName())){
				if(!keypairAlgorithmDB.getAlgorithmName().equals(keypairAlgorithmNew.getAlgorithmName())){
					resultString+="�㷨Ӣ����д��"+keypairAlgorithmDB.getAlgorithmName()+"���Ϊ"+keypairAlgorithmNew.getAlgorithmName()+";";
				}
			if(keypairAlgorithmDB.getAlgorithmOid()!=null&&keypairAlgorithmNew.getAlgorithmOid()!=null){
				if(!keypairAlgorithmDB.getAlgorithmOid().equals(keypairAlgorithmNew.getAlgorithmOid())){
					resultString+="�㷨OID��"+keypairAlgorithmDB.getAlgorithmOid()+"���Ϊ"+keypairAlgorithmNew.getAlgorithmOid()+";";
				}
			}
			if(keypairAlgorithmDB.getKeysize()!=null&&keypairAlgorithmNew!=null){
				if(!keypairAlgorithmDB.equals(keypairAlgorithmNew)){
					resultString+="��Կ������"+keypairAlgorithmDB.getKeysize()+"���Ϊ"+keypairAlgorithmNew.getKeysize()+";";
				}
			}
			if(keypairAlgorithmDB.getName()!=null&&keypairAlgorithmNew.getName()!=null){
				if(!keypairAlgorithmDB.equals(keypairAlgorithmNew)){
					resultString+="������"+keypairAlgorithmDB.getName()+"���Ϊ"+keypairAlgorithmNew.getName()+";";
				}
			}
			}
		}
		LOG.debug("compairKPAlg - end");
		return resultString;
		
	}
	/**
	 * ����id��ѯ
	 */
	private KeypairAlgorithm find(Long id){
		LOG.debug("find - start");
		KeypairAlgorithm keypairAlgorithm=keypairAlgorithmDAO.findById(id);
		LOG.debug("find - end");
		return keypairAlgorithm;
		}
	
	
}
