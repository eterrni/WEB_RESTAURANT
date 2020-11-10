package by.epamtc.restaurant.dao.impl.connection_poolTest.db_propertiesTest;

import java.util.ResourceBundle;


public class DBResourceManagerTest {

	private static final DBResourceManagerTest instance = new DBResourceManagerTest();

	private ResourceBundle bundle = ResourceBundle.getBundle("by.epamtc.restaurant.dao.impl.connection_poolTest.db_propertiesTest.db");
	
	public static DBResourceManagerTest getInstance() {
		return instance;
	}
	
	public String getValue(String key) {
		return bundle.getString(key);
	}
}