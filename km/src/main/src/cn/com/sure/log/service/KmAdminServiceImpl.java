/**
 * 
 */
package cn.com.sure.log.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.admin.entry.KmAdmin;
import cn.com.sure.log.dao.KmAdminDAO;
import cn.com.sure.socket.DigitalEnveServiceImpl;

/**
 * @author Limin
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("AdminService")
public class KmAdminServiceImpl implements KmAdminService{
	
	private static final Log LOG = LogFactory.getLog(DigitalEnveServiceImpl.class);
	
	@Autowired
	KmAdminDAO adminDAO;

	/* (non-Javadoc)
	 * @see cn.com.sure.commom.service.AdminService#fingByAdmId(int)
	 */
	@Override
	public KmAdmin fingByAdmId(int adminAuthNum) {
		LOG.debug("fingByAdmId - start");
		KmAdmin admin = adminDAO.fingByAdmId(adminAuthNum);
		LOG.debug("fingByAdmId - end");
		return admin;
	}

}
