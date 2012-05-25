package in.openloop;

import in.openloop.db.TournamentDbAdapter;
import in.openloop.db.model.Question;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class QuestionBankActivity extends Activity {

	TournamentDbAdapter mDbAdapter;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_bank);
		
		mDbAdapter = new TournamentDbAdapter(this);
		mDbAdapter.open();
		
		Button buttonClearQuestions = (Button)findViewById(R.id.btnClearQuestions);
		Button buttonImportQuestions = (Button)findViewById(R.id.btnImportQuestions);
		Button buttonFinish = (Button)findViewById(R.id.finishButton);
		
		
		buttonClearQuestions.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				mDbAdapter.clearQuestions();
			}
		});
	
		buttonImportQuestions.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				createQuestions();
			}
		});
		
		buttonFinish.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				finish();
			}
		});

	}
	
	private void createQuestions(){
		
		Question question = new Question("What is 1 + 1", 3, new String[]{"1","3","4","2"}, 0, 0);
    	mDbAdapter.createQuestion(question);
    	question = new Question("Which number is greater than 0", 1, new String[]{"-1","3","-4","-2"}, 0, 0);
    	mDbAdapter.createQuestion(question);
    	question = new Question("What is 1 + 4", 3, new String[]{"1","3","4","5"}, 0, 0);
    	mDbAdapter.createQuestion(question);
    	question = new Question("What is 1 + 0 ", 3, new String[]{"1","3","4","1"}, 0, 0);
    	mDbAdapter.createQuestion(question);
		
	}

}
