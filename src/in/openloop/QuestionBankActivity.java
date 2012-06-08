package in.openloop;

import java.util.Date;
import java.util.List;

import in.openloop.db.TournamentDbAdapter;
import in.openloop.db.model.Question;
import in.openloop.db.model.Subject;
import in.openloop.db.model.Tournament;
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
				createTournaments();
			}
		});
		
		buttonFinish.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				mDbAdapter.close();
				finish();
			}
		});

	}
	
	private void createTournaments(){
		for(Subject subject: mDbAdapter.getAllSubjects()){
		
			List<Question> questions = mDbAdapter.getAllQuestions(subject.getSubjectId());
			Tournament tournament = new Tournament();
			tournament.addQuestions(questions);
			tournament.setSubject(subject);
			tournament.setName(subject.getSubjectName() + new Date());
			mDbAdapter.createTournament(tournament);
		}
	}
	private void createQuestions(){
		
		Question question = new Question("What is 1 + 1", 3, new String[]{"1","3","4","2"}, 0, 2);
    	mDbAdapter.createQuestion(question);
    	question = new Question("Which number is greater than 0", 1, new String[]{"-1","3","-4","-2"}, 0, 2);
    	mDbAdapter.createQuestion(question);
    	question = new Question("What is 1 + 4", 3, new String[]{"1","3","4","5"}, 0, 2);
    	mDbAdapter.createQuestion(question);
    	question = new Question("What is 1 + 0 ", 3, new String[]{"1","3","4","1"}, 0, 2);
    	mDbAdapter.createQuestion(question);
		
	
    	question = new Question("What letter comes after 'D'", 3, new String[]{"B","A","C","E"},0,1 );
    	mDbAdapter.createQuestion(question);
    	question = new Question("Which letter comes first in alphabatical order", 1, new String[]{"B","A","C","D"}, 0, 1);
    	mDbAdapter.createQuestion(question);
    	question = new Question("What is the plural form for tooth", 1, new String[]{"tooths","teeth","teeths","tooth"}, 0, 1);
    	mDbAdapter.createQuestion(question);
    	question = new Question("What is the plural form for foot", 2, new String[]{"foots","feets","feet","foot"}, 0, 1);
    	mDbAdapter.createQuestion(question);
	
    	
    	question = new Question("What is H2O", 1, new String[]{"milk","water","gas","salt"}, 0, 3);
    	mDbAdapter.createQuestion(question);
    	question = new Question("What is common salt", 1, new String[]{"H2O","NaCl","HCL","O2"}, 0, 3);
    	mDbAdapter.createQuestion(question);
    	question = new Question("What is the boiling point of water", 3, new String[]{"1C","3C","4C","100C"}, 0, 3);
    	mDbAdapter.createQuestion(question);
    	question = new Question("What is the mass of an electron ", 3, new String[]{"1","3","4","1"}, 0, 3);
    	mDbAdapter.createQuestion(question);
	
	
	}

}
