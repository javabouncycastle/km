/**
 * 
 */
package cn.com.sure.auto.kpg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import cn.com.sure.algorthm.entry.KeyPairAlgorithm;
import cn.com.sure.algorthm.service.KeypairAlgorithmService;
import cn.com.sure.kpg.service.KeypairStandbyService;
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
	

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		//1查询
		//1.1查询密钥算法
		List <KeyPairAlgorithm> list = keypairAlgorithmService.selectAll();
		
		//1.2查询每种算法的密钥数量
		for(KeyPairAlgorithm kpa:list){
			
			if(keypairStandbyService.countNum(kpa.getAlgorithmName())<Integer.parseInt(sysCodeService.selectMin())){//密钥数量<密钥数量最小值
				new Thread(new AutoKeypairGenThead()).start();
			}
			return;
		}
		
	}

}
