package cn.com.sure.syscode.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.com.sure.km.KmApplicationexception;
import cn.com.sure.syscode.entry.SysCode;

public interface SysCodeService {

	void insert(SysCode sysCode, HttpServletRequest request)throws  KmApplicationexception ;

	SysCode find(Long id);

	void update(SysCode sysCode);

	void remove(Long id);

	void suspend(Long id);

	void activate(Long id);

	List<SysCode> selectAll(SysCode sysCode);
	
	List<SysCode> selectByType(SysCode sysCode);

	/**
	 * @param sysCode
	 * @return 
	 */
	List<SysCode> searchByCondition(SysCode sysCode);

}
