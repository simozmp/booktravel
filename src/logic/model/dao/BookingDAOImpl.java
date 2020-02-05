package logic.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logic.bean.BookingBean;
import logic.model.bookingstate.StateEnum;

public class BookingDAOImpl implements BookingDAO {

	/** Query for creating a new booking. */
	private static final String CREATE_QUERY = "INSERT INTO booking (check_in, check_out, state, user,  room_id) VALUES (?, ?, ?, ?, ?)";
	/** Query for reading the booking. */
	private static final String READ_QUERY = "SELECT check_in, check_out, state, user, id FROM booking WHERE id = ?";
	/** Query for reading all booking of a specific room. */
	private static final String READ_ALL_BY_ROOM_ID_QUERY = "SELECT h.name, " + "b.check_in, " + "b.check_out, "
			+ "b.state, " + "b.user, " + "b.id " + "FROM hotel h " + "INNER JOIN room r " + "ON h.id = r.hotel_id "
			+ "INNER JOIN booking b " + "ON r.id = b.room_id " + "WHERE b.room_id = ?";
	/** Query for reading all booking of an user. */
	private static final String READ_ALL_BY_USER_QUERY = "SELECT h.name, " + "b.check_in, " + "b.check_out, "
			+ "b.state, " + "b.user, " + "b.id " + "FROM hotel h " + "INNER JOIN room r " + "ON h.id = r.hotel_id "
			+ "INNER JOIN booking b " + "ON r.id = b.room_id " + "WHERE b.user = ?";
	/** Query for updating the booking. */
	private static final String UPDATE_QUERY = "UPDATE booking SET state = ? WHERE id = ?";

	@Override
	public List<BookingBean> getAllBookingOfARoom(int roomId) {
		List<BookingBean> bookings = new ArrayList<>();
		BookingBean booking = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DatabaseConnection.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(READ_ALL_BY_ROOM_ID_QUERY);
			preparedStatement.setInt(1, roomId);
			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();

			while (resultSet.next()) {
				booking = new BookingBean(resultSet.getString(1), resultSet.getDate(2).toLocalDate(),
						resultSet.getDate(3).toLocalDate(), StateEnum.valueOf(resultSet.getString(4)),
						resultSet.getString(5), resultSet.getInt(6));
				bookings.add(booking);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				resultSet.close();
			} catch (Exception rse) {
				rse.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (Exception sse) {
				sse.printStackTrace();
			}

		}

		return bookings;
	}

	@Override
	public List<BookingBean> getAllBookingOfAUser(String username) {
		List<BookingBean> bookings = new ArrayList<>();
		BookingBean booking = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DatabaseConnection.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(READ_ALL_BY_USER_QUERY);
			preparedStatement.setString(1, username);
			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();

			while (resultSet.next()) {
				booking = new BookingBean(resultSet.getString(1), resultSet.getDate(2).toLocalDate(),
						resultSet.getDate(3).toLocalDate(), StateEnum.valueOf(resultSet.getString(4)),
						resultSet.getString(5), resultSet.getInt(6));
				bookings.add(booking);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				resultSet.close();
			} catch (Exception rse) {
				rse.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (Exception sse) {
				sse.printStackTrace();
			}

		}

		return bookings;
	}

	@Override
	public int createBooking(BookingBean booking, int roomId) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			conn = DatabaseConnection.getInstance().getConnection();
			preparedStatement = conn.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setDate(1, Date.valueOf(booking.getCheckIn()));
			preparedStatement.setDate(2, Date.valueOf(booking.getCheckOut()));
			preparedStatement.setString(3, String.valueOf(booking.getState()));
			preparedStatement.setString(4, booking.getUser());
			preparedStatement.setInt(5, roomId);
			preparedStatement.execute();
			result = preparedStatement.getGeneratedKeys();

			if (result.next()) {
				return result.getInt(1);
			} else {
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
			} catch (Exception rse) {
				rse.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (Exception sse) {
				sse.printStackTrace();
			}

		}

		return -1;
	}

	@Override
	public boolean updateBooking(BookingBean booking) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = DatabaseConnection.getInstance().getConnection();
			preparedStatement = conn.prepareStatement(UPDATE_QUERY);
			preparedStatement.setString(1, String.valueOf(booking.getState()));
			preparedStatement.setInt(2, booking.getBookingId());

			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (Exception sse) {
				sse.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public BookingBean getBooking(int id) {
		BookingBean booking = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DatabaseConnection.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(READ_QUERY);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();

			if (resultSet.next()) {
				booking = new BookingBean();
				booking.setCheckIn(resultSet.getDate(1).toLocalDate());
				booking.setCheckOut(resultSet.getDate(2).toLocalDate());
				booking.setState(StateEnum.valueOf(resultSet.getString(3)));
				booking.setUser(resultSet.getString(4));
				booking.setBookingId(resultSet.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				resultSet.close();
			} catch (Exception rse) {
				rse.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (Exception sse) {
				sse.printStackTrace();
			}
		}

		return booking;
	}

}
