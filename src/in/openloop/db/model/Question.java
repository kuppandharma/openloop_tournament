package in.openloop.db.model;

public class Question {

	public String getQuestionText() {
		return mQuestionText;
	}

	public void setQuestionText(String mQuestionText) {
		this.mQuestionText = mQuestionText;
	}

	public int getAnswerCode() {
		return mAnswerCode;
	}

	public void setAnswerCode(int mAnswerCode) {
		this.mAnswerCode = mAnswerCode;
	}

	public String[] getChoices() {
		return mChoices;
	}

	public void setChoices(String[] mChoices) {
		this.mChoices = mChoices;
	}

	public int getLevel() {
		return mLevel;
	}

	public void setLevel(int mLevel) {
		this.mLevel = mLevel;
	}

	public int getSubjectId() {
		return mSubjectId;
	}

	public void setSubjectId(int mSubjectId) {
		this.mSubjectId = mSubjectId;
	}

	private String mQuestionText;
	private int mAnswerCode;
	private String[] mChoices;
	private int mLevel;
	private int mSubjectId;
	
	public Question(String mQuestionText, int answerCode, String[] choices, int level, int subjectId){
		
		this.mQuestionText = mQuestionText;
		this.mAnswerCode = answerCode;
		this.mChoices = choices;
		this.mLevel = level;
		this.mSubjectId = subjectId;
	}
	
	public Question(){
	}
}
