/**
 * 
 */
package cn.com.sure.log.dao;

import cn.com.sure.log.Admin;

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
