package cn.com.sure.keypair.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.common.KmConstants;
import cn.com.sure.keypair.dao.KeyPairAlgorithmDAO;
import cn.com.sure.keypair.entry.KeyPairAlgorithm;
import cn.com.sure.km.KmApplicationexception;
import cn.com.sure.km.KmErrorMessageConstants;

@Transactional(propagation = Propagation.REQUIRED)
@Service("keypairAlgorithmService")
public class KeyPairAlgorithmServiceImpl implements KeyPairAlgorithmService{
	
	private static final Log LOG = LogFactory.getLog(KeyPairAlgorithmService.class);
	
	@Autowired
	private KeyPairAlgorithmDAO keyPairAlgorithmDAO;

	@Override
	@Transactional(value="txManager" )//配置事务，基本形式
	public void insert(KeyPairAlgorithm keyPairAlgorithm) throws KmApplicationexception {
		LOG.debug("insert - start");
		KeyPairAlgorithm dbkeyPairAlgorithm = this.keyPairAlgorithmDAO.findByName(keyPairAlgorithm);
		if (dbkeyPairAlgorithm!=null){
			KmApplicationexception.throwException(KmErrorMessageConstants.nameExist, new String[]{keyPairAlgorithm.getName()});
		}if(dbkeyPairAlgorithm==null){
			keyPairAlgorithmDAO.insert(keyPairAlgorithm);
		}
		LOG.debug("insert - end");
	}

	@Override
	public List<KeyPairAlgorithm> selectAll(KeyPairAlgorithm keyPairAlgorithm) {
		LOG.debug("selectAll - start");
		List<KeyPairAlgorithm> keyPairAlgorithms = keyPairAlgorithmDAO.selectAll(keyPairAlgorithm);
		LOG.debug("selectAll - end");
		return keyPairAlgorithms;
	}

	@Override
	@Transactional(value="txManager" )
	public void update(KeyPairAlgorithm keyPairAlgorithm) {
		LOG.debug("update - start");
		keyPairAlgorithmDAO.update(keyPairAlgorithm);
		LOG.debug("update - end");
	}

	@Override
	@Transactional(value="txManager" )
	public void delete(Long id) {
		LOG.debug("delete - start");
		keyPairAlgorithmDAO.delete(id);
		LOG.debug("delete - end");
	}

	@Override
	@Transactional(value="txManager" )
	public void suspend(Long id) {
		LOG.debug("suspend - start");
		KeyPairAlgorithm keyPairAlgorithm = keyPairAlgorithmDAO.findById(id);
		keyPairAlgorithm.setIsValid(KmConstants.YES_OR_NO_OPTION_NO);
		keyPairAlgorithmDAO.update(keyPairAlgorithm);
		LOG.debug("suspend - end");
		
	}

	@Override
	@Transactional(value="txManager" )
	public void activate(Long id) {
		LOG.debug("activate - start");
		KeyPairAlgorithm keyPairAlgorithm = keyPairAlgorithmDAO.findById(id);
		keyPairAlgorithm.setIsValid(KmConstants.YES_OR_NO_OPTION_YES);
		keyPairAlgorithmDAO.update(keyPairAlgorithm);
		LOG.debug("activate - end");
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KeypairAlgorithmService#selectOpYes()
	 */
	@Override
	public List<KeyPairAlgorithm> selectOpYes(KeyPairAlgorithm keyPairAlgorithm) {
		LOG.debug("selectOpYes - start");
		keyPairAlgorithm.setIsValid(KmConstants.YES_OR_NO_OPTION_YES);
		List<KeyPairAlgorithm> keyPairAlgorithms = keyPairAlgorithmDAO.selectOpYes(keyPairAlgorithm);
		LOG.debug("selectOpYes - start");
		return keyPairAlgorithms;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KeypairAlgorithmService#searchByCondition(cn.com.sure.keypair.entry.KeypairAlgorithm)
	 */
	@Override
	public List<KeyPairAlgorithm> searchByCondition(KeyPairAlgorithm keyPairAlgorithm) {
		LOG.debug("searchByCondition - start");
		List<KeyPairAlgorithm> keyPairAlgorithms = this.keyPairAlgorithmDAO.searchByCondition(keyPairAlgorithm);
		LOG.debug("searchByCondition - end");
		return keyPairAlgorithms;
	}

}
