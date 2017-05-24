package cn.com.sure.keypair.alg.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.common.KmConstants;
import cn.com.sure.keypair.alg.dao.KeypairAlgorithmDAO;
import cn.com.sure.keypair.alg.entry.KeypairAlgorithm;
import cn.com.sure.km.KmApplicationexception;
import cn.com.sure.km.KmErrorMessageConstants;

@Transactional(propagation = Propagation.REQUIRED)
@Service("keypairAlgorithmService")
public class KeypairAlgorithmServiceImpl implements KeypairAlgorithmService{
	
	private static final Log LOG = LogFactory.getLog(KeypairAlgorithmService.class);
	
	@Autowired
	private KeypairAlgorithmDAO keypairAlgorithmDAO;

	@Override
	public void insert(KeypairAlgorithm keypairAlgorithm) throws KmApplicationexception {
		LOG.debug("insert - start");
		KeypairAlgorithm dbkeypairAlgorithm = this.keypairAlgorithmDAO.findByName(keypairAlgorithm);
		if (dbkeypairAlgorithm!=null){
			KmApplicationexception.throwException(KmErrorMessageConstants.nameExist, new String[]{keypairAlgorithm.getName()});
		}if(dbkeypairAlgorithm==null){
			keypairAlgorithmDAO.insert(keypairAlgorithm);
		}
		LOG.debug("insert - end");
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
	public void suspend(Long id) {
		LOG.debug("suspend - start");
		KeypairAlgorithm keypairAlgorithm = keypairAlgorithmDAO.findById(id);
		keypairAlgorithm.setIsValid(KmConstants.YES_OR_NO_OPTION_NO);
		keypairAlgorithmDAO.update(keypairAlgorithm);
		LOG.debug("suspend - end");
		
	}

	@Override
	public void activate(Long id) {
		LOG.debug("activate - start");
		KeypairAlgorithm keypairAlgorithm = keypairAlgorithmDAO.findById(id);
		keypairAlgorithm.setIsValid(KmConstants.YES_OR_NO_OPTION_YES);
		keypairAlgorithmDAO.update(keypairAlgorithm);
		LOG.debug("activate - end");
	}

}