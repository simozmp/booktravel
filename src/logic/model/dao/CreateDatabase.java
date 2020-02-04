package logic.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {
	
	private static final String CREATE_DATABASE_QUERY = "CREATE SCHEMA IF NOT EXISTS `travelbook` DEFAULT CHARACTER SET utf8 ;";
	
	private static final String CREATE_TABLE_HOTEL = "CREATE TABLE IF NOT EXISTS `travelbook`.`hotel` (\r\n" + 
			"  `name` VARCHAR(45) NOT NULL,\r\n" + 
			"  `address` VARCHAR(45) NOT NULL,\r\n" + 
			"  `city` VARCHAR(45) NOT NULL,\r\n" + 
			"  `description` VARCHAR(500) NOT NULL,\r\n" + 
			"  `owner` VARCHAR(45) NOT NULL,\r\n" + 
			"  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,\r\n" + 
			"  PRIMARY KEY (`id`))\r\n" + 
			"ENGINE = InnoDB;";
	
	private static final String CREATE_TABLE_ROOM = "CREATE TABLE IF NOT EXISTS `travelbook`.`room` (\r\n" + 
			"  `description` VARCHAR(500) NOT NULL,\r\n" + 
			"  `beds` SMALLINT NOT NULL,\r\n" + 
			"  `size` SMALLINT NOT NULL,\r\n" + 
			"  `toilets` SMALLINT NOT NULL,\r\n" + 
			"  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,\r\n" + 
			"  `hotel_id` BIGINT UNSIGNED NOT NULL,\r\n" + 
			"  PRIMARY KEY (`id`, `hotel_id`),\r\n" + 
			"  INDEX `fk_room_hotel_idx` (`hotel_id` ASC) VISIBLE,\r\n" + 
			"  CONSTRAINT `fk_room_hotel`\r\n" + 
			"    FOREIGN KEY (`hotel_id`)\r\n" + 
			"    REFERENCES `travelbook`.`hotel` (`id`)\r\n" + 
			"    ON DELETE NO ACTION\r\n" + 
			"    ON UPDATE NO ACTION)\r\n" + 
			"ENGINE = InnoDB;";
	
	private static final String CREATE_TABLE_BOOKING = "CREATE TABLE IF NOT EXISTS `travelbook`.`booking` (\r\n" + 
			"  `check_in` DATE NOT NULL,\r\n" + 
			"  `check_out` DATE NOT NULL,\r\n" + 
			"  `state` ENUM('SUBMITTED', 'ACCEPTED', 'DELETED', 'INACTIVE') NOT NULL,\r\n" + 
			"  `user` VARCHAR(45) NOT NULL,\r\n" + 
			"  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,\r\n" + 
			"  `room_id` BIGINT UNSIGNED NOT NULL,\r\n" + 
			"  PRIMARY KEY (`id`),\r\n" + 
			"  INDEX `fk_booking_room1_idx` (`room_id` ASC) VISIBLE,\r\n" + 
			"  CONSTRAINT `fk_booking_room1`\r\n" + 
			"    FOREIGN KEY (`room_id`)\r\n" + 
			"    REFERENCES `travelbook`.`room` (`id`)\r\n" + 
			"    ON DELETE NO ACTION\r\n" + 
			"    ON UPDATE NO ACTION)\r\n" + 
			"ENGINE = InnoDB;";
	
	private static final String CREATE_TABLE_PERSON = "CREATE TABLE IF NOT EXISTS `travelbook`.`Person` (\r\n" + 
			"  `fiscal_code` VARCHAR(16) NOT NULL,\r\n" + 
			"  `first_name` VARCHAR(45) NOT NULL,\r\n" + 
			"  `last_name` VARCHAR(45) NOT NULL,\r\n" + 
			"  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,\r\n" + 
			"  `booking_id` BIGINT UNSIGNED NOT NULL,\r\n" + 
			"  PRIMARY KEY (`id`, `booking_id`),\r\n" + 
			"  INDEX `fk_Person_booking1_idx` (`booking_id` ASC) VISIBLE,\r\n" + 
			"  CONSTRAINT `fk_Person_booking1`\r\n" + 
			"    FOREIGN KEY (`booking_id`)\r\n" + 
			"    REFERENCES `travelbook`.`booking` (`id`)\r\n" + 
			"    ON DELETE NO ACTION\r\n" + 
			"    ON UPDATE NO ACTION)\r\n" + 
			"ENGINE = InnoDB;";
	
	private static final void createDatabase() {		
		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String db_url = "jdbc:mysql://localhost?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		
		String user = "root";
		String password = "password";
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(db_url, user, password);
			stmt = conn.createStatement();
			stmt.executeUpdate(CREATE_DATABASE_QUERY);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null)
					stmt.close();
			} catch(SQLException se2) {
				se2.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	private static final void executeQuery(String query) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = DatabaseConnection.getInstance().getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null)
					stmt.close();
			} catch(SQLException se2) {
				se2.printStackTrace();
			}
		}
	}
	
	public static final void createTables() {
		CreateDatabase.createDatabase();
		
		CreateDatabase.executeQuery(CREATE_TABLE_HOTEL);
		CreateDatabase.executeQuery(CREATE_TABLE_ROOM);
		CreateDatabase.executeQuery(CREATE_TABLE_BOOKING);
		CreateDatabase.executeQuery(CREATE_TABLE_PERSON);
		
		String hotelRoma = "INSERT INTO `travelbook`.`hotel` (`name`, `address`, `city`, `description`, `owner`, `id`) VALUES ('hotel roma', 'via roma 1', 'roma', 'bell\\'hotel proprio', 'owner', 1);";
		String hotelBello = "INSERT INTO `travelbook`.`hotel` (`name`, `address`, `city`, `description`, `owner`, `id`) VALUES ('hotel bello', 'via bella 1', 'rioma', 'descrizione bella', 'owner', 2);";
		
		String room1 = "INSERT INTO `travelbook`.`room` (`description`, `beds`, `size`, `toilets`, `id`, `hotel_id`) VALUES ('bellissima camera con 2 letti e 1 bagno', 2, 20, 1, 1, 1);";
		String room2 = "INSERT INTO `travelbook`.`room` (`description`, `beds`, `size`, `toilets`, `id`, `hotel_id`) VALUES ('belissima camera con 3 letti e 1 bagno', 3, 25, 1, 2, 1);";
		String room3 = "INSERT INTO `travelbook`.`room` (`description`, `beds`, `size`, `toilets`, `id`, `hotel_id`) VALUES ('belissima camera con 4 letti e 1 bagno', 4, 30, 1, 3, 1);";
		String room4 = "INSERT INTO `travelbook`.`room` (`description`, `beds`, `size`, `toilets`, `id`, `hotel_id`) VALUES ('belissima camera con 5 letti e 2 bagni', 5, 35, 2, 4, 1);";
		String room5 = "INSERT INTO `travelbook`.`room` (`description`, `beds`, `size`, `toilets`, `id`, `hotel_id`) VALUES ('bella camera con 6 letti', 6, 40, 2, 5, 1);";
		String room6 = "INSERT INTO `travelbook`.`room` (`description`, `beds`, `size`, `toilets`, `id`, `hotel_id`) VALUES ('bella camera con 2 letti', 2, 20, 1, 6, 1);";
		
		
		CreateDatabase.executeQuery(hotelRoma);
		CreateDatabase.executeQuery(hotelBello);
		CreateDatabase.executeQuery(room1);
		CreateDatabase.executeQuery(room2);
		CreateDatabase.executeQuery(room3);
		CreateDatabase.executeQuery(room4);
		CreateDatabase.executeQuery(room5);
		CreateDatabase.executeQuery(room6);
	}

}
