package cn.com.sure.keypair.dao;

import java.util.List;

import cn.com.sure.keypair.entry.KeyPairAlgorithm;

public interface KeyPairAlgorithmDAO {


	/**
	 * 增加密钥算法
	 * @param keypairAlgorithm
	 */
	void insert(KeyPairAlgorithm keyPairAlgorithm);

	/**
	 * 查询全部密钥算法
	 * @param keypairAlgorithm
	 * @return
	 */
	List<KeyPairAlgorithm> selectAll(KeyPairAlgorithm keyPairAlgorithm);

	/**
	 * 更新密钥算法
	 * @param keypairAlgorithm
	 */
	void update(KeyPairAlgorithm keyPairAlgorithm);

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
	KeyPairAlgorithm findById(Long id);
	
	/**
	 * 根据name查找密钥算法
	 * @param keypairAlgorithm
	 * @return
	 */
	KeyPairAlgorithm findByName(KeyPairAlgorithm keyPairAlgorithm);

	/**
	 * @return
	 * 查询已启用的数据
	 */
	List<KeyPairAlgorithm> selectOpYes(KeyPairAlgorithm keyPairAlgorithm);

	/**
	 * @return
	 */
	List<KeyPairAlgorithm> searchByCondition(KeyPairAlgorithm keyPairAlgorithm);

}
