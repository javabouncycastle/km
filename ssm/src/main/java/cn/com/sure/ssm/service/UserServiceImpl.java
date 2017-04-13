package cn.com.sure.ssm.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sure.ssm.dao.UserDAO;
import cn.com.sure.ssm.entry.User;

@Transactional()
@Service(value="UserService")
public class UserServiceImpl implements UserService{
	
	private static final Log LOG = LogFactory.getLog(UserServiceImpl.class);
	
	@Resource
	private UserDAO userDAO;

	@Override
	public void insert(User user) {
		LOG.debug("insert - start");
		this.userDAO.insert(user);
		LOG.debug("insert - end");
	}

	@Override
	public void remove(int id) {
		LOG.debug("remove - start");
		this.userDAO.delete(id);
		LOG.debug("remove - end");
	}

	@Override
	public void update(User user) {
		LOG.debug("update - start");
		this.userDAO.update(user);
		LOG.debug("update - end");
	}

	@Override
	public List<User> find() {
		LOG.debug("update - start");
		List<User> users=this.userDAO.select();
		LOG.debug("update - end");
		return users;
	}

}
