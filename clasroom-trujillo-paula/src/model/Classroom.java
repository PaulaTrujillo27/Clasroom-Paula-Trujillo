package model;

import java.util.ArrayList;
import java.util.List;



public class Classroom {
	
	private List<UserAccount> Accounts;
	
	public Classroom() {
		Accounts=new ArrayList<>();
	}
	
	public void addUserAccount(String user, String pass, String photo, String gender, String career, String birth, String browser) {
		Accounts.add(new UserAccount(user, pass,  photo,  gender,  career,  birth,  browser));
	}
	
	public List<UserAccount> getAccounts(){
		return Accounts;
	}
}