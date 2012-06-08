package in.openloop;

import in.openloop.applogik.UserObjects;
import in.openloop.db.TournamentDbAdapter;
import in.openloop.db.model.User;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;



public class LoginActivity extends Activity {

	private TournamentDbAdapter mDbAdapter;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		
		EditText name = (EditText)findViewById(R.id.name);
		EditText email = (EditText)findViewById(R.id.email);
		mDbAdapter = new TournamentDbAdapter(this);
		mDbAdapter.open();
		
		loginUser(name.getText().toString(), email.getText().toString());
		
		Button btnNext = (Button)findViewById(R.id.btnLogin);
		btnNext.setOnClickListener(new LoginButtonListener(name.getText().toString(), email.getText().toString()) {
			
			public void onClick(View v) {
				Intent nextScreen = new Intent(getApplicationContext(),SubjectsActivity.class);
				startActivity(nextScreen);
			}
		});
		
		Button btnUpdateQuestionBank = (Button)findViewById(R.id.btnUpdateQuestionBank);
		btnUpdateQuestionBank.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent nextScreen = new Intent(getApplicationContext(), QuestionBankActivity.class);
				startActivity(nextScreen);
			}
		});
	}
	
	private void loginUser(String name, String email){
		
		User user = mDbAdapter.getUser(name, email);
		if(user == null){
			user = mDbAdapter.createUser(name, email);
		}
		
		UserObjects.getUserObjects().setUser(user);
	}
	
	private abstract class LoginButtonListener implements OnClickListener{
		
		protected String mName;
		protected String mEmail;
		
		private LoginButtonListener(String name, String email){
		    mName = name;
		    mEmail = email;
		}
	}
}
