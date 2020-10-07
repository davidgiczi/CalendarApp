package com.david.giczi.calendar.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

import com.david.giczi.calendar.model.Note;
import com.david.giczi.calendar.model.NoteManager;


class NoteManagerClassTest {

	@Test
	public void testGetNoteFileName2() throws IOException {
		
		String path1 = "C:\\Converter\\Note_2020_09_28.txt";
		String path2 = "C:\\Converter\\Note_2020_09_29.txt";
		
		File note1 = new File(path1);
		File note2 = new File(path2);
		
		note1.createNewFile();
		note2.createNewFile();
		
		String[] notes =  NoteManager.getNoteFileName("C:\\Converter");
		
		assertEquals("Note_2020_09_28.txt", notes[0]);
		assertEquals("Note_2020_09_29.txt", notes[1]);
		
		note1.delete();
		note2.delete();
	}
	
	@Test
	public void testGetNoteFileName1() throws IOException {
		
		
		String[] notes = NoteManager.getNoteFileName("C:\\Converter");
		
		assertTrue(notes.length == 0);
		
	}
	
	@Test
	public void testGetAllNotes1() {
		
		List<Note> noteStore = NoteManager.getAllNotes("C:\\Converter");
		
		assertTrue(noteStore.isEmpty());
		
	}
	
	@Test
	public void testGetAllNotes2() throws IOException {
		
		Note note = new Note("Note_2020_10_01");
		
		note.setColor("#10101");
		note.setPreContent("UB2020:");
		note.setContent("Ez egy jó és izgalmas kaland!");
		
		NoteManager.saveNote(note, "C:\\Converter");
		
		List<Note> noteStore = NoteManager.getAllNotes("C:\\Converter");
		
		System.out.println(noteStore);
		
		assertEquals(noteStore.get(0).getColor(), "#10101");
		assertEquals(noteStore.get(0).getPreContent(), "UB2020:");
		assertEquals(noteStore.get(0).getContent(), "Ez egy jó és izgalmas kaland!");
		
		
		NoteManager.deleteNote(note,"C:\\Converter");
	}
	
	@Test
	public void testOpenNote() throws IOException {
		
		Note note = new Note("Note_2020_10_04");
		note.setColor("#20304");
		note.setPreContent("Kiscsillag koncert:");
		note.setContent("Kobuci 1/2 8, ottali.");
		
		NoteManager.saveNote(note, "C:\\Converter");
		NoteManager.openNote(note, "C:\\Converter");
		NoteManager.deleteNote(note,"C:\\Converter");
		
	}
	
}
