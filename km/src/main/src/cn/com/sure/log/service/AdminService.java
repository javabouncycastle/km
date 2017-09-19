/**
 * 
 */
package cn.com.sure.log.service;

import cn.com.sure.admin.entry.Admin;

/**
 * @author Limin
 *
 */
public interface AdminService {

	/**
	 * @param adminAuthNum
	 * @return
	 */
	Admin fingByAdmId(int adminAuthNum);
	
	

}
