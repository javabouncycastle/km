package cn.com.sure.keypair.dao;

import java.util.List;

import cn.com.sure.keypair.entry.KeypairAlgorithm;

public interface KeypairAlgorithmDAO {

	int findNameCountById(KeypairAlgorithm keypairAlgorithm);

	void insert(KeypairAlgorithm keypairAlgorithm);

	List<KeypairAlgorithm> selectAll(KeypairAlgorithm keypairAlgorithm);

	void update(KeypairAlgorithm keypairAlgorithm);

	void delete(Long id);

	KeypairAlgorithm findById(Long id);
	
	KeypairAlgorithm findByName(KeypairAlgorithm keypairAlgorithm);

}
