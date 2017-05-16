package cn.com.sure.syscode.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.common.KmConstants;
import cn.com.sure.km.KmApplicationexception;
import cn.com.sure.km.KmErrorMessageConstants;
import cn.com.sure.syscode.dao.SysCodeDAO;
import cn.com.sure.syscode.entry.SysCode;

@Transactional(propagation = Propagation.REQUIRED)
@Service("SysCodeService")
public class SysCodeServiceImpl implements SysCodeService{
	
	private static final Log LOG = LogFactory.getLog(SysCodeServiceImpl.class);
	
	@Autowired
	private SysCodeDAO sysCodeDAO;

	@Override
	public void insert(SysCode sysCode, HttpServletRequest request)
			throws KmApplicationexception {
		LOG.debug("insert - start");
		SysCode dbSysCode = sysCodeDAO.findByName(sysCode);
		if(dbSysCode==null){
			sysCodeDAO.insert(sysCode);
		}if(dbSysCode!=null){
			KmApplicationexception.throwException(KmErrorMessageConstants.paraValueExist, new String[]{sysCode.getParaValue()});
		}
		
		LOG.debug("insert - end");
	}

	@Override
	public SysCode find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(SysCode sysCode) {
		LOG.debug("update - start");
		this.sysCodeDAO.update(sysCode);
		LOG.debug("update - end");
	}

	@Override
	public void remove(Long id) {
		LOG.debug("remove - start");
		this.sysCodeDAO.delete(id);
		LOG.debug("remove - end");
	}

	@Override
	public void suspend(Long id) {
		LOG.debug("suspend - start");
		SysCode sysCode = sysCodeDAO.findById(id);
		sysCode.setIsValid(KmConstants.YES_OR_NO_OPTION_NO);
		sysCodeDAO.updateValid(sysCode);
		LOG.debug("suspend - end");
	}

	@Override
	public void activate(Long id) {
		LOG.debug("activate - start");
		SysCode sysCode = sysCodeDAO.findById(id);
		sysCode.setIsValid(KmConstants.YES_OR_NO_OPTION_YES);
		sysCodeDAO.updateValid(sysCode);
		LOG.debug("activate - start");
	}

	@Override
	public List<SysCode> selectAll(SysCode sysCode) {
		LOG.debug("selectAll - start");
		List<SysCode> sysCodes = sysCodeDAO.selectAll(sysCode);
		LOG.debug("selectAll - end");
		return sysCodes;
	}

}
