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
		TextView scoreCard = (TextView)findViewById(R.id.scoreCardText);
		
		Intent i = getIntent();
		Bundle extras = i.getExtras();
		
		int score = extras.getInt("score",0);
		int total = extras.getInt("total", 10);
		int tournamentId = extras.getInt("tournament_id",100);
		
		double percent = ((double)score/total)*100.0;
		
		scoreCard.setText("Score!! -- "+score+"/"+total+"  "+percent + tournamentId);
		
		finishButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				finish();
			}
		});
	}
}
