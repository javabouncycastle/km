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

import cn.com.sure.keypair.dao.KeypairStandbyDAO;
import cn.com.sure.keypair.entry.KeypairStandby;

/**
 * @author Limin
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("keypairStandbyService")
public class KeypairStandbyServiceImpl implements KeypairStandbyService{
	
	private static final Log LOG = LogFactory.getLog(KeypairStandbyServiceImpl.class);
	
	@Autowired
	private KeypairStandbyDAO keypairStandbyDAO;

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KeypairStandbyService#insert()
	 */
	@Override
	public void insert1024(KeypairStandby keypairStandby) {
		LOG.debug("insert - start");
		this.keypairStandbyDAO.insert(keypairStandby);
		LOG.debug("insert - end");
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KeypairStandbyService#insert2048(cn.com.sure.keypair.entry.KeypairStandby)
	 */
	@Override
	public void insert2048(KeypairStandby keypairStandby) {
		LOG.debug("insert - start");
		this.keypairStandbyDAO.insert(keypairStandby);
		LOG.debug("insert - end");
	}

}
