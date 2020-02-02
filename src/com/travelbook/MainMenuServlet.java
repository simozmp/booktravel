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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainMenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/HotelListView.jsp";
		
		String city = request.getParameter("city");
		String checkIn = request.getParameter("checkin");
		String checkOut = request.getParameter("checkout");
		String personCount = request.getParameter("personcount");
		CityDateBean fields = new CityDateBean(city, Util.parseDate(checkIn), Util.parseDate(checkOut), Integer.parseInt(personCount));
		List<HotelBean> hotels = BookHotelController.getInstance().retrieveHotelByCity(city);
		
		request.setAttribute("hotels", hotels);
		request.setAttribute("city", city);
		request.setAttribute("checkin", checkIn);
		request.setAttribute("checkout", checkOut);
		request.setAttribute("personcount", personCount);
		request.getSession().setAttribute("fields", fields);
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String url = "/HotelListView.jsp";
		
		String city = request.getParameter("city");
		String checkIn = request.getParameter("checkin");
		String checkOut = request.getParameter("checkout");
		String personCount = request.getParameter("personcount");
		List<HotelBean> hotels = BookHotelController.getInstance().retrieveHotelByCity(city);
		
		request.setAttribute("hotels", hotels);
		request.setAttribute("city", city);
		request.setAttribute("checkin", checkIn);
		request.setAttribute("checkout", checkOut);
		request.setAttribute("personcount", personCount);
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
