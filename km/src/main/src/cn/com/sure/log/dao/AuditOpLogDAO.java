package cn.com.sure.log.dao;

import cn.com.sure.log.entry.AuditOpLog;

public interface AuditOpLogDAO {

	void insert(AuditOpLog auditOpLog);
	
	

}
