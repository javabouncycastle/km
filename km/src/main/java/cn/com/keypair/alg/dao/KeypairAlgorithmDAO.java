package cn.com.keypair.alg.dao;

import java.util.List;

import cn.com.keypair.alg.entry.KeypairAlgorithm;


public interface KeypairAlgorithmDAO {

	int findNameCountById(KeypairAlgorithm keypairAlgorithm);

	void insert(KeypairAlgorithm keypairAlgorithm);

	List<KeypairAlgorithm> selectAll(KeypairAlgorithm keypairAlgorithm);

	void update(KeypairAlgorithm keypairAlgorithm);

	void delete(Long id);

	KeypairAlgorithm findById(KeypairAlgorithm keypairAlgorithm);

}