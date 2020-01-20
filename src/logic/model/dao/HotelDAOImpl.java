package logic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logic.bean.HotelBean;
import logic.model.Hotel;

public class HotelDAOImpl implements HotelDAO {
	/** Query for creating a new hotel. */
	private static final String CREATE_QUERY = "INSERT INTO hotel (name, address, city, description, owner) VALUE (?, ?, ?, ?, ?)";
	/** Query for reading one hotel. */
	private static final String READ_QUERY = "SELECT * FROM hotel WHERE id = ?";
	/** Query for reading all hotel placed in a city. */
	private static final String READ_BY_CITY_QUERY = "SELECT * FROM hotel WHERE city = ?";
	/** Query for reading all the hotel of an owner. */
	private static final String READ_BY_OWNER_QUERY = "SELECT * FROM hotel WHERE owner = ?";
	/** Query for updating the fields of an hotel. */
	private static final String UPDATE_QUERY = "UPDATE hotel SET name = ?, address = ?, city = ?, description = ?, owner = ? WHERE id = ?";
	/** Query for deleting an hotel. */
	private static final String DELETE_QUERY = "DELETE FROM hotel WHERE id = ?";

	@Override
	public List<HotelBean> getAllHotelByCity(String city) {
		List<HotelBean> hotels = new ArrayList<HotelBean>();
		HotelBean hotel = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DatabaseConnection.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(READ_BY_CITY_QUERY);
			preparedStatement.setString(1, city);
			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();
			
			while(resultSet.next()) {
				hotel = new HotelBean(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4) , resultSet.getString(5), resultSet.getInt(6));
				hotels.add(hotel);
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
		
		return hotels;
	}

	@Override
	public HotelBean getHotel(int id) {		
		HotelBean hotel = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DatabaseConnection.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(READ_QUERY);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();
			
			if(resultSet.next() && resultSet != null) {
				hotel = new HotelBean(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), 
						resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6));
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
		
		return hotel;
	}

	@Override
	public int createHotel(HotelBean hotel) {
		Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            conn = DatabaseConnection.getInstance().getConnection();
            preparedStatement = conn.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, hotel.getName());
            preparedStatement.setString(2, hotel.getAddress());
            preparedStatement.setString(3, hotel.getCity());
            preparedStatement.setString(4, hotel.getDescription());
            preparedStatement.setString(5, hotel.getOwner());
            preparedStatement.execute();
            result = preparedStatement.getGeneratedKeys();
 
            if (result.next() && result != null) {
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
	public boolean updateHotel(HotelBean hotel) {
		Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
        	conn = DatabaseConnection.getInstance().getConnection();
            preparedStatement = conn.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, hotel.getName());
            preparedStatement.setString(2, hotel.getAddress());
            preparedStatement.setString(3, hotel.getCity());
            preparedStatement.setString(4, hotel.getDescription());
            preparedStatement.setString(5, hotel.getOwner());
            preparedStatement.setInt(6, hotel.getId());
            
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

	@Override
	public Hotel deleteHotel(Hotel hotel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HotelBean> getAllHotelByOwner(String owner) {
		List<HotelBean> hotels = new ArrayList<HotelBean>();
		HotelBean hotel = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DatabaseConnection.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(READ_BY_OWNER_QUERY);
			preparedStatement.setString(1, owner);
			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();
			
			while(resultSet.next()) {
				hotel = new HotelBean(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4) , resultSet.getString(5), resultSet.getInt(6));
				hotels.add(hotel);
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
		
		return hotels;
	}

}
