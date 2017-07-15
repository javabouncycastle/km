/**
 * 
 */
package cn.com.sure.keypair.dao;

import java.util.List;

import cn.com.sure.kpg.entry.KeypairStandby;


/**
 * @author Limin
 *
 */
public interface KeyPairStandbyDAO {


	/**
	 * @param keyPairStandby
	 */
	void insert(KeypairStandby keyPairStandby);


	/**
	 * @return
	 */
	List<KeypairStandby> selectAll();


	/**
	 * @return
	 */
	int countNum(String algorithmName);

}
