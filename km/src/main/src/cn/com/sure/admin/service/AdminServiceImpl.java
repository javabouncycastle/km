/**
 * 
 */
package cn.com.sure.admin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Limin
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("adminService")
public class AdminServiceImpl implements AdminService{


	@Override
	public String obtAdmCert(int adminAuthNum) {
		// TODO Auto-generated method stub
		return null;
	}

}
