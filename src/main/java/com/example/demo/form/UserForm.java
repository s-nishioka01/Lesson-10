package com.example.demo.form;

public class UserForm {

	private int id;

	private String name;

	public UserForm(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public UserForm() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
