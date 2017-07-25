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

}
