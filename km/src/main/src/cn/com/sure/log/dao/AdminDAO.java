/**
 * 
 */
package cn.com.sure.log.dao;

import cn.com.sure.admin.entry.Admin;

/**
 * @author Limin
 *
 */
public interface AdminDAO {

	/**
	 * @param adminAuthNum
	 * @return
	 */
	Admin fingByAdmId(int adminAuthNum);

}
