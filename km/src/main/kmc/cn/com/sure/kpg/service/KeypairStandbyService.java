/**
 * 
 */
package cn.com.sure.kpg.service;

import java.util.List;

import javax.servlet.ServletOutputStream;

import cn.com.sure.algorthm.entry.KeyPairAlgorithm;
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


	/**
	 * @param algorithmName
	 * @return
	 */
	int countNum(String algorithmName);


	/**
	 * @return
	 */
	KeypairStandby obtKpStandby(KeyPairAlgorithm algSize);


	/**
	 * @param id
	 */
	void delete(String id);


	/**
	 * @param id
	 */
	void backups(KeypairStandby keypairStandby,ServletOutputStream out);


	/**
	 * @param keypairStandby
	 */
	List<KeypairStandby> searchByCondition(KeypairStandby keypairStandby);

}
