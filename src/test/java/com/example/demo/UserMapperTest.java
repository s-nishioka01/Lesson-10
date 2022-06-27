package com.example.demo;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.User;
import com.example.demo.form.UserForm;
import com.example.demo.mapper.UserMapper;

class UserMapperTest {

	@Autowired
	UserMapper userMapper;

	@Test
	void すべてのユーザーが取得できること() {
		List<User> users = userMapper.findAll();
		assertThat(users).hasSize(3).contains(new User(1, "Harry Potter"), new User(2, "Ron Weasley"),
				new User(3, "Hermione Granger"));
	}

	@Test
	void 選択したユーザーが取得できること() {
		Optional<User> user = userMapper.findOne(1);
		assertThat(user).contains(new User(1, "Harry Potter"));
	}

	@Test
	void ユーザーの新規登録ができること() {
		UserForm user = new UserForm(4, "Draco Malfoy");
		userMapper.save(user);
	}

	@Test
	void ユーザーの編集ができること() {
		UserForm user = new UserForm(1, "Harry Potter");
		userMapper.update(user);

	}

	@Test
	void ユーザーの削除ができること() {
		userMapper.delete(1);
	}
}