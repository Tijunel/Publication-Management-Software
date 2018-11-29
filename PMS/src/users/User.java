package users;

import java.io.Serializable;
import java.util.ArrayList;

import back_end.Publication;
import back_end.Store;
import front_end.View;

public abstract class User implements Serializable{
	private static final long serialVersionUID = 1L;
	protected String username;
	protected View view;
	protected String email;
	protected char type;
	protected Store store;
	
	public User(String username, char type){
		this.username = username;
		this.type = type;
	}
	
	public char getType(){
		return type;
	}
	
	public String getUsername(){
		return username;
	}
	
	public Store getStore(){
		return store;
	}
	
	public void setStore(Store store) {
		this.store = store;
	}
	
	public ArrayList<String> getPublicationInfo(){
		ArrayList<Publication> pubs = store.getInventory().getPublications();
		ArrayList<String> pubData = new ArrayList<String>();
		for(int i = 0; i < pubs.size(); i++) {
			String data = "Title: " + pubs.get(i).getTitle() + ";  " + "Author: " + pubs.get(i).getAuthor() + ";  " + "IBSN: " + pubs.get(i).getIBSN();
			pubData.add(data);
		}
		return pubData;
	}
}
