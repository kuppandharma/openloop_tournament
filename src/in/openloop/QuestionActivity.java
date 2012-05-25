package in.openloop;
import in.openloop.db.TournamentDbAdapter;
import in.openloop.db.model.Question;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import android.app.Activity;
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
	
	}
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question);
		
		mDbAdapter = new TournamentDbAdapter(this);
		mDbAdapter.open();
		
		List<Question> questions = mDbAdapter.getAllQuestions(0);
		mQuestionIterator = questions.listIterator();
		if(mQuestionIterator.hasNext()){
			displayQuestion(mQuestionIterator.next());
			mNext = true;
		}
		
		
		Button btnFinish = (Button)findViewById(R.id.finishButton);
	
		btnFinish.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				finish();
			}
		});
	
		
		Button nextButton = (Button)findViewById(R.id.nextButton);
		Button prevButton = (Button)findViewById(R.id.prevButton);
		
		nextButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				RadioGroup group = (RadioGroup)findViewById(R.id.answerChoices);
				int id = group.getCheckedRadioButtonId();
				RadioButton radioButton = (RadioButton)findViewById(id);
				
//				Toast.makeText(QuestionActivity.this,
//						radioButton.getText(), Toast.LENGTH_SHORT).show();
//			
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

}
