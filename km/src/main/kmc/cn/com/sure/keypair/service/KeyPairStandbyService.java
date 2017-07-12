/**
 * 
 */
package cn.com.sure.keypair.service;

import cn.com.sure.keypair.entry.KeyPairStandby;


/**
 * @author Limin
 *
 */
public interface KeyPairStandbyService {

	/**
	 * 
	 */
	void insert1024(KeyPairStandby keyPairStandby);

	void insert2048(KeyPairStandby keyPairStandby);

}
