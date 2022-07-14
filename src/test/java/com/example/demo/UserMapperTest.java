package com.example.demo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.example.demo.entity.User;
import com.example.demo.form.UserForm;
import com.example.demo.mapper.UserMapper;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;

@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserMapperTest {

	@Autowired
	UserMapper userMapper;

	@Test
	@DataSet(value = "users.yml")
	void すべてのユーザーが取得できること() {
		List<User> actual = userMapper.findAll();
		assertEquals(3, actual.size());
	}

	@Test
	@DataSet("users.yml")
	void 選択したユーザーが取得できること() {
		User actual = userMapper.findOne(1).get();
		assertEquals(1, actual.getId());
		assertEquals("Harry Potter", actual.getName());
	}

	@Test
	@DataSet(value = "users.yml")
	@ExpectedDataSet(value = "expectedNew.yml", ignoreCols = "id")
	void ユーザーの新規登録ができること() {
		UserForm user = new UserForm(4, "Draco Malfoy");
		userMapper.save(user);
	}

	@Test
	@DataSet("users.yml")
	@ExpectedDataSet(value = "expectedEdit.yml", ignoreCols = "id")
	void ユーザーの編集ができること() {
		UserForm user = new UserForm(1, "Albus Dumbledore");
		userMapper.update(user);
		User actual = userMapper.findOne(1).get();
		assertEquals(1, actual.getId());
		assertEquals("Albus Dumbledore", actual.getName());
	}

	@Test
	@DataSet("users.yml")
	@ExpectedDataSet(value = "expectedDelete.yml", ignoreCols = "id")
	void ユーザーの削除ができること() {
		userMapper.delete(1);
		List<User> actual = userMapper.findAll();
		assertEquals(2, actual.size());
	}
}
