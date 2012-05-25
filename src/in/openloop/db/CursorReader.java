package in.openloop.db;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;

public abstract class CursorReader<T> {
	
	protected Cursor mCursor;
	private List<T> result = new ArrayList<T>();
	public CursorReader(Cursor c){
		this.mCursor = c;
	}

	public List<T> readRecords(){
		
		if (mCursor!= null){
			if(mCursor.moveToFirst()){
				do{
					result.add(readRecord());
				}while(mCursor.moveToNext());
			}
		}
		
		return result;
	}
	
	protected abstract T readRecord();
	
	public List<T> getResult(){
		return result;
	}
}
