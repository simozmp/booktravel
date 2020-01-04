package logic.bean;

import java.time.LocalDate;

public class CityDateBean {
	
	private String city;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private int personCount;
	
	public String getCity() { return city; }
	
	public void setCity(String city) { this.city = city; }
	
	public LocalDate getCheckIn() {	return checkIn;	}
	
	public void setCheckIn(LocalDate checkIn) { this.checkIn = checkIn;	}
	
	public LocalDate getCheckOut() { return checkOut; }
	
	public void setCheckOut(LocalDate checkOut) { this.checkOut = checkOut;	}
	
	public int getPersonCount() { return personCount; }
	
	public void setPersonCount(int personCount) { this.personCount = personCount; }

}
