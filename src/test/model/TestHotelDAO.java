package test.model;



import java.util.List;

import org.junit.Assert;
import org.junit.Test;


import logic.bean.HotelBean;
import logic.model.dao.HotelDAO;
import logic.model.dao.HotelDAOImpl;

public class TestHotelDAO {

	private HotelDAO hotelDao;
	
	
	@Test
	public void testGetHotel() {
		hotelDao = new HotelDAOImpl();
		HotelBean hotel = hotelDao.getHotel(1);
		Assert.assertEquals(1 , hotel.getId());
		Assert.assertEquals("hotel bello", hotel.getName());
		Assert.assertEquals("via bella", hotel.getAddress());
		return;	
	}
	
	@Test 
	public void testCreateHotel() {
		hotelDao = new HotelDAOImpl();
		List<HotelBean> hotels = hotelDao.getAllHotelByOwner("owner");
		int size =hotels.size();
		HotelBean hotel = new HotelBean("hotel 1", "indirizzo 1", "Roma", "descrizione", "owner", 2);
		hotelDao.createHotel(hotel);
		List<HotelBean> hotels2 = hotelDao.getAllHotelByOwner("owner");
		int size1 =hotels2.size();
		Assert.assertEquals(size +1, size1);
		return;
	}
	
	@Test
	public void testGetAllHotelByOwner() {
		hotelDao = new HotelDAOImpl();
	
		List<HotelBean> hotels = hotelDao.getAllHotelByOwner("owner");
		Assert.assertEquals(2, hotels.size());
	
		
	
		return;
		
		
	}
}	
