package com.david.giczi.calendar.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.david.giczi.calendar.model.DisplayerManager;
import com.david.giczi.calendar.model.Month;
import com.david.giczi.calendar.model.MonthFactory;
import com.david.giczi.calendar.model.Note;
import com.david.giczi.calendar.model.PropertyStore;
import com.david.giczi.calendar.utils.MonthName;





/**
 *
 * @author GicziD
 */
@WebServlet(name = "InitServlet", urlPatterns = {"/init"})
public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        PropertyStore.loadPropertiesFromFile();
         
        LocalDate today = LocalDate.now();
        
        Month month = MonthFactory.createMonth(MonthName.getMonthNameByIndex(today.getMonthValue() - 1), today.getYear());
        
        DisplayerManager disp = new DisplayerManager(month.getDays(),
                month.getYear(), month.getMonthName());

       List<Note> noteListOfActualMonth = disp.createNoteListForActualMonth(today.getYear(), today.getMonthValue());
          
      	request.setAttribute("year", disp.getYear());
        request.setAttribute("month", disp.getNameOfMonth());
        request.setAttribute("rows", disp.getNumberOfRows());
        request.setAttribute("days", disp.getDayNumberOfMonth());
        request.setAttribute("names", disp.getNamedaysOfMonth());
        request.setAttribute("holidays", disp.getHolidaysOfMonth());
        request.setAttribute("colors", disp.getDayColorOfMonth());
        request.setAttribute("monthsize", disp.getMonthDaysNumber());
        request.setAttribute("notestore", noteListOfActualMonth);
        request.setAttribute("selectedcolor", "#000000");
        request.setAttribute("colorstore", DisplayerManager.noteColor);
        request.setAttribute("colornames", DisplayerManager.noteColorNames);
        
        request.getRequestDispatcher("display.jsp").forward(request, response);

    }

	
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
