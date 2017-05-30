/**
 * 
 */
package cn.com.sure.syscode.dao;

import java.util.List;

import cn.com.sure.syscode.entry.SysCodeType;

/**
 * @author Limin
 *
 */
public interface SysCodeTypeDAO {

	/**
	 * @param paraType
	 * @return 
	 */
	public SysCodeType findByType(String paraType);

	/**
	 * @param sysCodeType
	 */
	public void insert(SysCodeType sysCodeType);

	/**
	 * @param sysCodeType
	 */
	public void update(SysCodeType sysCodeType);

	/**
	 * @param sysCodeType
	 */
	public List<SysCodeType> selectAll(SysCodeType sysCodeType);

	/**
	 * @param id
	 */
	public void delete(Long id);
}
