/**
 * 
 */
package cn.com.sure.keypair.dao;

import java.util.List;

import cn.com.sure.kpg.entry.KeypairInuse;

/**
 * @author Limin
 *
 */
public interface KeypairInuseDAO {

	/**
	 * @return
	 */
	List<KeypairInuse> selectAll();

	/**
	 * @param inuse
	 */
	void insert(KeypairInuse inuse);

	/**
	 * @param kpInuse
	 * @return 
	 */
	KeypairInuse findBySn(KeypairInuse kpInuse);

	/**
	 * @param id
	 */
	void delete(String id);

}
