package in.openloop.db.model;

public class Subject {

	private int mId;
	private String mSubjectName;
	
	
	public Subject(){}

	public Subject(int id, String subjectName){
		this.mId = id;
		this.mSubjectName = subjectName;
	}
	
	public String getSubjectName(){
		return mSubjectName;
	}
	
	public int getSubjectId(){
		return mId;
	}
	
	public void setSubjectName(String name){
		this.mSubjectName = name;
	}
	
	public void setSubjectId(int id){
		this.mId = id;
	}
	
	public boolean equals(Object obj){
		if(obj instanceof Subject){
			Subject that = (Subject)obj;
			return that.mSubjectName.equals(mSubjectName);
		}
		return true;
	}
	
	public int hashCode(){
		return mSubjectName.hashCode();
	}
}
