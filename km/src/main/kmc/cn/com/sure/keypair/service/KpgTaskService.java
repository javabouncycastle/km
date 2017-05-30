/**
 * 
 */
package cn.com.sure.keypair.service;

import java.util.List;

import cn.com.sure.keypair.entry.KpgTask;
import cn.com.sure.km.KmApplicationexception;

/**
 * @author Limin
 *
 */
public interface KpgTaskService {

	/**
	 * @return 
	 * 
	 */
	List<KpgTask> selectAll();

	/**
	 * @param kpgTask
	 */
	void insert(KpgTask kpgTask)throws KmApplicationexception;

	/**
	 * @param kpgTask
	 */
	void update(KpgTask kpgTask);

	/**
	 * @param id
	 */
	void delete(Long id);

}
