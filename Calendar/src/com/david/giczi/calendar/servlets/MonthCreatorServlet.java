package com.david.giczi.calendar.servlets;

import com.david.giczi.calendar.model.Month;
import com.david.giczi.calendar.model.MonthFactory;
import com.david.giczi.calendar.model.Note;
import com.david.giczi.calendar.model.DisplayerManager;
import com.david.giczi.calendar.utils.MonthName;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author GicziD
 */
@WebServlet(name = "MonthCreatorServlet", urlPatterns = {"/createMonth"})
public class MonthCreatorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Map<String, String[]> inputDataStore = request.getParameterMap();
        
        int yearValue = Integer.parseInt(inputDataStore.get("inputyear")[0]);
        int monthValue =  Integer.parseInt(inputDataStore.get("inputmonth")[0]);

        Month month = MonthFactory.createMonth(MonthName.getMonthNameByIndex(monthValue), yearValue);
 
        DisplayerManager disp = new DisplayerManager(month.getDays(),
                month.getYear(), month.getMonthName());
        
       List<Note> noteListOfActualMonth = disp.createNoteListForActualMonth(yearValue, ++monthValue);
       
       
        request.setAttribute("year", disp.getYear());
        request.setAttribute("month", disp.getNameOfMonth());
        request.setAttribute("rows", disp.getNumberOfRows());
        request.setAttribute("days", disp.getDayNumberOfMonth());
        request.setAttribute("names", disp.getNamedaysOfMonth());
        request.setAttribute("holidays", disp.getHolidaysOfMonth());
        request.setAttribute("colors", disp.getDayColorOfMonth());
        request.setAttribute("monthsize", disp.getMonthDaysNumber());
        request.setAttribute("notestore", noteListOfActualMonth);
        request.setAttribute("selectedcolor", inputDataStore.get("inputcolor")[0]);
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
