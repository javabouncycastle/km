package cn.com.sure.log;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;


@Service(value="auditLogAspect")
@Aspect
public class AuditLogAspect {
	
	/*private static final Log LOG = LogFactory.getLog(AuditLogAspect.class);

	@Autowired
	private AuditOpLogService auditOpLogService;
	
	@Autowired
	private KeyPairAlgorithmDAO keypairAlgorithmDAO;
	
	//获取当前时间，并且把时间转化为自定义格式
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date date=new Date();
	String date1 = dateFormat.format(date);
	
	
    *//** 
     * 添加业务逻辑方法切入点 ,insert KeyPairAlg方法
     *//*  
	@Pointcut("execution(* cn.com.sure.keypair.service.*.insert(..))")  
	 public void insert() {
		
	} 
	*//** 
     * 添加业务逻辑方法切入点 ,delete方法
     *//*  
	@Pointcut("execution(* cn.com.sure.keypair.service.*.delete*(..))")
	public void delete(){
		
	}
	*//** 
     * 添加业务逻辑方法切入点 ,update方法
     *//*  
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
	
	*//**
	 * 比较一下密钥算法更新更新了那些字段
	 *//*
	  /**
  	 * 比较一下密钥算法更新更新了那些字段
  	 */
/*  	public String compairKPAlg(KeyPairAlgorithm keyPairAlgorithmNew){
  		LOG.debug("compairKPAlg - start");
  		String resultString="";
  		if(keyPairAlgorithmNew!=null&&!"".equals(keyPairAlgorithmNew)){
  			KeyPairAlgorithm keyPairAlgorithmDB=this.find(keyPairAlgorithmNew.getId());
  			if(StringUtils.isNotBlank(keyPairAlgorithmDB.getAlgorithmName())&&StringUtils.isNotBlank(keyPairAlgorithmNew.getAlgorithmName())){
  				if(!keyPairAlgorithmDB.getAlgorithmName().equals(keyPairAlgorithmNew.getAlgorithmName())){
  					resultString+="算法英文缩写由"+keyPairAlgorithmDB.getAlgorithmName()+"变更为"+keyPairAlgorithmNew.getAlgorithmName()+";";
  				}
  			if(keyPairAlgorithmDB.getAlgorithmOid()!=null&&keyPairAlgorithmNew.getAlgorithmOid()!=null){
  				if(!keyPairAlgorithmDB.getAlgorithmOid().equals(keyPairAlgorithmNew.getAlgorithmOid())){
  					resultString+="算法OID由"+keyPairAlgorithmDB.getAlgorithmOid()+"变更为"+keyPairAlgorithmNew.getAlgorithmOid()+";";
  				}
  			}
  			if(keyPairAlgorithmDB.getKeysize()!=null&&keyPairAlgorithmNew!=null){
  				if(!keyPairAlgorithmDB.equals(keyPairAlgorithmNew)){
  					resultString+="密钥长度由"+keyPairAlgorithmDB.getKeysize()+"变更为"+keyPairAlgorithmNew.getKeysize()+";";
  				}
  			}
  			if(keyPairAlgorithmDB.getName()!=null&&keyPairAlgorithmNew.getName()!=null){
  				if(!keyPairAlgorithmDB.equals(keyPairAlgorithmNew)){
  					resultString+="别名由"+keyPairAlgorithmDB.getName()+"变更为"+keyPairAlgorithmNew.getName()+";";
  				}
  			}
  			}
  		}
  		LOG.debug("compairKPAlg - end");
  		return resultString;
  		
  	}
}*/
	/**
	 * 根据id查询
	 */
	/*private KeypairAlgorithm find(Long id){
		LOG.debug("find - start");
		KeypairAlgorithm keypairAlgorithm=keypairAlgorithmDAO.findById(id);
		LOG.debug("find - end");
		return keypairAlgorithm;
		}
	*/
	
}
