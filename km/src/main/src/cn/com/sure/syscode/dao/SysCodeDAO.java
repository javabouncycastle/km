package cn.com.sure.syscode.dao;

import java.util.List;

import cn.com.sure.syscode.entry.SysCode;

public interface SysCodeDAO {

	public int insert(SysCode sysCode);

	public SysCode findByName(SysCode sysCode);

	public List<SysCode> selectAll(SysCode sysCode);

	public int delete(Long id);

	public SysCode findById(Long id);

	public void updateValid(SysCode sysCode);

	public int update(SysCode sysCode);
	
	public List<SysCode> findByType(SysCode sysCode);

	/**
	 * @param sysCode
	 * @return 
	 */
	public List<SysCode> serachByContion(SysCode sysCode);

	/**
	 * @param algorithmName
	 * @return
	 */
	public int countNum(String algorithmName);

	/**
	 * @param sysCode
	 * @return
	 */
	public List<SysCode> selectMin(SysCode sysCode);

	/**
	 * @param sysCode
	 * @return
	 */
	public List<SysCode> selectBuffer(SysCode sysCode);

	/**
	 * @param sysCode
	 * @return
	 */
	public List<SysCode> selectBufSize(SysCode sysCode);


}
