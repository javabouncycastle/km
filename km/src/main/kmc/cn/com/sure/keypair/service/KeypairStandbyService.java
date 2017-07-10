/**
 * 
 */
package cn.com.sure.keypair.service;

import cn.com.sure.keypair.entry.KeypairStandby;

/**
 * @author Limin
 *
 */
public interface KeypairStandbyService {

	/**
	 * 
	 */
	void insert1024(KeypairStandby keypairStandby);

	/**
	 * @param keypairStandby
	 */
	void insert2048(KeypairStandby keypairStandby);

}
