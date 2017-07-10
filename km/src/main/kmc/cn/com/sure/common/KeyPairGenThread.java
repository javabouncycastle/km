/**
 * 
 */
package cn.com.sure.common;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.com.sure.keypair.entry.KpgTask;
import cn.com.sure.keypair.service.KpgTaskExecuteService;
import cn.com.sure.keypair.service.KpgTaskService;
import cn.com.sure.syscode.entry.SysCode;

/**
 * @author Limin
 *
 */
public class KeyPairGenThread extends Thread{
	
	private static final Log LOG = LogFactory.getLog(KeyPairGenThread.class);
	
	private KpgTask kpgTask;
	
	private KpgTaskExecuteService kpgTaskExecuteService;
	
	private KpgTaskService kpgTaskService;
	
	//线程传值只能用构造方法传值，没法注入，注入进来是空！！
/*	public KeyPairGenThread(KpgQuartzTaskExecutorService kpgQuartzTaskExecutorService2, KpgTask kpgTask){
		this.kpgTask=kpgTask;
		this.kpgQuartzTaskExecutorService=kpgQuartzTaskExecutorService2;
	}*/
	
	public KeyPairGenThread(KpgTaskService kpgTaskService, KpgTask kpgTask){
		this.kpgTask=kpgTask;
	}
	
/*	public KpgQuartzTaskExecutorService getKpgQuartzTaskExecutorService() {
		return kpgQuartzTaskExecutorService;
	}

	public void setKpgQuartzTaskExecutorService(KpgQuartzTaskExecutorService kpgQuartzTaskExecutorService) {
		this.kpgQuartzTaskExecutorService = kpgQuartzTaskExecutorService;
	}
*/
	public void run(){
		
		do {
			LOG.info("执行TASK"+kpgTask.getId()+","+kpgTask.getName());
			LOG.info("run - start at " + new Date());
			

			try {
				kpgTaskExecuteService.executeTaskSlice(kpgTask.getId());
			} catch (Exception e) {
				LOG.error("执行TASK出现异常！");
				LOG.error(e);

				kpgTask = kpgTaskService.selectById(kpgTask.getId());
				kpgTask.setExeTaskEndTime(new Date());
				SysCode sysCode = new SysCode();
				sysCode.setParaValue(KmConstants.CODE_ID_TASK_STATUS_EXCEPTION.toString());
				kpgTask.setTaskStatus(sysCode);
				kpgTask.setTaskExeResult(e.getMessage());
				kpgTaskService.update(kpgTask);
				
				LOG.error("executeTask - exception - end at " + new Date());
				return;
			}
			
			kpgTask = kpgTaskService.selectById(kpgTask.getId());
			
			if(kpgTask.getGeneratedKeyAmount().intValue() == kpgTask.getKpgKeyAmount().intValue()){
				SysCode sysCode = new SysCode();
				sysCode.setParaValue(KmConstants.CODE_ID_TASK_STATUS_FINISHED.toString());
				kpgTask.setTaskStatus(sysCode);
				kpgTask.setExeTaskEndTime(new Date());
				kpgTask.setTaskExeResult("成功");
				kpgTaskService.update(kpgTask);
				return;
			}
			
			
		} while (kpgTask.getTaskStatus().getParaValue() == KmConstants.CODE_ID_TASK_STATUS_EXECUTING.toString());
		
		
		LOG.info("run - end at "+ new Date());
		/*try {
			kpgQuartzTaskExecutorService.executeTask(kpgTask);
		} catch (NoSuchAlgorithmException | KmApplicationexception e) {
			e.printStackTrace();
		}*/
	}

	public KpgTask getKpgTask() {
		return kpgTask;
	}

	public void setKpgTask(KpgTask kpgTask) {
		this.kpgTask = kpgTask;
	}
	
	
}
