package in.openloop.db.model;

import java.util.Collection;
import java.util.List;

public class Answer {
	
	private int mAnswerChoice;
	private int mUserChoice;
	
	
	public void setUserChoice(int index){
		mUserChoice = index;
	}

	public void setAnswerChoice(int index){
		mAnswerChoice = index;
	}
	
	public static int evaluateScore(Collection<Answer> answers){
		int score=0;
		
		for(Answer answer: answers){
			if(answer.mAnswerChoice == answer.mUserChoice){
				score++;
			}
		}
		return score;
	}

}
