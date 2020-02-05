package logic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logic.bean.RoomBean;

public class RoomDAOImpl implements RoomDAO {
	/** Query for creating a new room. */
	private static final String CREATE_QUERY = "INSERT INTO room (description, beds, size, toilets, hotel_id) VALUES (?, ?, ?, ?, ?) ";	
	/** Query for reading all room of a specific hotel */
	private static final String READ_ALL_QUERY_BY_HOTEL_ID = "SELECT room.description, room.beds, room.size, room.toilets, room.id"
															+ " FROM room INNER JOIN hotel ON room.hotel_id = hotel.id WHERE hotel.id = ?";
	/** Query for updating a room. */
	private static final String UPDATE_QUERY = "UPDATE room SET description = ?, beds = ?, size = ?, toilets = ? WHERE id = ?";
	

	@Override
	public List<RoomBean> getAllRoomOfAnHotel(int hotelId) {
		List<RoomBean> rooms = new ArrayList<>();
		RoomBean room = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DatabaseConnection.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(READ_ALL_QUERY_BY_HOTEL_ID);
			preparedStatement.setInt(1, hotelId);
			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();
			
			while(resultSet.next()) {
				room = new RoomBean(resultSet.getString(1), resultSet.getInt(2), resultSet.getInt(3),
						resultSet.getInt(4) , resultSet.getInt(5));
				rooms.add(room);
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
		
		return rooms;
	}

	@Override
	public int createRoom(RoomBean room, int hotelId) {
		Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            conn = DatabaseConnection.getInstance().getConnection();
            preparedStatement = conn.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, room.getDescription());
            preparedStatement.setInt(2, room.getBeds());
            preparedStatement.setInt(3, room.getSize());
            preparedStatement.setInt(4, room.getToilets());
            preparedStatement.setInt(5, hotelId);
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
	public boolean updateRoom(RoomBean room) {
		Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
        	conn = DatabaseConnection.getInstance().getConnection();
            preparedStatement = conn.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, room.getDescription());
            preparedStatement.setInt(2, room.getBeds());
            preparedStatement.setInt(3, room.getSize());
            preparedStatement.setInt(4, room.getToilets());
            preparedStatement.setInt(5, room.getId());
            
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
            try {
                conn.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
        return false;
	}

}
