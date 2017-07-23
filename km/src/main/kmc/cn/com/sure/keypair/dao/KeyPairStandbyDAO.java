/**
 * 
 */
package cn.com.sure.keypair.dao;

import cn.com.sure.keypair.entry.KeyPairStandby;


/**
 * @author Limin
 *
 */
public interface KeyPairStandbyDAO {


	/**
	 * @param keyPairStandby
	 */
	void insert1024(KeyPairStandby keyPairStandby);

	/**
	 * @param keyPairStandby2048
	 */
	void insert2048(KeyPairStandby keyPairStandby2048);

}
