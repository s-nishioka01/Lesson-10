package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.form.UserForm;
import com.example.demo.mapper.UserMapper;

@Service
public class UserService {

	private final UserMapper userMapper;

	@Autowired
	public UserService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public List<User> getUserList() {
		return userMapper.findAll();
	}

	public User findOne(int id) throws Exception {
		return userMapper.findOne(id).orElseThrow(() -> new Exception("データが登録されていません"));
	}

	public void saveUserList(UserForm userForm) {
		userMapper.save(userForm);
	}

	public void updateUserList(UserForm userForm) {
		userMapper.update(userForm);
	}

	public void deleteUserList(int id) {
		userMapper.delete(id);
	}

}