/**
 * 
 */
package cn.com.sure.keypair.dao;

import java.util.List;

import cn.com.sure.keypair.entry.KeyPairStandby;


/**
 * @author Limin
 *
 */
public interface KeyPairStandbyDAO {


	/**
	 * @param keyPairStandby
	 */
	void insert(KeyPairStandby keyPairStandby);


	/**
	 * @return
	 */
	List<KeyPairStandby> selectAll();

}
