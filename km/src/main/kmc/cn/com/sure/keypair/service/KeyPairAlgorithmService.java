package cn.com.sure.keypair.service;

import java.util.List;

import cn.com.sure.keypair.entry.KeyPairAlgorithm;
import cn.com.sure.km.KmApplicationexception;

public interface KeyPairAlgorithmService {
	
	/**
	 * 增加密钥算法
	 * @param keypairAlgorithm
	 * @return 
	 * @throws KmApplicationexception
	 */
	int insert(KeyPairAlgorithm keyPairAlgorithm) throws  KmApplicationexception;

	/**
	 * 查询全部密钥算法
	 * @param keypairAlgorithm
	 * @return
	 */
	List<KeyPairAlgorithm> selectAll(KeyPairAlgorithm keyPairAlgorithm);

	/**
	 * 更新密钥算法
	 * @param keypairAlgorithm
	 * @return 
	 */
	int update(KeyPairAlgorithm keyPairAlgorithm);
	
	/**
	 * 删除密钥算法
	 * @param id
	 * @return 
	 */
	int delete(Long id);

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
	List<KeyPairAlgorithm> selectOpYes(KeyPairAlgorithm keyPairAlgorithm);

	/**
	 * @param keypairAlgorithm
	 * @return
	 */
	List<KeyPairAlgorithm> searchByCondition(KeyPairAlgorithm keyPairAlgorithm);

	/**
	 * @param id
	 * @return
	 */
	KeyPairAlgorithm selectById(Long id);

}
