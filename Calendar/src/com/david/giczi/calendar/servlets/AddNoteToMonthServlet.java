package com.david.giczi.calendar.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.david.giczi.calendar.exceptions.NoteNotFoundException;
import com.david.giczi.calendar.model.DisplayerManager;
import com.david.giczi.calendar.model.Note;
import com.david.giczi.calendar.model.NoteManager;
import com.david.giczi.calendar.model.PropertyStore;

@WebServlet("/addNote")
public class AddNoteToMonthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddNoteToMonthServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		Map<String, String[]> inputDataStore = request.getParameterMap();

		String noteFileName = NoteManager.createFileNameString(inputDataStore.get("year")[0],
				inputDataStore.get("month")[0], inputDataStore.get("day")[0]);
		Note note;

		if (inputDataStore.get("msg")[0].endsWith(":")) {

			try {

				note = NoteManager.findNoteByName(noteFileName, PropertyStore.URL4);
				note.setColor(DisplayerManager.noteColor[Integer.parseInt(inputDataStore.get("color")[0])]);
				note.setPreContent(inputDataStore.get("msg")[0]);
				NoteManager.saveNote(note, PropertyStore.URL4);
				NoteManager.openNote(note, PropertyStore.URL4);
				response.getWriter().append("opened");

			} catch (NoteNotFoundException e) {

				note = new Note(noteFileName);
				note.setColor(DisplayerManager.noteColor[Integer.parseInt(inputDataStore.get("color")[0])]);
				note.setPreContent(inputDataStore.get("msg")[0]);
				NoteManager.saveNote(note, PropertyStore.URL4);
				response.getWriter().append("ok");

			}

		} else {

			String[] inputData = inputDataStore.get("msg")[0].split(";");

			try {

				note = NoteManager.findNoteByName(noteFileName, PropertyStore.URL4);

				if(inputData.length == 2) {
				
				note.setColor(DisplayerManager.noteColor[Integer.parseInt(inputDataStore.get("color")[0])]);
				note.setPreContent(inputData[0] + ";");
				note.setContent(inputData[1].trim());
				NoteManager.saveNote(note, PropertyStore.URL4);
				NoteManager.openNote(note, PropertyStore.URL4);
				response.getWriter().append("opened");
				}
				else {
					
					note.setColor(DisplayerManager.noteColor[Integer.parseInt(inputDataStore.get("color")[0])]);
					note.setContent(inputData[0]);
					NoteManager.saveNote(note, PropertyStore.URL4);
					response.getWriter().append("ok");
				}
			

			} catch (NoteNotFoundException e) {

				
				note = new Note(noteFileName);
				
				if(inputData.length == 2) {
					
					note.setColor(DisplayerManager.noteColor[Integer.parseInt(inputDataStore.get("color")[0])]);
					note.setPreContent(inputData[0] + ";");
					note.setContent(inputData[1].trim());
					NoteManager.saveNote(note, PropertyStore.URL4);
					response.getWriter().append("ok");
					
				}
				else {
					
					note.setColor(DisplayerManager.noteColor[Integer.parseInt(inputDataStore.get("color")[0])]);
					note.setContent(inputData[0]);
					NoteManager.saveNote(note, PropertyStore.URL4);
					response.getWriter().append("ok");
				}
				
					

			}

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
