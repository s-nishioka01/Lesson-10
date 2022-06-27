package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.UserForm;
import com.example.demo.service.UserService;

@Controller
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("userList", userService.getUserList());
		return "index";
	}

	@GetMapping("/new")
	public String newUser(Model model) {
		model.addAttribute("error", "データが登録されていません");
		return "new";
	}

	@GetMapping("/update/{id}")
	public String updateUser(@PathVariable int id, Model model) {
		try {
			model.addAttribute("user", userService.findOne(id));
			return "update";

		} catch (Exception e) {
			model.addAttribute("userList", userService.getUserList());
			model.addAttribute("error", "データが登録されていません");
			return "index";
		}

	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable int id, Model model) {
		try {
			model.addAttribute("user", userService.findOne(id));
			return "delete";

		} catch (Exception e) {
			model.addAttribute("userList", userService.getUserList());
			model.addAttribute("error", "データが登録されていません");
			return "index";
		}

	}

	@PostMapping("/new")
	public String create(@Validated @ModelAttribute UserForm userForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "new";
		}
		userService.saveUserList(userForm);
		return "redirect:/index";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable int id, @Validated @ModelAttribute UserForm userForm, BindingResult result,
			Model model) throws Exception {
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("user", userService.findOne(id));
			model.addAttribute("validationError", errorList);
			return "update";
		}
		userForm.setId(id);
		userService.updateUserList(userForm);
		return "redirect:/index";
	}

	@PostMapping("/delete/{id}")
	public String destroy(@PathVariable int id) {
		userService.deleteUserList(id);
		return "redirect:/index";
	}
}