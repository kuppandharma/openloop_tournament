package in.openloop;

import in.openloop.db.TournamentDbAdapter;
import in.openloop.db.model.Subject;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class SubjectsActivity extends Activity {

	
	private TournamentDbAdapter mDbAdapter;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subjects);
	
		mDbAdapter = new TournamentDbAdapter(this);
		mDbAdapter.open();
		
		LinearLayout layout = (LinearLayout)findViewById(R.id.subjects);
		
		List<Subject> subjects = mDbAdapter.getAllSubjects();
		for(Subject subject: subjects) {
			Button myButton = new Button(this);
			myButton.setText(subject.getSubjectName());
			layout.addView(myButton);
		
			myButton.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					Intent  mathsQuestion = new Intent(getApplicationContext(),QuestionActivity.class);
					startActivity(mathsQuestion);
				}
			});
		
		}
		
	}
}