package cn.com.sure.keypair.dao;

import java.util.List;

import cn.com.sure.keypair.entry.KeypairAlgorithm;

public interface KeypairAlgorithmDAO {

/*	*//**
	 * 
	 * @param keypairAlgorithm
	 * @return
	 *//*
	int findNameCountById(KeypairAlgorithm keypairAlgorithm);*/

	/**
	 * 增加密钥算法
	 * @param keypairAlgorithm
	 */
	void insert(KeypairAlgorithm keypairAlgorithm);

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
	 * 根据id查找密钥算法
	 * @param id
	 * @return
	 */
	KeypairAlgorithm findById(Long id);
	
	/**
	 * 根据name查找密钥算法
	 * @param keypairAlgorithm
	 * @return
	 */
	KeypairAlgorithm findByName(KeypairAlgorithm keypairAlgorithm);

	/**
	 * @return
	 * 查询已启用的数据
	 */
	List<KeypairAlgorithm> selectOpYes(KeypairAlgorithm keypairAlgorithm);

}
