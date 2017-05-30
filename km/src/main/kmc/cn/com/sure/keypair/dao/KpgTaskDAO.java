/**
 * 
 */
package cn.com.sure.keypair.dao;

import java.util.List;

import cn.com.sure.keypair.entry.KpgTask;

/**
 * @author Limin
 *
 */
public interface KpgTaskDAO {

	/**
	 * @return
	 */
	List<KpgTask> selectAll();

	/**
	 * @param name
	 * @return
	 */
	KpgTask findByName(String name);

	/**
	 * @param kpgTask
	 */
	void update(KpgTask kpgTask);

	/**
	 * @param id
	 */
	void delete(Long id);

	/**
	 * @param kpgTask
	 */
	void insert(KpgTask kpgTask);

}
