package in.openloop.db.model;

public class User {

	private long mId;
	private String mUserName;
	private String mEmail;
	
	public long getId(){
		return mId;
	}
	
	public void setId(long id){
		mId = id;
	}
	
	public String getUserName(){
		return mUserName;
	}
	
	public String getEmail(){
		return mEmail;
	}
	
	public void setUserName(String username){
		mUserName = username;
	}
	
	public void setEmail(String email){
		mEmail = email;
	}
}
