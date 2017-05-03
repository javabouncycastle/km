package cn.com.keypair.alg.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.keypair.alg.dao.KeypairAlgorithmDAO;
import cn.com.keypair.alg.entry.KeypairAlgorithm;

@Transactional(propagation = Propagation.REQUIRED)
@Service("keypairAlgorithmService")
public class KeypairAlgorithmServiceImpl implements KeypairAlgorithmService{
	
	private static final Log LOG = LogFactory.getLog(KeypairAlgorithmService.class);
	
	@Autowired
	private KeypairAlgorithmDAO keypairAlgorithmDAO;

	@Override
	public Map insert(KeypairAlgorithm keypairAlgorithm) {
		LOG.debug("insert - start");
		Map<String,Object> resultMap=new HashMap<String,Object>();
		int i = keypairAlgorithmDAO.findNameCountById(keypairAlgorithm);
		if(i==0){
			keypairAlgorithmDAO.insert(keypairAlgorithm);
			resultMap.put("success", true);
		}else{
			resultMap.put("success", false);
			resultMap.put("msg", "");
		}
		LOG.debug("insert - end");
		return resultMap;
	}

	@Override
	public List<KeypairAlgorithm> selectAll(KeypairAlgorithm keypairAlgorithm) {
		LOG.debug("selectAll - start");
		List<KeypairAlgorithm> keypairAlgorithms = keypairAlgorithmDAO.selectAll(keypairAlgorithm);
		LOG.debug("selectAll - end");
		return keypairAlgorithms;
	}

	@Override
	public void update(KeypairAlgorithm keypairAlgorithm) {
		LOG.debug("update - start");
		keypairAlgorithmDAO.update(keypairAlgorithm);
		LOG.debug("update - end");
	}

	@Override
	public void delete(Long id) {
		LOG.debug("delete - start");
		keypairAlgorithmDAO.delete(id);
		LOG.debug("delete - end");
	}

	@Override
	public KeypairAlgorithm findById(KeypairAlgorithm keypairAlgorithm) {
		keypairAlgorithm = keypairAlgorithmDAO.findById(keypairAlgorithm);
		return keypairAlgorithm;
	}
	

}
