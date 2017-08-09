/**
 * 
 */
package cn.com.sure.keypair.dao;

import java.util.List;

import cn.com.sure.kpg.entry.KeypairArchive;

/**
 * @author Limin
 *
 */
public interface KeypairArchiveDAO {

	/**
	 * @return 
	 * 
	 */
	public List<KeypairArchive> selectAll();

	/**
	 * @param kpArchive
	 */
	public void insert(KeypairArchive kpArchive);

	/**
	 * @param certSn
	 * @return 
	 */
	public KeypairArchive findBySn(String certSn);

}
