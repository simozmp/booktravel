package logic.bean;

public class RoomBean {

	private String description;

	private int beds;

	private int size;

	private int toilets;

	private int id;

	private int availability;

	private int roomChoise;

	public RoomBean() {
	}

	public RoomBean(String description, int beds, int size, int toilets, int id) {

		this.description = description;
		this.beds = beds;
		this.size = size;
		this.toilets = toilets;
		this.id = id;

	}

	public int getRoomChoise() {
		return roomChoise;
	}

	public void setRoomChoise(String roomChoise) {
		this.roomChoise = Integer.parseInt(roomChoise);
	}

	public int getBeds() {
		return beds;
	}

	public void setBeds(String beds) {
		this.beds = Integer.parseInt(beds);
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = Integer.parseInt(availability);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getToilets() {
		return toilets;
	}

	public void setToilets(int toilets) {
		this.toilets = toilets;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
