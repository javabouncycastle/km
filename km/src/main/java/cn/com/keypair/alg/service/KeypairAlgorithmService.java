package cn.com.keypair.alg.service;

import java.util.List;
import java.util.Map;

import cn.com.keypair.alg.entry.KeypairAlgorithm;

public interface KeypairAlgorithmService {

	Map insert(KeypairAlgorithm keypairAlgorithm);

	List<KeypairAlgorithm> selectAll(KeypairAlgorithm keypairAlgorithm);

	

}
