/**
 * 
 */
package cn.com.sure.syscode.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.km.KmApplicationexception;
import cn.com.sure.km.KmErrorMessageConstants;
import cn.com.sure.syscode.dao.SysCodeTypeDAO;
import cn.com.sure.syscode.entry.SysCodeType;

/**
 * @author Limin
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("SysCodeTypeService")
public class SysCodeTypeServiceImpl implements SysCodeTypeService {
	
	private static final Log LOG = LogFactory.getLog(SysCodeTypeServiceImpl.class);
	
	@Autowired
	private SysCodeTypeDAO sysCodeTypeDAO;

	/* (non-Javadoc)
	 * @see cn.com.sure.syscode.service.SysCodeTypeService#insert(cn.com.sure.syscode.entry.SysCodeType)
	 */
	@Override
	public void insert(SysCodeType sysCodeType) throws KmApplicationexception {
		LOG.debug("insert - start");
		SysCodeType dbSysCodeType = sysCodeTypeDAO.findByType(sysCodeType.getParaType());
		if(dbSysCodeType==null){
			this.sysCodeTypeDAO.insert(sysCodeType);
		}if(dbSysCodeType!=null){
			KmApplicationexception.throwException(KmErrorMessageConstants.paraTypeValueExist, new String[]{sysCodeType.getParaType()});
		}
		LOG.debug("insert - start");
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.syscode.service.SysCodeTypeService#update(cn.com.sure.syscode.entry.SysCodeType)
	 */
	@Override
	public void update(SysCodeType sysCodeType) {
		LOG.debug("update - start");
		this.sysCodeTypeDAO.update(sysCodeType);
		LOG.debug("update - start");
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.syscode.service.SysCodeTypeService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		LOG.debug("delete - start");
		this.sysCodeTypeDAO.delete(id);
		LOG.debug("delete - start");
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.syscode.service.SysCodeTypeService#selectAll(cn.com.sure.syscode.entry.SysCodeType)
	 */
	@Override
	public List<SysCodeType> selectAll(SysCodeType sysCodeType) {
		LOG.debug("selectAll - start");
		List<SysCodeType> sysCodeTypes = sysCodeTypeDAO.selectAll(sysCodeType);
		LOG.debug("selectAll - end");
		return sysCodeTypes;
	}

}
