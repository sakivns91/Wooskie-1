package com.vikasyadav.database;

public class User {

	int id;
	String name;
	String username;
	int score;
	public User(String username,String name,int score)
	{
		this.name=name;
		this.username=username;
		this.score=score;
	}
	public User(int id,String username,String name,int score)
	{
		this.id=id;
		this.name=name;
		this.username=username;
		this.score=score;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
