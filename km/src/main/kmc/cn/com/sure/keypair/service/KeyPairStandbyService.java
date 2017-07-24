/**
 * 
 */
package cn.com.sure.keypair.service;

import java.util.List;

import cn.com.sure.keypair.entry.KeyPairStandby;


/**
 * @author Limin
 *
 */
public interface KeyPairStandbyService {

	/**
	 * 
	 */
	void insert(KeyPairStandby keyPairStandby);


	/**
	 * @return 
	 * 
	 */
	List<KeyPairStandby> selectAll();

}
