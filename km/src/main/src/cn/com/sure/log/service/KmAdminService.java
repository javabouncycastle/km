/**
 * 
 */
package cn.com.sure.log.service;

import cn.com.sure.admin.entry.KmAdmin;

/**
 * @author Limin
 *
 */
public interface KmAdminService {

	/**
	 * @param adminAuthNum
	 * @return
	 */
	KmAdmin fingByAdmId(int adminAuthNum);
	
	

}
