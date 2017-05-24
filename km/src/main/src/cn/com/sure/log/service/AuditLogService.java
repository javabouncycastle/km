package cn.com.sure.log.service;

import cn.com.sure.log.entry.AuditLog;

public interface AuditLogService {

	void insert(AuditLog auditLog);
	
}
