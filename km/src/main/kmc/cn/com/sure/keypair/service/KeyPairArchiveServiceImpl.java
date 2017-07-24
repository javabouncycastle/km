/**
 * 
 */
package cn.com.sure.keypair.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sure.keypair.dao.KeyPairArchiveDAO;
import cn.com.sure.keypair.entry.KeyPairArchive;

/**
 * @author Limin
 *
 */
public class KeyPairArchiveServiceImpl implements KeyPairArchiveService{
	
	private static final Log LOG = LogFactory.getLog(KeyPairArchiveServiceImpl.class);
	
	@Autowired
	private KeyPairArchiveDAO keyPairArchiveDAO;

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KeyPairArchiveService#selectAll()
	 */
	@Override
	public List<KeyPairArchive> selectAll() {
		LOG.debug("selectAll - start");
		keyPairArchiveDAO.selectAll();
		LOG.debug("selectAll - end");
		return null;
	}

}
