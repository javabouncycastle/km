package cn.com.sure.log.service;

import java.util.Date;
import java.util.List;

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


	/* (non-Javadoc)
	 * @see cn.com.sure.log.service.AuditOpLogService#selectAll()
	 */
	@Override
	public List<AuditOpLog> selectAll() {
		LOG.debug("selectAll - start");
		List<AuditOpLog> auditOpLogs = auditOpLogDAO.selectAll();
		LOG.debug("selectAll - end");
		return auditOpLogs;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.log.service.AuditOpLogService#searchByCondition(cn.com.sure.log.entry.AuditOpLog)
	 */
	@Override
	public List<AuditOpLog> searchByCondition(AuditOpLog auditOpLog) {
		LOG.debug("searchByCondition - start");
		List<AuditOpLog> auditOpLogs = auditOpLogDAO.searchByCondition(auditOpLog);
		LOG.debug("searchByCondition - start");
		return auditOpLogs;
	}



	/**
	 * 插入日志信息
	 */
	@Override
	public void insert(long type, String action, String actionExt1,
			String actionExt2, String actionExt3, String actionExt4,
			String message, Date timestamp, String ip, String operator,
			Integer isOpSucc) {
		LOG.debug("insert - start");
		AuditOpLog auditOpLog = new AuditOpLog();
		auditOpLog.setType(type);
		auditOpLog.setAction(action);
		auditOpLog.setActionExt1(actionExt1);
		auditOpLog.setActionExt2(actionExt2);
		auditOpLog.setActionExt3(actionExt3);
		auditOpLog.setActionExt4(actionExt4);
		auditOpLog.setMessage(message);
		auditOpLog.setTimestamp(timestamp);
		auditOpLog.setIp(ip);
		auditOpLog.setOperator(operator);
		auditOpLog.setIsOpSucc(isOpSucc);
		
		auditOpLogDAO.insert(auditOpLog);
		
		LOG.debug("insert - end");
	}

}
