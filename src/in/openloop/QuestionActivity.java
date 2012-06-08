package in.openloop;
import in.openloop.db.TournamentDbAdapter;
import in.openloop.db.model.Answer;
import in.openloop.db.model.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class QuestionActivity  extends Activity{
	
	private static final String TAG="QuestionActivity";
	
	private TournamentDbAdapter mDbAdapter;
	EditText editText;
	ListIterator<Question> mQuestionIterator;
	private boolean mNext = true;
	private HashMap<Question, Answer> answerMap = new HashMap<Question,Answer>();
	
	private Question currentQuestion;
	
	private void displayQuestion(Question question){
	
		editText = (EditText)findViewById(R.id.questionTxt);
		//editText.setEnabled(false);
		editText.setText(question.getQuestionText());
		editText.setInputType(InputType.TYPE_NULL);
	
		RadioButton choiceA = (RadioButton)findViewById(R.id.choiceA);
		RadioButton choiceB = (RadioButton)findViewById(R.id.choiceB);
		RadioButton choiceC = (RadioButton)findViewById(R.id.choiceC);
		RadioButton choiceD = (RadioButton)findViewById(R.id.choiceD);
		
		String choices[] = question.getChoices();
		choiceA.setText(choices[0]);
		choiceB.setText(choices[1]);
		choiceC.setText(choices[2]);
		choiceD.setText(choices[3]);
	
		currentQuestion = question;
	}
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question);
		
		mDbAdapter = new TournamentDbAdapter(this);
		mDbAdapter.open();
		
		Intent i = getIntent();
		int tournamentId = i.getIntExtra("tournament_id",0);
		List<Question> questions = mDbAdapter.getAllTournamentQuestions(tournamentId);
		
		Log.w(TAG, "Question Size" + questions.size());
        
		questions = mDbAdapter.fillQuestions(questions);
		
		Log.w(TAG, "Question Size Filled" + questions.size());
        
		
		mQuestionIterator = questions.listIterator();
		if(mQuestionIterator.hasNext()){
			displayQuestion(mQuestionIterator.next());
			mNext = true;
		}
		
		
		Button btnFinish = (Button)findViewById(R.id.finishButton);
	
		btnFinish.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				finish();
				 
				 Intent prevIntent = getIntent();
				 
				 Intent i = new Intent(getApplicationContext(), ScoreCardActivity.class);
				 Bundle extras = new Bundle();
				 extras.putInt("total",  answerMap.size());
				 extras.putInt("score", Answer.evaluateScore(answerMap.values()));
				 extras.putInt("tournament_id", prevIntent.getIntExtra("tournament_id", 1));
				 i.putExtras(extras);
				
			     startActivity(i);
			}
		});
	
		
		Button nextButton = (Button)findViewById(R.id.nextButton);
		Button prevButton = (Button)findViewById(R.id.prevButton);
		
		nextButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				answerMap.put(currentQuestion, getAnswer(currentQuestion));
				
//				Toast.makeText(QuestionActivity.this,
//						id, Toast.LENGTH_SHORT).show();
			
				if(mQuestionIterator.hasNext()){
					if(!mNext){
						displayQuestion(mQuestionIterator.next());
					}
					displayQuestion(mQuestionIterator.next());
					
					mNext = true;
				}else{
					finish();
					
					Intent prevIntent = getIntent();
					Intent i = new Intent(getApplicationContext(), ScoreCardActivity.class);
					i.putExtra("score", Answer.evaluateScore(answerMap.values()));
					i.putExtra("total", answerMap.size());
					i.putExtra("tournament_id", prevIntent.getIntExtra("tournament_id", -1));
					startActivity(i);
				}
			}
		});
	
		prevButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
			
				if(mQuestionIterator.hasPrevious()){
					if(mNext){
						displayQuestion(mQuestionIterator.previous());
					}
					displayQuestion(mQuestionIterator.previous());
					
					mNext = false;
				}else{
					Toast.makeText(QuestionActivity.this,
					"No Previous Question", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	private Answer getAnswer(Question question){
		Answer answer = new Answer();
		RadioButton []choices = new RadioButton[4];
		choices[0] = (RadioButton)findViewById(R.id.choiceA);
		choices[1] = (RadioButton)findViewById(R.id.choiceB);
		choices[2] = (RadioButton)findViewById(R.id.choiceC);
		choices[3] = (RadioButton)findViewById(R.id.choiceD);
		
		for(int i=0;i<4; i++){
			if(choices[i].isChecked()){;
			answer.setUserChoice(i);
			break;
			}
		}
		answer.setAnswerChoice(question.getAnswerCode());
		
		return answer;
	}

}
