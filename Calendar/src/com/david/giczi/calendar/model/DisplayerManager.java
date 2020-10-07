package com.david.giczi.calendar.model;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.david.giczi.calendar.exceptions.NoSuchDayException;
import com.david.giczi.calendar.exceptions.NoSuchMonthException;
import com.david.giczi.calendar.utils.MonthName;

/**
 *
 * @author GicziD
 */
public class DisplayerManager {

	private final MonthName monthName;
	private final List<Day> days;
	private final int year;
	private int day;
	private final List<Integer> dayNumberOfMonth;
	private final List<String> dayColorOfMonth;
	private final List<String> namedaysOfMonth;
	private final List<String> holidaysOfMonth;
	private int numberOfRows;
	public static final String[] noteColor = { "#000000", "#0000FF", "#00FF00", "#FF7F00", "#FF00FF", "#FF0000"};
	public static final String[] noteColorNames = { "fekete", "kék", "zöld", "narancs", "rózsaszín", "piros"};

	public DisplayerManager(int year, MonthName monthName) {

		this.year = year;
		this.monthName = monthName;
		this.days = null;
		this.dayNumberOfMonth = null;
		this.dayColorOfMonth = null;
		this.namedaysOfMonth = null;
		this.holidaysOfMonth = null;
	}

	public DisplayerManager(List<Day> days, int year, MonthName monthName) {

		this.days = days;
		this.year = year;
		this.monthName = monthName;

		dayNumberOfMonth = new ArrayList<>();
		dayColorOfMonth = new ArrayList<>();
		namedaysOfMonth = new ArrayList<>();
		holidaysOfMonth = new ArrayList<>();

		createViewLists();
	}

	public List<Integer> getDayNumberOfMonth() {
		return dayNumberOfMonth;
	}

	public List<String> getDayColorOfMonth() {
		return dayColorOfMonth;
	}

	public List<String> getNamedaysOfMonth() {
		return namedaysOfMonth;
	}

	public List<String> getHolidaysOfMonth() {
		return holidaysOfMonth;
	}

	public int getYear() {
		return year;
	}

	public List<Day> getDays() {
		return days;
	}

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getNameOfMonth() {

		switch (monthName) {

		case JAN:

			return "Január";

		case FEB:

			return "Február";

		case MAR:

			return "Március";

		case APR:

			return "Április";

		case MAY:

			return "Május";

		case JUN:

			return "Június";

		case JUL:

			return "Július";

		case AUG:

			return "Augusztus";

		case SEP:

			return "Szeptember";

		case OCT:

			return "Október";

		case NOV:

			return "November";

		case DEC:

			return "December";

		default:

			throw new NoSuchMonthException();
		}

	}

	public int getMonthDaysNumber() {

		switch (monthName) {

		case JAN:
		case MAR:
		case MAY:
		case JUL:
		case AUG:
		case OCT:
		case DEC:

			return 31;

		case FEB:

			return new GregorianCalendar().isLeapYear(year) ? 29 : 28;

		case APR:
		case JUN:
		case SEP:
		case NOV:

			return 30;

		default:

			throw new NoSuchMonthException();

		}

	}

	private int getPreviousMonthDaysNumber() {

		switch (monthName) {

		case MAR:

			if (new GregorianCalendar().isLeapYear(year)) {
				return 29;
			} else {
				return 28;
			}

		case JAN:
		case FEB:
		case APR:
		case JUN:
		case AUG:
		case SEP:
		case NOV:
			return 31;

		case MAY:
		case JUL:
		case OCT:
		case DEC:
			return 30;

		default:
			throw new NoSuchMonthException();
		}

	}

	private void createTheEndOfThePreviousMonthElementsForViewLists() {

		int dayOfWeek = days.get(0).getDayOfWeek();

		switch (dayOfWeek) {

		case 1:

			for (int i = getPreviousMonthDaysNumber() - 5; i <= getPreviousMonthDaysNumber(); i++) {

				dayNumberOfMonth.add(i);
				dayColorOfMonth.add("#C0C0C0");
				namedaysOfMonth.add("");
				holidaysOfMonth.add("");
			}

			break;

		case 3:

			for (int i = getPreviousMonthDaysNumber(); i <= getPreviousMonthDaysNumber(); i++) {

				dayNumberOfMonth.add(i);
				dayColorOfMonth.add("#C0C0C0");
				namedaysOfMonth.add("");
				holidaysOfMonth.add("");

			}

			break;

		case 4:

			for (int i = getPreviousMonthDaysNumber() - 1; i <= getPreviousMonthDaysNumber(); i++) {

				dayNumberOfMonth.add(i);
				dayColorOfMonth.add("#C0C0C0");
				namedaysOfMonth.add("");
				holidaysOfMonth.add("");

			}

			break;

		case 5:

			for (int i = getPreviousMonthDaysNumber() - 2; i <= getPreviousMonthDaysNumber(); i++) {

				dayNumberOfMonth.add(i);
				dayColorOfMonth.add("#C0C0C0");
				namedaysOfMonth.add("");
				holidaysOfMonth.add("");

			}

			break;

		case 6:

			for (int i = getPreviousMonthDaysNumber() - 3; i <= getPreviousMonthDaysNumber(); i++) {

				dayNumberOfMonth.add(i);
				dayColorOfMonth.add("#C0C0C0");
				namedaysOfMonth.add("");
				holidaysOfMonth.add("");

			}

			break;

		case 7:

			for (int i = getPreviousMonthDaysNumber() - 4; i <= getPreviousMonthDaysNumber(); i++) {

				dayNumberOfMonth.add(i);
				dayColorOfMonth.add("#C0C0C0");
				namedaysOfMonth.add("");
				holidaysOfMonth.add("");
			}

		}

	}

	private void createTheMonthElementsForViewLists() {

		for (int i = 1; i <= days.size(); i++) {

			dayNumberOfMonth.add(i);
			namedaysOfMonth.add(days.get(i - 1).getNames());
			holidaysOfMonth.add(days.get(i - 1).getHolidayName());

			if (days.get(i - 1).getDayOfWeek() == 1) {

				dayColorOfMonth.add("red");

			} else if (days.get(i - 1).getDayOfWeek() != 1 && !"".equals(days.get(i - 1).getHolidayName())) {

				dayColorOfMonth.add("red");
			} else {

				dayColorOfMonth.add("black");
			}

		}

	}

	private void createTheBeginOfTheNextMonthElementsForViewLists() {

		int sizeValue = 35 - dayNumberOfMonth.size();

		if (dayNumberOfMonth.size() <= 35) {

			for (int i = 0; i < sizeValue; i++) {

				dayNumberOfMonth.add(i + 1);
				dayColorOfMonth.add("#C0C0C0");
				namedaysOfMonth.add("");
				holidaysOfMonth.add("");
			}

			numberOfRows = 4;

		} else {

			for (int i = 0; i < 7 + sizeValue; i++) {

				dayNumberOfMonth.add(i + 1);
				dayColorOfMonth.add("#C0C0C0");
				namedaysOfMonth.add("");
				holidaysOfMonth.add("");
			}

			numberOfRows = 5;
		}

	}

	private void createViewLists() {

		createTheEndOfThePreviousMonthElementsForViewLists();
		createTheMonthElementsForViewLists();
		createTheBeginOfTheNextMonthElementsForViewLists();

	}

	public String getNameOfDay() {

		int dayOfWeek = new GregorianCalendar(year, MonthName.getMonthIndexByName(monthName), day)
				.get(Calendar.DAY_OF_WEEK);

		switch (dayOfWeek) {

		case 1:
			return "vasárnap";
		case 2:
			return "hétfő";
		case 3:
			return "kedd";
		case 4:
			return "szerda";
		case 5:
			return "csütörtök";
		case 6:
			return "péntek";
		case 7:
			return "szombat";

		default:

			throw new NoSuchDayException();

		}

	}

	public List<Note> createNoteListForActualMonth(int year, int month) {

		List<Note> monthNoteStore = new ArrayList<>();

		int dayOfActualMonthCounter = 1;

		for (String color : dayColorOfMonth) {

			if ("#C0C0C0".equals(color)) {

				monthNoteStore.add(new Note(null));
			} else if ("black".equals(color) || "red".equals(color)) {

				monthNoteStore.add(getActualNote(dayOfActualMonthCounter, year, month));
				dayOfActualMonthCounter++;
			}

		}

		return monthNoteStore;
	}

	private Note getActualNote(int numberOfDay, int year, int month) {

		String dayValue = 10 > numberOfDay ? "0" + numberOfDay : String.valueOf(numberOfDay);
		String monthValue = String.valueOf(month);
		monthValue = 10 > month ? "0" + monthValue : monthValue;

		StringBuilder build = new StringBuilder("Note_");

		String actualNoteFileName = build.append(year).append("_").append(monthValue).append("_").append(dayValue)
				.toString();

		Note actualNote = new Note(actualNoteFileName);

		List<Note> savedNotes = NoteManager.getAllNotes(PropertyStore.URL4);

		for (Note savedNote : savedNotes) {

			if (actualNoteFileName.equals(savedNote.getNoteFileName())) {
				actualNote = savedNote;
			}

		}

		return actualNote;
	}

}
