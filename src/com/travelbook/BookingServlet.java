package com.travelbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.BookingBean;
import logic.bean.CityDateBean;
import logic.bean.HotelBean;
import logic.bean.LoginBean;
import logic.bean.RoomBean;
import logic.model.BookHotelController;
import logic.model.LoginController;
import logic.model.Person;
import logic.model.RentablePlace;
import logic.model.dao.HotelDAOImpl;

/**
 * Servlet implementation class BookingServlet
 */
@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/booking.jsp";
		Enumeration<String> parameterNames = request.getParameterNames();
		Map<String, Integer> parsedValues = new HashMap<>();
		int id = (int) request.getSession().getAttribute("hotelid");
		
		while(parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			String [] parameterValues = request.getParameterValues(parameterName);
			
			for(int i = 0; i < parameterValues.length; i++) {
				parsedValues.put(parameterName, Integer.parseInt(parameterValues[i]));
			}
			
		}
		
		CityDateBean fields = (CityDateBean) request.getSession().getAttribute("fields");
		int personCount = fields.getPersonCount();
		int totalBeds = 0;
		
		for(Map.Entry<String, Integer> entry : parsedValues.entrySet())  {
			int beds = Integer.parseInt(entry.getKey());
			totalBeds += beds*entry.getValue();
		}
		
		RentablePlace hotel = BookHotelController.getInstance().getRentablePlace(id);
		List<RoomBean> rooms = hotel.getAvailableRooms(fields);
		HotelBean hotelBean = new HotelDAOImpl().getHotel(id);
		
		if(totalBeds < personCount || !checkAvailability(parsedValues, rooms)) {
			url = "/HotelView.jsp";
			request.setAttribute("error", "Invalid combination.");
		}
		
		request.getSession().setAttribute("rooms", rooms);
		request.setAttribute("hotel", hotelBean);
		
		getServletContext().getRequestDispatcher(url).forward(request, response);		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/UserProfile.jsp";
		CityDateBean fields = (CityDateBean) request.getSession().getAttribute("fields");
		int personCount = fields.getPersonCount();
		List<Person> peopleList = new ArrayList<>();
		
		for(int i = 1; i <= personCount; i++) {
			Person person = retrievePersonData(request, i);
			peopleList.add(person);
		}
		
		int hotelID = (int) request.getSession().getAttribute("hotelid");
		RentablePlace hotel = BookHotelController.getInstance().getRentablePlace(hotelID);
		
		List<RoomBean> roomBeans = (List<RoomBean>)request.getSession().getAttribute("rooms");
		
		BookHotelController.getInstance().createBooking(fields, peopleList, roomBeans, hotel);
		
		LoginBean loginBean = new LoginBean(LoginController.getInstance().getUsername(), LoginController.getInstance().getPassword());
		List<BookingBean> bookings = BookHotelController.getInstance().retrieveBookingOfAnUser(loginBean);
		Collections.reverse(bookings);
		
		request.setAttribute("bookings", bookings);		
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
	
	/**
	 * Find data from request and return a Person bean.
	 * @param request 	HTTP request
	 * @param i			the number of the person in the list.
	 * @return			a person with data found in the request
	 */
	private Person retrievePersonData(HttpServletRequest request, int i) {
		String paramName = String.valueOf(i) + "fname";
		String paramLName = String.valueOf(i) + "lname";
		String paramCF = String.valueOf(i) + "cf";
		
		String fName = request.getParameter(paramName);
		String lName = request.getParameter(paramLName);
		String cf = request.getParameter(paramCF);
		
		return new Person(fName, lName, cf);
	}

	/**
	 * return true if the rooms chooise are available, false otherwise.
	 * 
	 * @param inputValues		Map that contains (key: number of beds, value: number of rooms choise by user).
	 * @param availableRooms	List of rooms available.
	 * @return					true if rooms are available, false otherwise.
	 */
	private boolean checkAvailability(Map<String, Integer> inputValues, List<RoomBean> availableRooms) {
		
		for(Map.Entry<String, Integer> entry : inputValues.entrySet()) {
			int beds = Integer.parseInt(entry.getKey());
			for(RoomBean room : availableRooms) {
				if(beds == room.getBeds() && entry.getValue() > room.getAvailability()) {
					return false;
				} else if(beds == room.getBeds() && entry.getValue() <= room.getAvailability()){
					room.setRoomChoise(String.valueOf(entry.getValue()));
				}
			}
		}
		return true;
		
	}

}
