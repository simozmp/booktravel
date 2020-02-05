package com.travelbook;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.CityDateBean;
import logic.bean.HotelBean;
import logic.bean.RoomBean;
import logic.model.BookHotelController;
import logic.model.RentablePlace;
import logic.model.dao.HotelDAOImpl;
import logic.util.Util;

/**
 * Servlet implementation class HotelView
 */
@WebServlet("/HotelView")
public class HotelView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HotelView() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String url = "/HotelView.jsp";
		
		String city = request.getParameter("city");
		LocalDate checkIn = Util.parseDate(request.getParameter("checkin"));
		LocalDate checkOut = Util.parseDate(request.getParameter("checkout"));
		int personCount = Integer.parseInt(request.getParameter("personcount"));		
		CityDateBean fields = new CityDateBean(city, checkIn, checkOut, personCount);
		
		int id = Integer.parseInt(request.getParameter("hotelid"));
		RentablePlace hotel = BookHotelController.getInstance().getRentablePlace(id );
		HotelBean hotelBean = new HotelDAOImpl().getHotel(id);
		List<RoomBean> rooms = hotel.getAvailableRooms(fields);
		
		request.getSession().setAttribute("hotelid", id);
		request.setAttribute("rooms", rooms);
		request.setAttribute("hotel", hotelBean);
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
