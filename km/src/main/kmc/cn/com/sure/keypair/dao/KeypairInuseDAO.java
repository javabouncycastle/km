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

}
