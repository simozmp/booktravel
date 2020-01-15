package logic.bean;

public class RoomBean {
	
	private int beds;
	
	private int availability;
	
	private int roomChoise;

	public int getRoomChoise() {	return roomChoise; }

	public void setRoomChoise(String roomChoise) { this.roomChoise = Integer.parseInt(roomChoise); }

	public int getBeds() { return beds;	}

	public void setBeds(String beds) {	this.beds = Integer.parseInt(beds); }

	public int getAvailability() { return availability;	}

	public void setAvailability(String availability) {	this.availability = Integer.parseInt(availability); }

}
