package in.openloop.db.model;

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
	
	public static int evaluateScore(List<Answer> answers){
		int score=0;
		
		for(Answer answer: answers){
			if(answer.mAnswerChoice == answer.mUserChoice){
				score++;
			}
		}
		return score;
	}

}
