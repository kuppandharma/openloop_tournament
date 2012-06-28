package in.openloop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import in.openloop.SubjectsActivity.SubjectListener;
import in.openloop.applogik.TournamentsXMLParser;
import in.openloop.applogik.UserObjects;
import in.openloop.data.NetworkData;
import in.openloop.db.TournamentDbAdapter;
import in.openloop.db.model.Question;
import in.openloop.db.model.Subject;
import in.openloop.db.model.Tournament;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TournamentActivity extends Activity{

	private TournamentDbAdapter mDbAdapter;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tournament);
		
		
		mDbAdapter = new TournamentDbAdapter(this);
		mDbAdapter.open();
	
		TextView textView = (TextView)findViewById(R.id.textSalute);
		textView.setText("Welcome " + UserObjects.getUserObjects().getUser().getUserName());
		
		Button finish = (Button)findViewById(R.id.btnFinish);
		finish.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				finish();
			}
		});
		
		Button importTournaments = (Button)findViewById(R.id.importTournaments);
		importTournaments.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				ImportTournamentsListener list = new ImportTournamentsListener();
				list.execute("test");
			}
		});
		
		
	
		LinearLayout layout = (LinearLayout)findViewById(R.id.tournaments);

		Intent i = getIntent();
		int subjectId = i.getIntExtra("subject_id",0);
		List<Tournament> tournaments = mDbAdapter.getTournamentNames(subjectId);
		
		
		for(Tournament tournament: tournaments) {
			Button myButton = new Button(this);
			myButton.setText(tournament.getId() + tournament.getName());
			layout.addView(myButton);
			myButton.setOnClickListener(new TournamentListener(tournament) {
				
				public void onClick(View v) {
					Intent  questions = new Intent(getApplicationContext(),QuestionActivity.class);
					questions.putExtra("tournament_id", mTournament.getId());

					
					startActivity(questions);
					
					finish();
				}
				
			});
		
		}
	}
	
	public static 	abstract class TournamentListener implements OnClickListener{

		protected Tournament mTournament;
		
		public TournamentListener(Tournament tournament){
			mTournament = tournament;
		}
		
		
	}
	
	public static class ImportTournamentsListener extends AsyncTask<String, Void, String> {
		
		
		
		
		public void importTournaments(){
			
			// Somewhere in your code this is called
			try {
				URL url = new URL("http://www.openloop.in/tournaments.xml");
				HttpURLConnection con = (HttpURLConnection) url
					.openConnection();
				readStream(con.getInputStream());
				} catch (Exception e) {
				e.printStackTrace();
			}
		
		}

		private void readStream(InputStream in) {
			
			List<Tournament> tournaments = 
				TournamentsXMLParser.parseTournaments(in);
			
			Log.w("Dharma",tournaments.size()+"##");
			for(Question question:tournaments.get(0).getQuestions()){
				Log.w("Dharma",question.toString());
			}
			
			Tournament tournament = tournaments.get(0);
			Log.w("Dharma", tournament.getName());
			Log.w("Dharma", tournament.getSubject().toString());
			
		
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			importTournaments();
			return null;
		}
	}
}
