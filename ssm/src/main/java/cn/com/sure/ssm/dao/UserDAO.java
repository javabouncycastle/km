package cn.com.sure.ssm.dao;


import java.util.List;

import cn.com.sure.ssm.entry.User;

public interface UserDAO {
	 public void insert(User user);
	 public void delete(int id);
	 public void update(User user);
	 public List<User> select();
}
