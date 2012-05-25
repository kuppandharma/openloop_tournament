package in.openloop.db;


import in.openloop.db.model.Question;
import in.openloop.db.model.Subject;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class TournamentDbAdapter {


	private static final String DATABASE_NAME = "data";
	private static final int DATABASE_VERSION = 2;

	private static final String CREATE_DATABASE_QUESTIONS = 
			"create table questions (_id integer primary key autoincrement, "
		+	"  question_text text not null," +
		" answer_choice text not null, " +
		"choice_a text not null," +
		" choice_b text not null, " +
		"choice_c text not null," +
		" choice_d text not null," +
		"question_level int not null, " +
		"subject_id int not null);";
	
	
	private static final String CREATE_DATABASE_TOURNAMENTS = 
			"create table tournaments (_id integer not null, "
		+	"  question_id int not null);"; 
			

	private static final String CREATE_DATABASE_SUBJECTS = 
			"create table subjects (_id integer  primary key autoincrement, "
		+	"  subject_name text not null);"; 

	private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    private final Context mCtx;

	    
	public static class DatabaseHelper extends SQLiteOpenHelper{

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_DATABASE_QUESTIONS);
			
			db.execSQL(CREATE_DATABASE_TOURNAMENTS);
			db.execSQL(CREATE_DATABASE_SUBJECTS);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS questions");
			db.execSQL("DROP TABLE IF EXISTS tournaments");
			db.execSQL("DROP TABLE IF EXISTS subjects");
            onCreate(db);
		}
		
	}
	
	
	public TournamentDbAdapter(Context context){
		mCtx = context;
	}
	
	
	public TournamentDbAdapter open() throws SQLException{
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}
	
	
	public void close(){
	  mDbHelper.close();
	}

	public long createSubject(String subjectName){
		ContentValues initialValues = new ContentValues();
		initialValues.put("subject_name", subjectName);
		
		return mDb.insert("subjects", null, initialValues);
	}

	
	public ArrayList<Subject> getAllSubjects(){
		ArrayList<Subject> result = new ArrayList<Subject>();
		
		Cursor c = fetchAllSubjects();
		if (c!= null){
			if(c.moveToFirst()){
				do{
					String subjectName = c.getString(c.getColumnIndex("subject_name"));
					int id = c.getInt(c.getColumnIndex("_id"));
					result.add(new Subject(id,subjectName));
				}while(c.moveToNext());
			}
		}
		
		return result;
	}
	
	private Cursor fetchAllSubjects(){
		
		return mDb.query("subjects", new String[]{"_id","subject_name"}, null, null, null,null, null);
		
	}
	
	public void clearSubjects(){
		mDb.execSQL("DROP TABLE IF EXISTS subjects");

		mDb.execSQL(CREATE_DATABASE_SUBJECTS);
	}
	
	
	public List<Question> getAllQuestions(int subjectId){
	

	return	new CursorReader<Question>(fetchAllQuestions(0)) {

			@Override
			protected Question readRecord() {
				Question question = new Question();
				question.setQuestionText(mCursor.getString(mCursor.getColumnIndex("question_text")));
				question.setAnswerCode(mCursor.getInt(mCursor.getColumnIndex("answer_choice")));
				question.setLevel(mCursor.getInt(mCursor.getColumnIndex("question_level")));
				question.setSubjectId(mCursor.getInt(mCursor.getColumnIndex("subject_id")));
				String []choices = new String[4];
				choices[0] = mCursor.getString(mCursor.getColumnIndex("choice_a"));
				choices[1] = mCursor.getString(mCursor.getColumnIndex("choice_b"));
				choices[2] = mCursor.getString(mCursor.getColumnIndex("choice_c"));
				choices[3] = mCursor.getString(mCursor.getColumnIndex("choice_d"));
				question.setChoices(choices);
				return question;
			}
		}.readRecords();
		
		
	}

	public void clearQuestions(){
		mDb.execSQL("DROP TABLE IF EXISTS questions");

		mDb.execSQL(CREATE_DATABASE_QUESTIONS);
	}

	private Cursor fetchAllQuestions(int subjectId){
		
		return mDb.query("questions", new String[]{"_id","question_text","answer_choice","choice_a","choice_b","choice_c","choice_d","question_level","subject_Id"}, null, null, null,null, null);
	}
	public long createQuestion(Question question){
		
		ContentValues initialValues = new ContentValues();
		initialValues.put("question_text", question.getQuestionText());
		initialValues.put("answer_choice", question.getAnswerCode());
		initialValues.put("choice_a", question.getChoices()[0]);
		initialValues.put("choice_b", question.getChoices()[1]);
		initialValues.put("choice_c", question.getChoices()[2]);
		initialValues.put("choice_d", question.getChoices()[3]);
		initialValues.put("question_level", question.getLevel());
		initialValues.put("subject_id", question.getSubjectId());
		
		return mDb.insert("questions", null, initialValues);
	}
	
	
}
