/**
 * 
 */
package cn.com.sure.keypair.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.keypair.dao.KeyPairStandby1024DAO;
import cn.com.sure.keypair.entry.KeyPairStandby1024;

/**
 * @author Limin
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("keyPairStandby1024Service")
public class KeyPairStandby1024ServiceImpl implements KeyPairStandby1024Service{
	
	private static final Log LOG = LogFactory.getLog(KeyPairStandby1024ServiceImpl.class);
	
	@Autowired
	private KeyPairStandby1024DAO keyPairStandby1024DAO;

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KeypairStandbyService#insert()
	 */
	@Override
	public void insert1024(KeyPairStandby1024 keyPairStandby1024) {
		LOG.debug("insert - start");
		this.keyPairStandby1024DAO.insert(keyPairStandby1024);
		LOG.debug("insert - end");
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KeypairStandbyService#insert2048(cn.com.sure.keypair.entry.KeypairStandby)
	 */
/*	@Override
	public void insert2048(KeypairStandby1024 keypairStandby1024) {
		LOG.debug("insert - start");
		this.keypairStandby1024DAO.insert(keypairStandby1024);
		LOG.debug("insert - end");
	}*/

}
