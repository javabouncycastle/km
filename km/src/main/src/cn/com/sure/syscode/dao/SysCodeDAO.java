package cn.com.sure.syscode.dao;

import java.util.List;

import cn.com.sure.syscode.entry.SysCode;

public interface SysCodeDAO {

	public void insert(SysCode sysCode);

	public SysCode findByName(SysCode sysCode);

	public List<SysCode> selectAll(SysCode sysCode);

	public void delete(Long id);

	public SysCode findById(Long id);

	public void updateValid(SysCode sysCode);

	public void update(SysCode sysCode);

}
