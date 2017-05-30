/**
 * 
 */
package cn.com.sure.syscode.service;

import java.util.List;

import cn.com.sure.km.KmApplicationexception;
import cn.com.sure.syscode.entry.SysCodeType;

/**
 * @author Limin
 *
 */
public interface SysCodeTypeService {

	/**
	 * @param sysCodeType
	 */
	void insert(SysCodeType sysCodeType)throws KmApplicationexception;

	/**
	 * @param sysCodeType
	 */
	void update(SysCodeType sysCodeType);

	/**
	 * @param id
	 */
	void delete(Long id);

	/**
	 * @param sysCodeType
	 * @return
	 */
	List<SysCodeType> selectAll(SysCodeType sysCodeType);
	
}
