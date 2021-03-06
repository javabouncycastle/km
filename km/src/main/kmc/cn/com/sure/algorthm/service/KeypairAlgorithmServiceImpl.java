package cn.com.sure.algorthm.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.algorthm.entry.KeyPairAlgorithm;
import cn.com.sure.common.KmConstants;
import cn.com.sure.keypair.dao.KeypairAlgorithmDAO;
import cn.com.sure.km.KmApplicationexception;
import cn.com.sure.km.KmErrorMessageConstants;

@Transactional(propagation = Propagation.REQUIRED)
@Service("keypairAlgorithmService")
public class KeypairAlgorithmServiceImpl implements KeypairAlgorithmService{
	
	private static final Log LOG = LogFactory.getLog(KeypairAlgorithmService.class);
	
	@Autowired
	private KeypairAlgorithmDAO keyPairAlgorithmDAO;

	@Override
	@Transactional(value="txManager" )//配置事务，基本形式
	public int insert(KeyPairAlgorithm keyPairAlgorithm) throws KmApplicationexception {
		LOG.debug("insert - start");
		int i = keyPairAlgorithmDAO.insert(keyPairAlgorithm);
		/*KeyPairAlgorithm dbkeyPairAlgorithm = this.keyPairAlgorithmDAO.selectByName(keyPairAlgorithm);
		int i=0;
		if (dbkeyPairAlgorithm.equals(keyPairAlgorithm)){
			KmApplicationexception.throwException(KmErrorMessageConstants.nameExist, new String[]{keyPairAlgorithm.getName()});
		}if(!dbkeyPairAlgorithm.equals(keyPairAlgorithm)){
			i = keyPairAlgorithmDAO.insert(keyPairAlgorithm);
		}*/
		LOG.debug("insert - end");
		return i;
	}

	@Override
	public List<KeyPairAlgorithm> selectAll() {
		LOG.debug("selectAll - start");
		List<KeyPairAlgorithm> keyPairAlgorithms = keyPairAlgorithmDAO.selectAll();
		LOG.debug("selectAll - end");
		return keyPairAlgorithms;
	}

	@Override
	public int update(KeyPairAlgorithm keyPairAlgorithm) throws KmApplicationexception {
		LOG.debug("update - start");
		KeyPairAlgorithm keyPairAlgorithmDB = keyPairAlgorithmDAO.selectById(keyPairAlgorithm.getId());
		int i = 0 ;
		if(keyPairAlgorithmDB.equals(keyPairAlgorithm)){
			KmApplicationexception.throwException(KmErrorMessageConstants.nameExist, new String[]{keyPairAlgorithm.getName()});
		}if(!keyPairAlgorithmDB.equals(keyPairAlgorithm)){
			i = keyPairAlgorithmDAO.update(keyPairAlgorithm);
		}
		
		LOG.debug("update - end");
		return i;
	}

	@Override
	public int delete(Long id) {
		LOG.debug("delete - start");
		int i = keyPairAlgorithmDAO.delete(id);
		LOG.debug("delete - end");
		return i;
	}

	@Override
	public void suspend(Long id) {
		LOG.debug("suspend - start");
		KeyPairAlgorithm keyPairAlgorithm = keyPairAlgorithmDAO.selectById(id);
		keyPairAlgorithm.setIsValid(KmConstants.YES_OR_NO_OPTION_NO);
		keyPairAlgorithmDAO.update(keyPairAlgorithm);
		LOG.debug("suspend - end");
		
	}

	@Override
	public void activate(Long id) {
		LOG.debug("activate - start");
		KeyPairAlgorithm keyPairAlgorithm = keyPairAlgorithmDAO.selectById(id);
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

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KeyPairAlgorithmService#selectById(java.lang.Long)
	 */
	@Override
	public KeyPairAlgorithm selectById(Long id) {
		LOG.debug("selectById - start");
		KeyPairAlgorithm keyPairAlgorithms = keyPairAlgorithmDAO.selectById(id);
		LOG.debug("selectById - end");
		return keyPairAlgorithms;
	}

}
