package com.david.giczi.calendar.model;

public class Note {

	
	private final String noteFileName;
	private String preContent = "registration";
	private String content;
	private String color = "#FF00FF";
	private int size = 3;
	
	public Note(String noteFileName) {
		
		this.noteFileName = noteFileName;
	}
	public String getPreContent() {
		return preContent;
	}
	public void setPreContent(String preContent) {
		this.preContent = preContent;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getNoteFileName() {
		return noteFileName;
	}
	
		
	@Override
	public String toString() {
		return "Note [preContent=" + preContent + ", content=" + content + ", color=" + color + ", size=" + size
				+ ", noteFileName=" + noteFileName + "]";
	}
	
		
	
}
