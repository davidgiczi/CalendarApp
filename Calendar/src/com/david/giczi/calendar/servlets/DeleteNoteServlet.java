package com.david.giczi.calendar.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.david.giczi.calendar.exceptions.NoteNotFoundException;
import com.david.giczi.calendar.model.Note;
import com.david.giczi.calendar.model.NoteManager;
import com.david.giczi.calendar.model.PropertyStore;


@WebServlet("/delete")
public class DeleteNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DeleteNoteServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		Map<String, String[]> inputDataStore = request.getParameterMap();

		String noteFileName = NoteManager.createFileNameString(inputDataStore.get("year")[0],
				inputDataStore.get("month")[0], inputDataStore.get("day")[0]);
		
		String[] date = noteFileName.split("_");
		
		try {
		
		Note note = NoteManager.findNoteByName(noteFileName, PropertyStore.URL4);
		
		NoteManager.deleteNote(note, PropertyStore.URL4);
		
		response.getWriter().append(date[1] + "." + date[2] + "." + date[3] + ". napon bejegyzés törölve.");
		
		}
		catch (NoteNotFoundException e) {
			
			response.getWriter().append(date[1] + "." + date[2] + "." + date[3] + ". napon bejegyzés nem található.");
			
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
