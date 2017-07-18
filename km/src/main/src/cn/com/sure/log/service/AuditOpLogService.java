package cn.com.sure.log.service;

import java.util.Date;
import java.util.List;

import cn.com.sure.log.entry.AuditOpLog;

public interface AuditOpLogService {
	
	
	/**
	 * 
	 * @param auditOpLog
	 */
	void insert(long type,String action,String actionExt1,String actionExt2,String actionExt3,String actionExt4,
			String  message,Date timestamp,String ip,String  operator,Integer isOpSucc);
	

	/**
	 * @return
	 */
	List<AuditOpLog> selectAll();


	/**
	 * @param auditOpLog
	 * @return
	 */
	List<AuditOpLog> searchByCondition(AuditOpLog auditOpLog);
	
}
