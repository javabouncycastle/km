/**
 * 
 */
package cn.com.sure.auto.kpg;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.XmlWebApplicationContext;

import cn.com.sure.algorthm.entry.KeyPairAlgorithm;
import cn.com.sure.algorthm.service.KeypairAlgorithmService;
import cn.com.sure.common.KeyPairGenThread;
import cn.com.sure.common.KmConstants;
import cn.com.sure.km.KmApplicationexception;
import cn.com.sure.kpg.service.KeypairStandbyService;
import cn.com.sure.kpgtask.entry.KpgTask;
import cn.com.sure.kpgtask.service.KpgTaskExecuteService;
import cn.com.sure.kpgtask.service.KpgTaskService;
import cn.com.sure.syscode.entry.SysCode;
import cn.com.sure.syscode.service.SysCodeService;

/**
 * @author Limin
 *
 */
@Component
public class AutoKpg implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	private SysCodeService sysCodeService;
	
	@Autowired
	private KeypairStandbyService keypairStandbyService;
	
	@Autowired
	private KeypairAlgorithmService keypairAlgorithmService;
	
	@Autowired
	private KpgTaskService kpgTaskService;
	
	@Autowired
	private KpgTaskExecuteService kpgTaskExecuteService;
	
	

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
	   if (event.getSource() instanceof XmlWebApplicationContext) {
           if (((XmlWebApplicationContext) event.getSource()).getDisplayName().equals("Root WebApplicationContext")) {
        	   
        	   autoKpg();
      
           }
       }
	}
	 public void autoKpg(){
	 		//1查询
    		//1.1查询密钥算法
    		List <KeyPairAlgorithm> list = keypairAlgorithmService.selectAll();
    		
    		//1.2查询每种算法的密钥数量
    		for(KeyPairAlgorithm kpa:list){
    			
    			//1.3判断数据库密钥数量是否小于密钥最小数量
    			if(keypairStandbyService.countNum(kpa.getAlgorithmName())<Integer.parseInt(sysCodeService.selectMin().get(0).getParaValue())){//密钥数量<密钥数量最小值
    				
    				//1.4新建一个任务,生成密钥
    				SysCode sysCode = new SysCode();
    				KpgTask task = new KpgTask();
    				
    				//1.4.1缓存数量，默认获取第一条
    				sysCode.setParaValue(sysCodeService.selectBuffer().get(0).getParaValue());
    				task.setDbCommitBufsize(sysCode);
    				
    				//1.4.2生成最小数量，默认取第一条
    				SysCode code = new SysCode();
    				code.setParaValue(sysCodeService.selectGenKeyNum().get(0).getParaValue());
    				task.setKpgKeyAmount(Integer.parseInt(code.getParaValue()));
    				
    				//1.4.3密钥算法
    				KeyPairAlgorithm algorithm = new KeyPairAlgorithm();
    				algorithm.setId(kpa.getId());
    				task.setKeyPairAlgorithm(algorithm);
    				
    				//1.4.4
    				SysCode codeAlg = new SysCode();
    				codeAlg.setParaValue(String.valueOf(KmConstants.CODE_ID_TASK_STATUS_EXECUTING));
    				task.setTaskStatus(codeAlg);
    				//用当前时间来明明任务名称
    				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    				task.setName("系统自动生成密钥"+sdf.format(new Date()));
    				task.setStartTime(new Date());
    				try {
    					//插入
    					kpgTaskService.insert(task);
    				} catch (KmApplicationexception e) {
    					e.printStackTrace();
    				}
    				
    				//启动线程，自动生成密钥
    				new Thread(new KeyPairGenThread(kpgTaskService,task,kpgTaskExecuteService)).start();
    			}
    			
    		}
  	   
     }
}
