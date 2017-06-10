/**
 * 
 */
package cn.com.sure.keypair.service;

import java.security.NoSuchAlgorithmException;

import cn.com.sure.keypair.entry.KpgTask;
import cn.com.sure.km.KmApplicationexception;

/**
 * @author Limin
 *
 */
public interface KpgQuartzTaskExecutorService {

	/**
	 * @throws NoSuchAlgorithmException 
	 * @throws KmApplicationexception 
	 * 
	 */
	void executeTask(KpgTask kpgTask) throws NoSuchAlgorithmException, KmApplicationexception;

}
