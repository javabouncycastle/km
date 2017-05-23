package cn.com.sure.keypair.alg.service;

import java.util.List;

import cn.com.sure.keypair.alg.entry.KeypairAlgorithm;
import cn.com.sure.km.KmApplicationexception;

public interface KeypairAlgorithmService {

	void insert(KeypairAlgorithm keypairAlgorithm) throws  KmApplicationexception;

	List<KeypairAlgorithm> selectAll(KeypairAlgorithm keypairAlgorithm);

	void update(KeypairAlgorithm keypairAlgorithm);

	void delete(Long id);

	void suspend(Long id);

	void activate(Long id);

	

}
