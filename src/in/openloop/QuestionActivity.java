package in.openloop;
import in.openloop.db.TournamentDbAdapter;
import in.openloop.db.model.Answer;
import in.openloop.db.model.Question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QuestionActivity  extends Activity{
	
	
	private TournamentDbAdapter mDbAdapter;
	EditText editText;
	ListIterator<Question> mQuestionIterator;
	private boolean mNext = true;
	private List<Answer> answers = new ArrayList<Answer>();
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
		int subjectId = i.getIntExtra("subject_id",0);
		List<Question> questions = mDbAdapter.getAllQuestions(subjectId);
		mQuestionIterator = questions.listIterator();
		if(mQuestionIterator.hasNext()){
			displayQuestion(mQuestionIterator.next());
			mNext = true;
		}
		
		
		Button btnFinish = (Button)findViewById(R.id.finishButton);
	
		btnFinish.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				finish();
				
				 Intent i = new Intent(getApplicationContext(), ScoreCardActivity.class);
				 i.putExtra("score", Answer.evaluateScore(answers));
				 
			     startActivity(i);
			}
		});
	
		
		Button nextButton = (Button)findViewById(R.id.nextButton);
		Button prevButton = (Button)findViewById(R.id.prevButton);
		
		nextButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				answers.add(getAnswer(currentQuestion));
				
//				Toast.makeText(QuestionActivity.this,
//						id, Toast.LENGTH_SHORT).show();
			
				if(mQuestionIterator.hasNext()){
					if(!mNext){
						displayQuestion(mQuestionIterator.next());
					}
					displayQuestion(mQuestionIterator.next());
					
					mNext = true;
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
