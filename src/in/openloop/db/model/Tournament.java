package in.openloop.db.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Tournament {

	private int mId;
	private String mName;
	private Subject mSubject;
	private List<Question> mQuestions = new ArrayList<Question>(); 
	
	
	public void setId(int id){
		mId = id;
	}
	public int getId(){
		return mId;
	}
	
	public String getName(){
		return mName;
	}
	
	public void setName(String name){
		mName = name;
	}
	public Subject getSubject(){
		return mSubject;
	}
	
	public void setSubject(Subject subject){
		mSubject = subject;
	}
	
	public void addQuestion(Question question){
		mQuestions.add(question);
	}
	
	public void addQuestions(Collection<Question> questions){
		mQuestions.addAll(questions);
	}
	
	public List<Question> getQuestions(){
		return mQuestions;
	}
	
}
