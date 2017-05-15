package cn.com.keypair.alg.service;

import java.util.List;

import cn.com.keypair.alg.entry.KeypairAlgorithm;
import cn.com.sure.km.KmApplicationexception;

public interface KeypairAlgorithmService {

	void insert(KeypairAlgorithm keypairAlgorithm) throws  KmApplicationexception;

	List<KeypairAlgorithm> selectAll(KeypairAlgorithm keypairAlgorithm);

	void update(KeypairAlgorithm keypairAlgorithm);

	void delete(Long id);

	KeypairAlgorithm findById(KeypairAlgorithm keypairAlgorithm);

	void suspend(Long id);

	void activate(Long id);

	

}
