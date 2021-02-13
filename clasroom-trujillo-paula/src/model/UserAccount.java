package model;

import java.io.File;

import javafx.scene.image.Image;

public class UserAccount {
	
	private String username;
	private String password;
	private Image photo;
	private String gender;
	private String birthday;
	private String career;
	private String browser;

	public UserAccount(String user, String pass, String photo, String gender, String career, String birth, String browser) {
		this.username=user;
		this.password=pass;
		File file = new File(photo);
		this.photo= new Image(file.toURI().toString());
		this.gender=gender;
		this.career=career;
		this.birthday=birth;
		this.browser=browser;
	}
	
	public String getUsername() {
		return username;
	}

	public Image getPhoto() {
		return photo;
	}
	
	public String getPassword() {
		return password;
	}

	public String getGender() {
		return gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getBrowser() {
		return browser;
	}

	public String getCareer() {
		return career;
	}
}
