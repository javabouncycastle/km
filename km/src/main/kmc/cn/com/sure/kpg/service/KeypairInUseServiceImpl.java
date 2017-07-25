/**
 * 
 */
package cn.com.sure.kpg.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.keypair.dao.KeypairInuseDAO;
import cn.com.sure.kpg.entry.KeypairInuse;

/**
 * @author Limin
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("keypairInuseService")
public class KeypairInUseServiceImpl implements KeypairInuseService{
	
	private static final Log LOG = LogFactory.getLog(KeypairInuseService.class);
	
	@Autowired
	private KeypairInuseDAO keyPairInUseDAO;

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KeyPairInUseService#selectAll()
	 */
	@Override
	public List<KeypairInuse> selectAll() {
		LOG.debug("selectAll - start");
		List<KeypairInuse> keyPairInUses = keyPairInUseDAO.selectAll();
		LOG.debug("selectAll - start");
		return keyPairInUses;
	}

}
