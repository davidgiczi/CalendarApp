package com.david.giczi.calendar.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.david.giczi.calendar.exceptions.NoteNotFoundException;


public class NoteManager {

	public static List<Note> getAllNotes(String path) {

		List<Note> store = new ArrayList<>();

		String[] noteFileNames = getNoteFileName(path);

		if (noteFileNames.length == 0) {
			return store;
		}

		for (String noteFileName : noteFileNames) {

			store.add(getNote(path, noteFileName));

		}

		return store;
	}

	public static String[] getNoteFileName(String path) {

		File notes = new File(path);
		String[] noteFileName = null;

		if (notes.exists()) {

			noteFileName = notes.list(new FilenameFilter() {

				@Override
				public boolean accept(File dir, String name) {

					return name.startsWith("Note");
				}

			});

		}

		return noteFileName;
	}

	private static Note getNote(String path, String fileName) {

		List<String> data = getData(new File(path + "\\" + fileName));

		Note note = new Note(fileName.substring(0, fileName.length() - 4));

		for (String row : data) {

			if (row.startsWith("#")) {

				note.setColor(row);
			} else if (row.endsWith(":") && 
					"registration".equals(note.getPreContent())) {

				note.setPreContent(row);
			} else {
				
				
				if(note.getContent() == null) {
					
					note.setContent(row);
				}
				else {
					
					String content = note.getContent() + "^" + row;
					note.setContent(content);
				}
			}
		}
		
		if(note.getContent() != null && "registration".equals(note.getPreContent())) {
			
			note.setPreContent("");
		}

		return note;
	}

	private static List<String> getData(File file) {

		List<String> rawData = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8")))) {

			String row;

			while ((row = reader.readLine()) != null) {

				rawData.add(row);

			}

		} catch (IOException e) {

			System.out.println("Incorrect path and/or file name: \'" + file.getAbsolutePath() + "\'");
		}

		return rawData;
	}

	public static void saveNote(Note note, String path) throws IOException {

		File noteFile = new File(path + "\\" + note.getNoteFileName() + ".txt");

			noteFile.createNewFile(); 

			try (BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(noteFile), Charset.forName("UTF-8")))) {

				writer.write(note.getColor());
				writer.newLine();
				if(!"registration".equals(note.getPreContent()) && !note.getPreContent().isEmpty()){
					writer.write(note.getPreContent());
					writer.newLine();
				}
				if (note.getContent() != null) {
					

					String[] contents = note.getContent().split("\\^");
					
					for (String content : contents) {
						writer.write(content);
						writer.newLine();
					}
					
				}

			} catch (IOException e) {

			}

		

	}

	public static boolean deleteNote(Note note, String path) {

		return new File(path + "\\" + note.getNoteFileName() + ".txt").delete();

	}

	public static void openNote(Note note, String path) throws IOException {

		File noteFile = new File(path + "\\" + note.getNoteFileName() + ".txt");

		if (noteFile.exists()) {

			Runtime run = Runtime.getRuntime();

			run.exec("notepad.exe " + noteFile.getAbsolutePath());

		}

	}

	public static String createFileNameString(String year, String month, String day) {

		month = Integer.parseInt(month) + 1 < 10 ? "0" + (Integer.parseInt(month) + 1)
				: String.valueOf(Integer.parseInt(month) + 1);
		day = Integer.parseInt(day) < 10 ? "0" + day : day;

		return "Note_" + year + "_" + month + "_" + day;
	}

	public static Note findNoteByName(String noteFileName, String path) throws NoteNotFoundException {

		List<Note> allNoteStore = getAllNotes(path);

		for (Note note : allNoteStore) {

			if (noteFileName.equals(note.getNoteFileName())) {

				return note;
			}

		}

		throw new NoteNotFoundException();
	}
}
