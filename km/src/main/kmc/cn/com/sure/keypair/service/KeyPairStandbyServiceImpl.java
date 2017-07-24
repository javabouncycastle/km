/**
 * 
 */
package cn.com.sure.keypair.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.keypair.dao.KeyPairStandbyDAO;
import cn.com.sure.keypair.entry.KeyPairStandby;

/**
 * @author Limin
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("keyPairStandbyService")
public class KeyPairStandbyServiceImpl implements KeyPairStandbyService{
	
	private static final Log LOG = LogFactory.getLog(KeyPairStandbyServiceImpl.class);
	
	@Autowired
	private KeyPairStandbyDAO keyPairStandbyDAO;
	


	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KeypairStandbyService#insert()    
	 */
	@Override
	public void insert(KeyPairStandby keyPairStandby) {
		LOG.debug("insert - start");
		this.keyPairStandbyDAO.insert(keyPairStandby);
		LOG.debug("insert - end");
	}



	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KeyPairStandbyService#selectAll()
	 */
	@Override
	public List<KeyPairStandby> selectAll() {
		LOG.debug("selectAll - start");
		List<KeyPairStandby> keyPairStandbys = keyPairStandbyDAO.selectAll();
		LOG.debug("selectAll - end");
		return keyPairStandbys;
	}



}
