/**
 * 
 */
package cn.com.sure.keypair.service;

import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.keypair.entry.KpgTask;
import cn.com.sure.km.KmApplicationexception;

	/**
	 * @author Limin
	 *
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Service("kpgQuartzTaskExecutorService")
	public class KpgQuartzTaskExecutorServiceImpl implements KpgQuartzTaskExecutorService{
		
		private static final Log LOG = LogFactory.getLog(KpgQuartzTaskExecutorServiceImpl.class);
		
		@Autowired
		private KpgTaskService kpgTaskService;
		
		/* (non-Javadoc)
		 */
		@Override
		public void executeTask(KpgTask kpgTask) throws NoSuchAlgorithmException, KmApplicationexception {
			
			/*LOG.info("Quartz:executeTask NotStartedTask - start at " + new Date());
			
			//1.启动新任务
			List<KpgTask> list = kpgTaskService.findAllUnExecutedTask(kpgTask);
			
			for(KpgTask task:list){
				
				 //设置密钥对执行开始时间
				task.setExeTaskStartTime(new Date());
				
				//更新任务状态
				SysCode sysCode = new SysCode();
				sysCode.setParaValue(String.valueOf(KmConstants.CODE_ID_TASK_STATUS_EXECUTING));
				task.setTaskStatus(sysCode);
				
				kpgTaskService.update(task);
				
				new Thread(new KeyPairGenThread(kpgTaskService,task)).start();
				
				LOG.info("Quartz:executeTask NotStartedTask - end at "+ new Date());
				
			}
			
			
			//2.继续暂停任务
			LOG.info("Quartz:executeTask ResumeTask - start at " + new Date());
	
			list=kpgTaskService.findByTaskStatus(KmConstants.CODE_ID_TASK_STATUS_WAITING_FOR_EXECUTING);
			
			for(KpgTask task:list){
				
				SysCode sysCode = new SysCode();
				
				sysCode.setParaValue(KmConstants.CODE_ID_TASK_STATUS_EXECUTING.toString());
				
				task.setTaskStatus(sysCode);
	
				kpgTaskService.update(task);
				
				LOG.info("将要继续执行任务TASK"+task.getId()+","+task.getName());
				
				new Thread(new KeyPairGenThread(kpgTaskService,task)).start();
				
			}*/
			
		}
	
	}
