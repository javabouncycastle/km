/**
 * 
 */
package cn.com.sure.keypair.service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sun.misc.BASE64Encoder;
import cn.com.sure.common.KmConstants;
import cn.com.sure.keypair.entry.KeyPairStandby1024;
import cn.com.sure.keypair.entry.KpgTask;
import cn.com.sure.km.KmApplicationexception;

	/**
	 * @author Limin
	 *
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Service("kpgTaskExecuteService")
	public class KpgTaskExecuteServiceImpl implements KpgTaskExecuteService{
		
		private static final Log LOG = LogFactory.getLog(KpgTaskExecuteServiceImpl.class);
		
		@Autowired
		private KeyPairStandby1024Service keyPairStandby1024Service;
		
		@Autowired
		private KpgTaskService kpgTaskService;
		
		/* (non-Javadoc)
		 * @see cn.com.sure.keypair.service.KpgTaskExecuteService#executeTaskSlice()
		 */
		public void executeTaskSlice(Long taskId) throws NoSuchAlgorithmException, NoSuchProviderException, KmApplicationexception, ClassNotFoundException, InstantiationException, IllegalAccessException {
			
			LOG.debug("executeTaskSlice - start");

			LOG.info("开始执行任务块-"+taskId);

			//1.查询任务
			KpgTask kpgTask = kpgTaskService.selectById(taskId);
			
			
			//2.判断状态
			if(kpgTask.getTaskStatus().getParaValue()==KmConstants.CODE_ID_TASK_STATUS_NOT_STARTED.toString()||
					kpgTask.getTaskStatus().getParaValue()==KmConstants.CODE_ID_TASK_STATUS_MANUAL_PAUSED.toString()||
					kpgTask.getTaskStatus().getParaValue()==KmConstants.CODE_ID_TASK_STATUS_EXCEPTION.toString()){
				LOG.info("无法继续执行任务块-["+taskId+"]任务状态："+kpgTask.getTaskStatus().getParaValue());
				return;
			}
			
			
			//3.判断本次任务片段总共生成多少密钥
			//3.1缓冲数量
			int sliceSize = kpgTask.getDbCommitBufsize().intValue();
			
			if((kpgTask.getKpgKeyAmount().intValue()- kpgTask.getGeneratedKeyAmount().intValue())< kpgTask.getDbCommitBufsize().intValue()){//(总共生成数量-已经生成数量)<缓冲数量|| 最后一片了
				sliceSize = kpgTask.getKpgKeyAmount().intValue()- kpgTask.getGeneratedKeyAmount().intValue();
			}
			
			//3.+ 生成完毕
			if(sliceSize==0) return;
			
			LOG.info("执行任务块-["+taskId+"]任务数量："+sliceSize);

			//4.获取provider 产生KeyPairGenerator 
			KeyPairGenerator kpg =	KeyPairGenerator.getInstance(kpgTask.getKeyPairAlgorithm().getName());
			kpg.initialize(kpgTask.getKeyPairAlgorithm().getKeysize());
			
			//5.循环执行************************************************
			for(int i=1;i<=sliceSize;i++)	{
				
				long exeStartMills = System.currentTimeMillis();
				
				LOG.info("执行任务块-["+taskId+"]任务小段序号["+i+"]开始");
				
				//产生KeyPair
				KeyPair keyPair = kpg.generateKeyPair();

				PrivateKey privateKey = keyPair.getPrivate();
				PublicKey publicKey = keyPair.getPublic();
				
				//5.1 将公钥私钥转为base64存储
				String prikey = new BASE64Encoder().encode(privateKey.getEncoded());
				String pubkey = new BASE64Encoder().encode(publicKey.getEncoded());
				
				//5.2将密钥存储到数据库中
				KeyPairStandby1024 keyPairStandby1024 = new KeyPairStandby1024();
				keyPairStandby1024.setGenTime(new Date());
				keyPairStandby1024.setKeyPairAlgorithm(kpgTask.getKeyPairAlgorithm());
				keyPairStandby1024.setKpgTask(kpgTask);
				keyPairStandby1024.setPriKey(prikey);
				keyPairStandby1024.setPubKey(pubkey);
				
				
				keyPairStandby1024Service.insert1024(keyPairStandby1024);
				
				long exeEndMills = System.currentTimeMillis();

				LOG.info("执行任务块-["+taskId+"]任务小段序号["+i+"]完成.所用时间："+(exeEndMills-exeStartMills)+"毫秒");
			}
			
			//6.更新执行数量
			kpgTaskService.updateGeneratedKeyAmount(taskId, sliceSize);
			LOG.debug("executeTaskSlice - end");
		}

		
	
	}