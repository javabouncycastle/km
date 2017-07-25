/**
 * 
 */
package cn.com.sure.kpg.service;

import java.util.List;

import cn.com.sure.kpg.entry.KeypairStandby;


/**
 * @author Limin
 *
 */
public interface KeypairStandbyService {

	/**
	 * 
	 */
	void insert(KeypairStandby keyPairStandby);


	/**
	 * @return 
	 * 
	 */
	List<KeypairStandby> selectAll();

}
