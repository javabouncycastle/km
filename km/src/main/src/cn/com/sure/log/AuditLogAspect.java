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
	
	//获取当前时间，并且把时间转化为自定义格式
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date date=new Date();
	String date1 = dateFormat.format(date);
	
	
    /** 
     * 添加业务逻辑方法切入点 ,insert KeyPairAlg方法
     */  
	@Pointcut("execution(* cn.com.sure.keypair.service.*.insert(..))")  
	 public void insert() {
		
	} 
	/** 
     * 添加业务逻辑方法切入点 ,delete方法
     */  
	@Pointcut("execution(* cn.com.sure.keypair.service.*.delete*(..))")
	public void delete(){
		
	}
	/** 
     * 添加业务逻辑方法切入点 ,update方法
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
		auditOpLog.setAction("新增密钥算法");
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
		auditOpLog.setAction("删除密钥算法");
		auditOpLog.setActionExt1(id.toString());//主键
		auditOpLog.setActionExt2("");//别名
		auditOpLog.setActionExt3("");
		auditOpLog.setActionExt4("");
		auditOpLog.setMessage("");
		auditOpLog.setTimestamp(nowDate);//操作时间
		auditOpLog.setId(InetAddress.getLocalHost().getHostAddress());//获取客户端的ip
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
		auditOpLog.setAction("更新");
		auditOpLog.setActionExt1(keypairAlgorithm.getId().toString());//主键
		auditOpLog.setActionExt2(keypairAlgorithm.getAlgorithmName());//别名
		auditOpLog.setActionExt3(attr);//保存更新了那些字段
		auditOpLog.setActionExt4("");
		auditOpLog.setMessage("");
		auditOpLog.setTimestamp(nowDate);//操作时间
		auditOpLog.setId(InetAddress.getLocalHost().getHostAddress());//获取客户端的ip
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
	 * 比较一下密钥算法更新更新了那些字段
	 */
	public String compairKPAlg(KeypairAlgorithm keypairAlgorithmNew){
		LOG.debug("compairKPAlg - start");
		String resultString="";
		if(keypairAlgorithmNew!=null&&!"".equals(keypairAlgorithmNew)){
			KeypairAlgorithm keypairAlgorithmDB=this.find(keypairAlgorithmNew.getId());
			if(StringUtils.isNotBlank(keypairAlgorithmDB.getAlgorithmName())&&StringUtils.isNotBlank(keypairAlgorithmNew.getAlgorithmName())){
				if(!keypairAlgorithmDB.getAlgorithmName().equals(keypairAlgorithmNew.getAlgorithmName())){
					resultString+="算法英文缩写由"+keypairAlgorithmDB.getAlgorithmName()+"变更为"+keypairAlgorithmNew.getAlgorithmName()+";";
				}
			if(keypairAlgorithmDB.getAlgorithmOid()!=null&&keypairAlgorithmNew.getAlgorithmOid()!=null){
				if(!keypairAlgorithmDB.getAlgorithmOid().equals(keypairAlgorithmNew.getAlgorithmOid())){
					resultString+="算法OID由"+keypairAlgorithmDB.getAlgorithmOid()+"变更为"+keypairAlgorithmNew.getAlgorithmOid()+";";
				}
			}
			if(keypairAlgorithmDB.getKeysize()!=null&&keypairAlgorithmNew!=null){
				if(!keypairAlgorithmDB.equals(keypairAlgorithmNew)){
					resultString+="密钥长度由"+keypairAlgorithmDB.getKeysize()+"变更为"+keypairAlgorithmNew.getKeysize()+";";
				}
			}
			if(keypairAlgorithmDB.getName()!=null&&keypairAlgorithmNew.getName()!=null){
				if(!keypairAlgorithmDB.equals(keypairAlgorithmNew)){
					resultString+="别名由"+keypairAlgorithmDB.getName()+"变更为"+keypairAlgorithmNew.getName()+";";
				}
			}
			}
		}
		LOG.debug("compairKPAlg - end");
		return resultString;
		
	}
	/**
	 * 根据id查询
	 */
	private KeypairAlgorithm find(Long id){
		LOG.debug("find - start");
		KeypairAlgorithm keypairAlgorithm=keypairAlgorithmDAO.findById(id);
		LOG.debug("find - end");
		return keypairAlgorithm;
		}
	
	
}
