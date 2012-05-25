package in.openloop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;



public class LoginActivity extends Activity {

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		Button btnNext = (Button)findViewById(R.id.btnNextScreen);
		btnNext.setOnClickListener(new OnClickListener() {
			
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
}
