package com.example.vaadindemo.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note {
	
	/*
	 * Fields.
	 */
	private String content;
	private Date lastModificationDate;
	
	/*
	 * Constructor.
	 */
	public Note(String content){
		this.setContent(content);
		this.setLastModificationDate(new Date());
	}
	
	/*
	 * Properties.
	 */
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
		this.setLastModificationDate(new Date());
	}

	public Date getLastModificationDate() {
		return lastModificationDate;
	}

	public String getLastModificationStringDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");			
		return dateFormat.format(lastModificationDate);
	}
	
	/*
	 * Private properties.
	 */
	private void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}	
}
