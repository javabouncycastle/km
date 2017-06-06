/**
 * 
 */
package cn.com.sure.keypair.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.common.KmConstants;
import cn.com.sure.keypair.dao.KpgTaskDAO;
import cn.com.sure.keypair.entry.KpgTask;
import cn.com.sure.km.KmApplicationexception;
import cn.com.sure.km.KmErrorMessageConstants;
import cn.com.sure.syscode.entry.SysCode;

/**
 * @author Limin
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("kpgTaskService")
public class KpgTaskServiceImpl implements KpgTaskService{
	
	private static final Log LOG = LogFactory.getLog(KpgTaskServiceImpl.class);
	
	@Autowired
	private KpgTaskDAO kpgTaskDAO;

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KpgTaskService#selectAll()
	 */
	@Override
	public List<KpgTask> selectAll() {
		LOG.debug("selectAll - start");
		List<KpgTask>kpgTasks=this.kpgTaskDAO.selectAll();
		LOG.debug("selectAll - end");
		return kpgTasks;
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KpgTaskService#insert(cn.com.sure.keypair.entry.KpgTask)
	 */
	@Override
	public void insert(KpgTask kpgTask) throws KmApplicationexception {
		LOG.debug("insert - start");
		KpgTask dbKpgTask = this.kpgTaskDAO.findByName(kpgTask.getName());
		if(dbKpgTask==null){
			SysCode taskStatus=new SysCode();
			taskStatus.setParaValue((KmConstants.CODE_ID_TASK_STATUS_NOT_STARTED).toString());
			kpgTask.setTaskStatus(taskStatus);
			this.kpgTaskDAO.insert(kpgTask);
		}if(dbKpgTask!=null){
			KmApplicationexception.throwException(KmErrorMessageConstants.kpgTaskNameExist, new String[]{kpgTask.getName()});
		}
		LOG.debug("insert - end");
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KpgTaskService#update(cn.com.sure.keypair.entry.KpgTask)
	 */
	@Override
	public void update(KpgTask kpgTask) {
		LOG.debug("update -start");
		this.kpgTaskDAO.update(kpgTask);
		LOG.debug("update - end");
	}

	/* (non-Javadoc)
	 * @see cn.com.sure.keypair.service.KpgTaskService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		LOG.debug("delete - start");
		this.kpgTaskDAO.delete(id);
		LOG.debug("delete - end");
	}

}
