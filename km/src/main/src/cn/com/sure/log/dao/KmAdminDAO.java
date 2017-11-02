/**
 * 
 */
package cn.com.sure.log.dao;

import cn.com.sure.admin.entry.KmAdmin;

/**
 * @author Limin
 *
 */
public interface KmAdminDAO {

	/**
	 * @param adminAuthNum
	 * @return
	 */
	KmAdmin fingByAdmId(int adminAuthNum);

}
