package logic.bean;

import java.time.LocalDate;
import java.util.List;

import logic.model.Person;

public class BookingBean {
	
	private String hotel;
	
	private LocalDate checkIn;
	
	private LocalDate checkOut;
	
	private List<Person> people;
	
	public BookingBean(String hotel, LocalDate checkIn, LocalDate checkOut, List<Person> people) {
		
		this.hotel = hotel;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.people = people;
		
	}

	public String getHotel() {
		return hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}

	public List<Person> getPeople() {
		return people;
	}

	public void setPeople(List<Person> people) {
		this.people = people;
	}

}
