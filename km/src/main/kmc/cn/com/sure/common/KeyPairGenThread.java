/**
 * 
 */
package cn.com.sure.common;

import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.com.sure.keypair.entry.KpgTask;
import cn.com.sure.keypair.service.KpgQuartzTaskExecutorService;
import cn.com.sure.keypair.service.KpgTaskService;
import cn.com.sure.km.KmApplicationexception;

/**
 * @author Limin
 *
 */
public class KeyPairGenThread extends Thread{
	
	private static final Log LOG = LogFactory.getLog(KeyPairGenThread.class);
	
	private KpgQuartzTaskExecutorService kpgQuartzTaskExecutorService;
	
	private KpgTask kpgTask;
	
	//线程传值只能用构造方法传值，没法注入，注入进来是空！！
	public KeyPairGenThread(KpgQuartzTaskExecutorService kpgQuartzTaskExecutorService2, KpgTask kpgTask){
		this.kpgTask=kpgTask;
		this.kpgQuartzTaskExecutorService=kpgQuartzTaskExecutorService2;
	}
	
	public KeyPairGenThread(KpgTaskService kpgTaskService, KpgTask kpgTask){
		this.kpgTask=kpgTask;
		//this.kpgQuartzTaskExecutorService=kpgQuartzTaskExecutorService2;
	}
	
	public KpgQuartzTaskExecutorService getKpgQuartzTaskExecutorService() {
		return kpgQuartzTaskExecutorService;
	}

	public void setKpgQuartzTaskExecutorService(KpgQuartzTaskExecutorService kpgQuartzTaskExecutorService) {
		this.kpgQuartzTaskExecutorService = kpgQuartzTaskExecutorService;
	}

	public void run(){
		try {
			kpgQuartzTaskExecutorService.executeTask(kpgTask);
		} catch (NoSuchAlgorithmException | KmApplicationexception e) {
			e.printStackTrace();
		}
	}

	public KpgTask getKpgTask() {
		return kpgTask;
	}

	public void setKpgTask(KpgTask kpgTask) {
		this.kpgTask = kpgTask;
	}
	
	
}
