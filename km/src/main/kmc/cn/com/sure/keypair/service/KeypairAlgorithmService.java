package cn.com.sure.keypair.service;

import java.util.List;

import cn.com.sure.keypair.entry.KeypairAlgorithm;
import cn.com.sure.km.KmApplicationexception;

public interface KeypairAlgorithmService {
	
	/**
	 * 增加密钥算法
	 * @param keypairAlgorithm
	 * @throws KmApplicationexception
	 */
	void insert(KeypairAlgorithm keypairAlgorithm) throws  KmApplicationexception;

	/**
	 * 查询全部密钥算法
	 * @param keypairAlgorithm
	 * @return
	 */
	List<KeypairAlgorithm> selectAll(KeypairAlgorithm keypairAlgorithm);

	/**
	 * 更新密钥算法
	 * @param keypairAlgorithm
	 */
	void update(KeypairAlgorithm keypairAlgorithm);
	
	/**
	 * 删除密钥算法
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 停用密钥算法
	 * @param id
	 */
	void suspend(Long id);

	/**
	 * 启用密钥算法
	 * @param id
	 */
	void activate(Long id);

	/**
	 * @param kpgTask 
	 * @param object
	 * @return
	 * 查询启用的数据
	 */
	List<KeypairAlgorithm> selectOpYes(KeypairAlgorithm keypairAlgorithm);

}
