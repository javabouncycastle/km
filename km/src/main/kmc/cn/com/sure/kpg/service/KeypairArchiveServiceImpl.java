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

import cn.com.sure.keypair.dao.KeypairArchiveDAO;
import cn.com.sure.kpg.entry.KeypairArchive;

/**
 * @author Limin
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("keypairArchiveService")
public class KeypairArchiveServiceImpl implements KeypairArchiveService{
	
	private static final Log LOG = LogFactory.getLog(KeypairArchiveServiceImpl.class);
	
	@Autowired
	private KeypairArchiveDAO keyPairArchiveDAO;

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KeyPairArchiveService#selectAll()
	 */
	@Override
	public List<KeypairArchive> selectAll() {
		LOG.debug("selectAll - start");
		List<KeypairArchive>keypairArchives= keyPairArchiveDAO.selectAll();
		LOG.debug("selectAll - end");
		return keypairArchives;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.kpg.service.KeypairArchiveService#insert(cn.com.sure.kpg.entry.KeypairArchive)
	 */
	@Override
	public void insert(KeypairArchive kpArchive) {
		LOG.debug("selectAll - start");
		keyPairArchiveDAO.insert(kpArchive);
		LOG.debug("selectAll - end");
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.kpg.service.KeypairArchiveService#findBySn(java.lang.String)
	 */
	@Override
	public KeypairArchive findBySn(String certSn) {
		LOG.debug("findBySn - start");
		KeypairArchive kpArchive = keyPairArchiveDAO.findBySn(certSn);
		LOG.debug("findBySn");
		
		return kpArchive;
	}

}
