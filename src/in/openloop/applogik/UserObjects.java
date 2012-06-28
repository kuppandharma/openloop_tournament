package in.openloop.applogik;

import in.openloop.db.model.User;

public class UserObjects {
	
	private static final UserObjects  userObjects = new UserObjects();
	
	public UserObjects(){};
	
	private User mLoggedInUser;
	
	public User getUser(){
		return mLoggedInUser;
	}
	
	public void setUser(User user){
		mLoggedInUser = user;
	}
	
	public static UserObjects getUserObjects(){
		return userObjects;
	}

}
