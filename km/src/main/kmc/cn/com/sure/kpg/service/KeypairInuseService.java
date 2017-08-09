/**
 * 
 */
package cn.com.sure.kpg.service;

import java.util.List;

import cn.com.sure.kpg.entry.KeypairInuse;

/**
 * @author Limin
 *
 */
public interface KeypairInuseService {

	/**
	 * @return
	 */
	List<KeypairInuse> selectAll();

	/**
	 * 
	 */
	void findKeypair();

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
