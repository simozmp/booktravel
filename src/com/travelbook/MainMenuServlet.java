package com.travelbook;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.CityDateBean;
import logic.bean.HotelBean;
import logic.model.BookHotelController;
import logic.util.Util;

/**
 * Servlet implementation class MainMenuServlet
 */
@WebServlet("/MainMenuServlet")
public class MainMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String FIELD_HOTELS = "hotels";
	private static final String FIELD_CITY = "city";
	private static final String FIELD_CHECKIN = "checkin";
	private static final String FIELD_CHECKOUT = "checkout";
	private static final String FIELD_PERSONCOUNT = "personcount";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainMenuServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/HotelListView.jsp";
		
		String city = request.getParameter(FIELD_CITY);
		String checkIn = request.getParameter(FIELD_CHECKIN);
		String checkOut = request.getParameter(FIELD_CHECKOUT);
		String personCount = request.getParameter(FIELD_PERSONCOUNT);
		CityDateBean fields = new CityDateBean(city, Util.parseDate(checkIn), Util.parseDate(checkOut), Integer.parseInt(personCount));
		List<HotelBean> hotels = BookHotelController.getInstance().retrieveHotelByCity(city);
		
		request.setAttribute(FIELD_HOTELS, hotels);
		request.setAttribute(FIELD_CITY, city);
		request.setAttribute(FIELD_CHECKIN, checkIn);
		request.setAttribute(FIELD_CHECKOUT, checkOut);
		request.setAttribute(FIELD_PERSONCOUNT, personCount);
		request.getSession().setAttribute("fields", fields);
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String url = "/HotelListView.jsp";
		
		String city = request.getParameter(FIELD_CITY);
		String checkIn = request.getParameter(FIELD_CHECKIN);
		String checkOut = request.getParameter(FIELD_CHECKOUT);
		String personCount = request.getParameter(FIELD_PERSONCOUNT);
		List<HotelBean> hotels = BookHotelController.getInstance().retrieveHotelByCity(city);
		
		request.setAttribute(FIELD_HOTELS, hotels);
		request.setAttribute(FIELD_CITY, city);
		request.setAttribute(FIELD_CHECKIN, checkIn);
		request.setAttribute(FIELD_CHECKOUT, checkOut);
		request.setAttribute(FIELD_PERSONCOUNT, personCount);
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
