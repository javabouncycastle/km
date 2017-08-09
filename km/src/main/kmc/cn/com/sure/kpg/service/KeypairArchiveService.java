/**
 * 
 */
package cn.com.sure.kpg.service;

import java.util.List;

import cn.com.sure.kpg.entry.KeypairArchive;

/**
 * @author Limin
 *
 */
public interface KeypairArchiveService {

	/**
	 * 
	 */
	List <KeypairArchive> selectAll();

	/**
	 * @param kpArchive
	 */
	void insert(KeypairArchive kpArchive);

	/**
	 * @param certSn
	 * @return 
	 */
	KeypairArchive findBySn(String certSn);

}
