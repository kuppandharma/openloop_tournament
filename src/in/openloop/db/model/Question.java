package in.openloop.db.model;

public class Question {

	public int hashCode(){
		return mQuestionText.hashCode();
	}
	
	public boolean equals(Object obj){
		if(obj instanceof Question){
			Question that = (Question)obj;
			return that.mQuestionText.equals(this.mQuestionText);
		}
		return false;
	}
	
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
	private int mId;
	
	public Question(String mQuestionText, int answerCode, String[] choices, int level, int subjectId){
		
		this.mQuestionText = mQuestionText;
		this.mAnswerCode = answerCode;
		this.mChoices = choices;
		this.mLevel = level;
		this.mSubjectId = subjectId;
	}
	
	public void setId(int id){
		mId = id;
	}
	
	public int getId(){
		return mId;
	}
	
	public Question(){
	}
	
	public String toString(){
		return mQuestionText + "\t" +
				mChoices[0] + "\t" +
				mChoices[1] + "\t" +
				mChoices[2] +"\t" +
				mChoices[3] + "\t" +
				mAnswerCode;
	}
}
