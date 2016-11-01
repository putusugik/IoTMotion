package com.androidhive.androidsqlite;

public class Contact {
	
	//private variables
	int _id;
	String _waktu1;
	String _waktu2;
	
	// Empty constructor
	public Contact(){
		
	}
	// constructor
	public Contact(int id, String waktu1, String waktu2){
		this._id = id;
		this._waktu1 = waktu1;
		this._waktu2 = waktu2;
	}
	
	// constructor
	public Contact(String waktu1, String waktu2){
		this._waktu1 = waktu1;
		this._waktu2 = waktu2;
	}
	// getting ID
	public int getID(){
		return this._id;
	}
	
	// setting id
	public void setID(int id){
		this._id = id;
	}
	
	// getting name
	public String get_waktu1(){
		return this._waktu1;
	}
	
	// setting name
	public void set_waktu1(String waktu1){
		this._waktu1 = waktu1;
	}
	
	// getting phone number
	public String get_waktu2(){
		return this._waktu2;
	}
	
	// setting phone number
	public void set_waktu2(String waktu2){
		this._waktu2 = waktu2;
	}
}
