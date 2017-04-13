package cn.com.sure.ssm.service;

import java.util.List;

import cn.com.sure.ssm.entry.User;

public interface UserService {
	
	void insert(User user);
	void remove(int id);
	void update(User user);
	List<User> find();

}
