package database;

import java.util.HashMap;
import java.util.Map;

public class Priv {
	private String User;
	private String ColumnName;
	private Map<String,String> map = new HashMap<String,String>();
	
	public void setUser(String User){
		this.User=User;
	}
	public String getUser(){
		return User;
	}
	public void setColumnName(String ColumnName){
		this.ColumnName=ColumnName;
	}
	public String getColumnName(){
		return ColumnName;
	}
	public void setMap(Map<String,String> map){
		this.map=map;
	}
	public Map<String,String> getMap(){
		return this.map;
	}
}
