package cn.com.sure.syscode.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.common.KmConstants;
import cn.com.sure.km.KmApplicationexception;
import cn.com.sure.km.KmErrorMessageConstants;
import cn.com.sure.syscode.dao.SysCodeDAO;
import cn.com.sure.syscode.entry.SysCode;
import cn.com.sure.syscode.entry.SysCodeType;

@Transactional(propagation = Propagation.REQUIRED)
@Service("SysCodeService")
public class SysCodeServiceImpl implements SysCodeService{
	
	private static final Log LOG = LogFactory.getLog(SysCodeServiceImpl.class);
	
	@Autowired
	private SysCodeDAO sysCodeDAO;
	

	@Override
	public int insert(SysCode sysCode, HttpServletRequest request)
			throws KmApplicationexception {
		LOG.debug("insert - start");
		SysCode dbSysCode = sysCodeDAO.findByName(sysCode);
		int i = 0 ;
		if(dbSysCode==null){
			i =sysCodeDAO.insert(sysCode);
		}if(dbSysCode!=null){
			KmApplicationexception.throwException(KmErrorMessageConstants.paraValueExist, new String[]{sysCode.getParaValue()});
		}
		
		LOG.debug("insert - end");
		return i;
	}

	@Override
	public int update(SysCode sysCode) {
		LOG.debug("update - start");
		int i = sysCodeDAO.update(sysCode);
		LOG.debug("update - end");
		return i;
	}

	@Override
	public int remove(Long id) {
		LOG.debug("remove - start");
		int i = sysCodeDAO.delete(id);
		LOG.debug("remove - end");
		return i;
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

	@Override
	public List<SysCode> selectByType(SysCode sysCode) {
		LOG.debug("selectByType - start");
		SysCodeType sysCodeType = new SysCodeType();
		sysCodeType.setParaType(KmConstants.TYPE_ID_TASK_STATUS);
		sysCode.setParaType(sysCodeType);
		sysCode.setIsValid(KmConstants.YES_OR_NO_OPTION_YES);
		List<SysCode> sysCodes = this.sysCodeDAO.findByType(sysCode);
		LOG.debug("selectByType - end");
		return sysCodes;
	}

	@Override
	public List<SysCode> searchByCondition(SysCode sysCode) {
		LOG.debug("searchByCondition - start");
		List<SysCode> sysCodes = sysCodeDAO.serachByContion(sysCode);
		LOG.debug("searchByCondition - end");
		return sysCodes;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.syscode.service.SysCodeService#selectById(java.lang.Long)
	 */
	@Override
	public SysCode selectById(Long id) {
		LOG.debug("selectById - start");
		SysCode sysCodes = sysCodeDAO.findById(id);
		LOG.debug("selectById - end");
		return sysCodes;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.syscode.service.SysCodeService#selectMin()
	 */
	@Override
	public String selectMin() {
		LOG.debug("selectMin - start");
		SysCodeType sysCodeType = new SysCodeType();
		sysCodeType.setParaType(KmConstants.KEY_NUM_MIN);
		SysCode sysCode = new SysCode();
		sysCode.setParaType(sysCodeType);
		String min = sysCodeDAO.selectMin(sysCode);
		LOG.debug("selectMin - end");
		return min;
	}


}
