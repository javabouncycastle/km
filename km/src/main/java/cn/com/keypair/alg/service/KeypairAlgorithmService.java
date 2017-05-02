package cn.com.keypair.alg.service;

import java.util.List;
import java.util.Map;

import cn.com.keypair.alg.entry.KeypairAlgorithm;
import cn.com.sure.km.KmApplicationexception;

public interface KeypairAlgorithmService {

	Map insert(KeypairAlgorithm keypairAlgorithm) throws  KmApplicationexception;

	List<KeypairAlgorithm> selectAll(KeypairAlgorithm keypairAlgorithm);

	void update(KeypairAlgorithm keypairAlgorithm);

	void delete(Long id);

	

}
