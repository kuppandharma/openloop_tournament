package in.openloop;

import in.openloop.applogik.UserObjects;
import in.openloop.db.TournamentDbAdapter;
import in.openloop.db.model.User;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
		
		Log.w("Dharma","Just a warning message");
		
		//loginUser(name.getText().toString(), email.getText().toString());
		
		Button btnNext = (Button)findViewById(R.id.btnLogin);
		btnNext.setOnClickListener(new LoginButtonListener(name, email) {
			
			public void onClick(View v) {
				Intent nextScreen = new Intent(getApplicationContext(),SubjectsActivity.class);
				Log.w("Dharma","Just another warning message");
						
				loginUser("'"+mName.getText().toString()+"'","'" + mEmail.getText().toString() +"'");
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
		
		
		Log.w("Dharma", "####" + name + email);
		
		User user = mDbAdapter.getUser(name, email);
		if(user == null){
			user = mDbAdapter.createUser(name, email);
		}
		
		Log.w("Dharma", "###"+user.getUserName() + user.getEmail());
		
		UserObjects.getUserObjects().setUser(user);
	}
	
	private abstract class LoginButtonListener implements OnClickListener{
		
		protected EditText mName;
		protected EditText mEmail;
		
		private LoginButtonListener(EditText name, EditText email){
		    mName = name;
		    mEmail = email;
		}
	}
}
