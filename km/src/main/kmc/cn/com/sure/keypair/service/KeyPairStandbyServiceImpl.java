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
	private KeyPairStandbyDAO keyPairStandby1024DAO;
	


	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KeypairStandbyService#insert()    
	 */
	@Override
	public void insert1024(KeyPairStandby keyPairStandby) {
		LOG.debug("insert - start");
		this.keyPairStandby1024DAO.insert1024(keyPairStandby);
		LOG.debug("insert - end");
	}



	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KeyPairStandbyService#insert2048(cn.com.sure.keypair.entry.KeyPairStandby)
	 */
	@Override
	public void insert2048(KeyPairStandby keyPairStandby2048) {
		// TODO Auto-generated method stub
		
	}



}
