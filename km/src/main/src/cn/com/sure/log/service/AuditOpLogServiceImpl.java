package cn.com.sure.log.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.log.dao.AuditOpLogDAO;
import cn.com.sure.log.entry.AuditOpLog;

@Transactional(propagation = Propagation.REQUIRED)
@Service(value="auditOpLogService")
public class AuditOpLogServiceImpl implements AuditOpLogService{
	
	private static final Log LOG = LogFactory.getLog(AuditOpLogServiceImpl.class);
	
	@Autowired
	private AuditOpLogDAO auditOpLogDAO;

	@Override
	public void insert(AuditOpLog auditOpLog){
		LOG.debug("insert - start");
		auditOpLogDAO.insert(auditOpLog);
		LOG.debug("insert - end");
	}

}
