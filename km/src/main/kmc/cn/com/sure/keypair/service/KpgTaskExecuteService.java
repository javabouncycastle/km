/**
 * 
 */
package cn.com.sure.keypair.service;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import cn.com.sure.km.KmApplicationexception;

/**
 * @author Limin
 *
 */
public interface KpgTaskExecuteService {
	
	void executeTaskSlice(Long taskId)throws NoSuchAlgorithmException, NoSuchProviderException, KmApplicationexception, ClassNotFoundException, InstantiationException, IllegalAccessException;

}
