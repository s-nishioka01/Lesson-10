package com.example.demo.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.User;
import com.example.demo.form.UserForm;

@Mapper
public interface UserMapper {

	List<User> findAll();

	Optional<User> findOne(int id);

	void save(UserForm userForm);

	void update(UserForm userForm);

	void delete(int id);
}