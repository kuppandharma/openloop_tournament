package in.openloop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ScoreCardActivity extends Activity{

	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score_card);
		
		Button finishButton = (Button)findViewById(R.id.finishButton);
		TextView score = (TextView)findViewById(R.id.scoreCardText);
		
		Intent i = getIntent();
		score.setText("Score!! -- "+i.getIntExtra("score", 0)+"");
		
		finishButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				finish();
			}
		});
	}
}
