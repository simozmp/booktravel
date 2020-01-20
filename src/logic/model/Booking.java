package logic.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import logic.bean.BookingBean;
import logic.model.bookingstate.AcceptedState;
import logic.model.bookingstate.BookingState;
import logic.model.bookingstate.DeletedState;
import logic.model.bookingstate.InactiveState;
import logic.model.bookingstate.StateEnum;
import logic.model.bookingstate.SubmittedState;
import logic.model.dao.PersonDAO;
import logic.model.dao.PersonDAOImpl;

/**
 * 
 * @author metal
 *
 * This class represent a booking, it is created in its initial state of submitted state.
 */
public class Booking {
	
	/**
	 * Identify uniquely the booking.
	 */
	private int id;
	
	/**
	 * The current state of the booking, it can be Submitted, Deleted, Active or Inactive.
	 */
	private BookingState currentState;

	/**
	 * The hotel name where the booking is located.
	 */
	private String hotel;
	
	/**
	 * The user who create this booking.
	 */
	private String user;
	
	/**
	 * The Check-In date.
	 */
	private LocalDate checkIn;
	
	/**
	 * The Check-Out date.
	 */
	private LocalDate checkOut;
	
	/**
	 * A reference to a list of people which the booking is composed.
	 * Never pass it out of this class.
	 */
	private List<Person> people;
	
	/**
	 * Constructor of the class.
	 * 
	 * @param checkIn	the check in date.
	 * @param checkOut	the check out date.
	 * @param people	the list of people for this booking
	 */
	public Booking(String hotel, String user, LocalDate checkIn, LocalDate checkOut, List<Person> people) {
		
		this(hotel, user, checkIn, checkOut);
		
		this.people.addAll(people);
		
		this.setState(new SubmittedState());
		
	}
	
	public Booking(String hotel, String user, LocalDate checkIn, LocalDate checkOut) {
		
		this.hotel = hotel;		
		this.user = user;
		this.checkIn = checkIn;
		this.checkOut = checkOut;		
		this.people = new ArrayList<>();
	}
	
	/**
	 * Constructor that takes bean as a parameter.
	 * 
	 * @param bean bean that contains data.
	 */
	public Booking(BookingBean bean) {
		
		this(bean.getHotel(), bean.getUser(), bean.getCheckIn(), bean.getCheckOut());
		this.setState(bean.getState());
		this.id = bean.getBookingId();
		
		PersonDAO dao = new PersonDAOImpl();
		this.people.addAll(dao.getAllPeopleOfABooking(bean.getBookingId()));
		
	}
	
	/**
	 * Set the state of a booking.
	 * 
	 * @param state		type of state.
	 */
	public void setState(StateEnum state) {
		
		switch(state) {
		
		case SUBMITTED:
			this.currentState = new SubmittedState();
			break;
		case ACCEPTED:
			this.currentState = new AcceptedState();
			break;
		case DELETED:
			this.currentState = new DeletedState();
			break;
		case INACTIVE:
			this.currentState = new InactiveState();
			break;
		
		}
		
	}
	
	/**
	 * Set the new state. It is called from internal state of the state machine.
	 * 
	 * @param newState		the new state of the state machine.
	 */
	public void setState(BookingState newState) {
		
		this.currentState = newState;
		
	}
	
	/**
	 * 
	 * @return the current state of the booking.
	 */
	public BookingState getState() {
		
		return this.currentState;
		
	}
	
	/**
	 * It behavior depends by the current state.
	 */
	public void accept() {
		
		this.currentState.accept(this);
		
	}
	
	/**
	 * It behavior depends by the current state.
	 */
	public void delete() {
		
		this.currentState.delete(this);
		
	}
	
	/**
	 * It behavior depends by the current state.
	 */
	public void resubmit() {
		
		this.currentState.resubmit(this);
		
	}


	/**
	 * Get the check in date.
	 * 
	 * @return	check in attribute.
	 */
	public LocalDate getCheckIn() {	
		return checkIn;
	}

	/**
	 * Get the check out date.
	 * 
	 * @return	check out attribute.
	 */
	public LocalDate getCheckOut() { 
		return checkOut;
	}	
	
	public String getHotel() {
		return hotel;
	}

	public String getUser() {
		return user;
	}

	public List<Person> getPeople() {
		return people;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StateEnum getEnumValueOfState() {
		return this.currentState.getEnumValueOfState();
	}

}
