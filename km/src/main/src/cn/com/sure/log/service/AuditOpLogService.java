package cn.com.sure.log.service;

import cn.com.sure.log.entry.AuditOpLog;

public interface AuditOpLogService {

	void insert(AuditOpLog auditOpLog);
	
}
