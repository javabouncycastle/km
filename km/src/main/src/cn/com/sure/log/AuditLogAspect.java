package cn.com.sure.log;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.sure.common.KmConstants;
import cn.com.sure.log.entry.AuditLog;
import cn.com.sure.log.service.AuditLogService;


@Service(value="AuditLogAspect")
@Aspect
public class AuditLogAspect {
	
	private static final Log LOG = LogFactory.getLog(AuditLogAspect.class);

	@Autowired
	private AuditLogService aditLogService;
	
    /** 
     * ���ҵ���߼���������� ,insert KeyPairAlg����
     */  
	@Pointcut("execution(* cn.com.sure.keypair.alg.service.*.insert(..))")  
	 public void insert() {
		
	} 

	/** 
     * ���ҵ���߼���������� ,delete����
     */  
	@Pointcut("execution(* cn.com.sure.keypair.alg.service.KeypairAlgorithmServiceImpl.delete*(..))")
	public void delete(){
		
	}
	
	@Before(value="insert()")
	public void insertLogsBefore(JoinPoint joinPoint) throws Throwable{
		}  
	
	@AfterReturning(value="insert()", argNames="rtv", returning="rtv")  
	public void insertLogsAfter(JoinPoint joinPoint, Object rtv) throws Throwable{
			
		AuditLog auditLog = new AuditLog();
		} 
	   
/*	@AfterThrowing(value="delete()", argNames="rtv",throwing="rtv")
	public void insertLogsAfterThrowing(JoinPoint joinPoint, Exception rtv) throws Throwable{
		LOG.debug("insertLogsAfterThrowing - start");
		
		LOG.debug("insertLogsAfterThrowing - end");
	    } 
	    */
	
	/*@Around(value="insert()")
	public Object insertAroundLogs(ProceedingJoinPoint pjp)throws Throwable{
		LOG.debug("insertAroundLogs - start");
		
		LOG.debug("insertAroundLogs - end");
		return pjp;
	}*/
	
	@Around(value="delete()")
	public Object deleteAroundsLogs(ProceedingJoinPoint pjp)throws Throwable{
		LOG.debug("deleteAroundsLogs - start");
		//��ȡ��ǰʱ�䣬���Է�����޸����ڸ�ʽ
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		//Date nowDate = dateFormat.format(date);
		AuditLog auditLog = new AuditLog();
		auditLog.setType(KmConstants.OPERATION_TYPE_DELETE);
		auditLog.setAction("ɾ��");
		auditLog.setActionExt1("");//����
		auditLog.setActionExt2("");
		auditLog.setActionExt3("");
		auditLog.setActionExt4("");
		auditLog.setMessage("");
		auditLog.setTimestamp(date);
		auditLog.setId(InetAddress.getLocalHost().getHostAddress());//��ȡ�ͻ��˵�ip
		auditLog.setOperator("");
		Object obj=pjp.proceed();
		int ifSuccess=1;
		if(obj!=null&&!"".equals(obj)){
			 ifSuccess=Integer.parseInt(obj.toString());
		}
		if(ifSuccess==1){
			auditLog.setIsOpSucc(1);
		}else{
			auditLog.setIsOpSucc(0);
		}
		
		aditLogService.insert(auditLog);
		LOG.debug("deleteAroundsLogs - end");
		return pjp;
		
	}
	
	
}
