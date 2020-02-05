package test.model;



import java.util.List;

import org.junit.Assert;
import org.junit.Test;


import logic.bean.HotelBean;
import logic.model.dao.HotelDAO;
import logic.model.dao.HotelDAOImpl;

public class TestHotelDAO {

	private HotelDAO hotelDao;
	private static final String OWNER_1 = "owner";
	
	
	@Test
	public void testGetHotel() {
		hotelDao = new HotelDAOImpl();
		HotelBean hotel = hotelDao.getHotel(1);
		Assert.assertEquals(1 , hotel.getId());
		Assert.assertEquals("hotel bello", hotel.getName());
		Assert.assertEquals("via bella", hotel.getAddress());
	
	}
	
	@Test 
	public void testCreateHotel() {
		hotelDao = new HotelDAOImpl();
		List<HotelBean> hotels = hotelDao.getAllHotelByOwner(OWNER_1);
		int size =hotels.size();
		HotelBean hotel = new HotelBean("hotel 1", "indirizzo 1", "Roma", "descrizione", "owner", 2);
		hotelDao.createHotel(hotel);
		List<HotelBean> hotels2 = hotelDao.getAllHotelByOwner("owner");
		int size1 =hotels2.size();
		Assert.assertEquals(size +1, size1);
		
	}
	
	@Test
	public void testGetAllHotelByOwner() {
		hotelDao = new HotelDAOImpl();
	
		List<HotelBean> hotels = hotelDao.getAllHotelByOwner("owner");
		Assert.assertEquals(2, hotels.size());
	
		
		
	}
}	
