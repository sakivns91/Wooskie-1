package com.vikasyadav.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper{

	public final static String USERNAME = "USERNAME";
	public final static String NAME = "NAME";
	public final static String SCORE = "SCORE";
	public DatabaseHandler(Context context) {
		super(context,"wooskie", null, 2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		
		String query="CREATE TABLE wooskie (ID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "USERNAME VARCHAR(40) UNIQUE,NAME varchar(60),SCORE INTEGER)";
		
		db.execSQL(query);
	//	db.close();
		}

	@Override
	public void onUpgrade(SQLiteDatabase db,int arg1, int arg2) {
		db.execSQL("DROP TABLE IF EXISTS wooskie" );
		
        onCreate(db);
		
	}

	public void reset()
	{
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS wooskie" );
		db.close();
	}
	public long add_user(String username,String name, int score)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(USERNAME,username);
		values.put(NAME, name);
		values.put(SCORE, score);
		long success=db.insert("wooskie", null, values);
		db.close();
		
		return success;
	}
	public long add_user(User user)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(USERNAME,user.username);
		values.put(NAME, user.name);
		values.put(SCORE, user.score);
		long success=db.insert("wooskie", null, values);
		db.close();
		
		return success;
	}

	public List<User> get_userdetails()
	{
		List<User> users=new ArrayList<User>();
		SQLiteDatabase db = this.getWritableDatabase();
		String query="SELECT * FROM wooskie ORDER BY SCORE";
		Cursor cursor=db.rawQuery(query, null);
		
		
		if(cursor.moveToFirst())
		{
			do{
			User user=new User(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),Integer.parseInt(cursor.getString(3)));
			users.add(user);
			}while(cursor.moveToNext());
		}
		db.close();
		return users;
		
	}
	public int update_User(User user)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(USERNAME,user.username);
		
		values.put(SCORE, user.score);
		int ii=db.update("wooskie", values,"USERNAME=?", new String[] { String.valueOf(user.getName())} );
		db.close();
		return ii;
		
	}
	public int update_User(String username,int score)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(USERNAME,username);
		
		values.put(SCORE, score);
		int ii=db.update("wooskie", values,"USERNAME=?", new String[] { String.valueOf(username)} );
		db.close();
		return ii;
		
	}
	public int getscore(String username)
	{
		 SQLiteDatabase db = this.getReadableDatabase();
		 Cursor cursor=db.query("wooskie", new String[] {NAME,SCORE},"USERNAME=?" ,new String[]{String.valueOf(username)},null,null,null);
		 if (cursor != null)
		        cursor.moveToFirst();
		 String s1=cursor.getString(0);
		 String s2=cursor.getString(1);
	
		 db.close();
		 return Integer.parseInt(s2);
	}
	public User get_User(String username)
	{
		 SQLiteDatabase db = this.getReadableDatabase();
		 Cursor cursor=db.query("wooskie", new String[] {NAME,SCORE},"USERNAME=?" ,new String[]{String.valueOf(username)},null,null,null);
		 if (cursor != null)
		        cursor.moveToFirst();
		 String s1=cursor.getString(0);
		 String s2=cursor.getString(1);
		 
		 User t=new User(username,s1,
		            Integer.parseInt(s2));
		 db.close();
		 return t;
	}
	public  boolean CheckIsUserNameExist(String username) {
		SQLiteDatabase db = this.getReadableDatabase();
	    String Query = "Select * from " + "wooskie" + " where " + USERNAME + " = " + username;
	    Cursor cursor=db.query("wooskie", new String[] {NAME,SCORE},"USERNAME=?" ,new String[]{String.valueOf(username)},null,null,null);
		     if(cursor.getCount() <= 0){
	            cursor.close();
	            return false;
	        }
	    cursor.close();
	    return true;
	}
}
