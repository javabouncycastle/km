package cn.com.sure.log.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.log.dao.AuditLogDAO;
import cn.com.sure.log.entry.AuditLog;

@Transactional(propagation = Propagation.REQUIRED)
@Service(value="auditLogServiceImp")
public class AuditLogServiceImpl implements AuditLogService{
	
	private static final Log LOG = LogFactory.getLog(AuditLogServiceImpl.class);
	
	@Autowired
	private AuditLogDAO auditLogDAO;

	@Override
	public void insert(AuditLog auditLog) {
		LOG.debug("insert - start");
		auditLogDAO.insert(auditLog);
		LOG.debug("insert - end");
	}

}
