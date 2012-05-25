package in.openloop;

import java.util.List;

import in.openloop.db.TournamentDbAdapter;
import in.openloop.db.model.Question;
import in.openloop.db.model.Subject;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class OpenloopTournamentsActivity extends Activity {
	
	private TournamentDbAdapter mDbAdapter;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        mDbAdapter = new TournamentDbAdapter(this);
        mDbAdapter.open();
        initData();
        
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
 
        startActivity(i);
    }
    
    private void initData(){
    	
    	List<Subject>  subjects = mDbAdapter.getAllSubjects();
       
    	if (!subjects.contains(new Subject(0,"ENGLISH"))){
    		mDbAdapter.createSubject("ENGLISH");
    	}

    	if (!subjects.contains(new Subject(0,"MATHS"))){
    		mDbAdapter.createSubject("MATHS");
    	}
    	
    	if (!subjects.contains(new Subject(0,"SCIENCE"))){
    		mDbAdapter.createSubject("SCIENCE");
    	}
    
    	
    }
}