package by.epamtc.restaurant.test;

import by.epamtc.restaurant.bean.user.Role;
import by.epamtc.restaurant.bean.user.User;
import by.epamtc.restaurant.bean.user.UserAuthData;
import by.epamtc.restaurant.bean.user.UserRegistrationData;
import by.epamtc.restaurant.bean.user.UserUpdateData;
import by.epamtc.restaurant.dao.exception.DAOException;
import by.epamtc.restaurant.dao.exception.UserExistsDAOException;
import by.epamtc.restaurant.dao.implTest.TestSQLUserDAO;

class TestUserDAO {

	@org.junit.jupiter.api.Test
	void positiveLogin() throws DAOException {

		TestSQLUserDAO testSQLUserDAO = new TestSQLUserDAO();

		UserAuthData userAuthData = new UserAuthData();
		userAuthData.setLogin("sasha");
		userAuthData.setPassword("6dbd0fe19c9a301c4708287780df41a2");

		User expected = new User();
		expected.setId(1);
		expected.setName("Александр");
		expected.setSurname("Новиков");
		expected.setPatronymic("Андреевич");
		expected.setPhoneNumber("375333362815");
		expected.setAge(20);
		expected.setEmail("sania.novik@mail.ru");
		expected.setRole(Role.ADMINISTRATOR);

		User actual = testSQLUserDAO.authorization(userAuthData);

		org.junit.Assert.assertEquals(expected, actual);
	}

	@org.junit.jupiter.api.Test
	void negativeLogin() throws DAOException {

		TestSQLUserDAO testSQLUserDAO = new TestSQLUserDAO();

		UserAuthData userAuthData = new UserAuthData();
		userAuthData.setLogin("nikita");
		userAuthData.setPassword("6dbd0fe19c9a301c4708287780df41a2");

		User actual = testSQLUserDAO.authorization(userAuthData);

		org.junit.Assert.assertNull(actual);
	}

	@org.junit.jupiter.api.Test
	void positiveRegistration() throws DAOException, UserExistsDAOException {

		TestSQLUserDAO testSQLUserDAO = new TestSQLUserDAO();

		UserRegistrationData userRegistrationData = new UserRegistrationData();
		userRegistrationData.setName("Иван");
		userRegistrationData.setSurname("Иванов");
		userRegistrationData.setPatronymic("Иванович");
		userRegistrationData.setAge(21);
		userRegistrationData.setEmail("ivan_ivanov@mail.ru");
		userRegistrationData.setLogin("ivan");
		userRegistrationData.setPassword("ivanqwerty1");
		userRegistrationData.setPhoneNumber("375298765432");

		org.junit.Assert.assertTrue(testSQLUserDAO.registartion(userRegistrationData));
	}

	@org.junit.jupiter.api.Test
	void negativeRegistration() throws DAOException {
		TestSQLUserDAO testSQLUserDAO = new TestSQLUserDAO();

		UserRegistrationData userRegistrationData = new UserRegistrationData();
		userRegistrationData.setName("Иван");
		userRegistrationData.setSurname("Иванов");
		userRegistrationData.setPatronymic("Иванович");
		userRegistrationData.setAge(21);
		userRegistrationData.setEmail("ivan_ivanov@mail.ru");
		userRegistrationData.setLogin("sasha");
		userRegistrationData.setPassword("ivanqwerty1");
		userRegistrationData.setPhoneNumber("375298765432");

		org.junit.Assert.assertThrows(UserExistsDAOException.class,
				() -> testSQLUserDAO.registartion(userRegistrationData));
	}

	@org.junit.jupiter.api.Test
	void successfulUpdateUserData() throws DAOException {
		TestSQLUserDAO testSQLUserDAO = new TestSQLUserDAO();
		UserUpdateData userUpdateData = new UserUpdateData();
		User user = new User();

		userUpdateData.setAge(21);
		userUpdateData.setEmail("sania.novik@mail.ru");
		userUpdateData.setName("Александр");
		userUpdateData.setPatronymic("Андреевич");
		userUpdateData.setPhoneNumber("375333362815");
		userUpdateData.setSurname("Новиков");
		userUpdateData.setId(1);

		org.junit.Assert.assertTrue(testSQLUserDAO.updateUserData(userUpdateData, user));
	}

	@org.junit.jupiter.api.Test
	void unsuccessfulUpdateData() throws DAOException {
		TestSQLUserDAO testSQLUserDAO = new TestSQLUserDAO();
		UserUpdateData userUpdateData = new UserUpdateData();
		User user = new User();

		userUpdateData.setAge(21);
		userUpdateData.setEmail("newmail@mail.ru");
		userUpdateData.setName("Александр");
		userUpdateData.setPatronymic("Андреевич");
		userUpdateData.setPhoneNumber("375333362815");
		userUpdateData.setSurname("Новиков");
		userUpdateData.setId(10);

		org.junit.Assert.assertFalse(testSQLUserDAO.updateUserData(userUpdateData, user));
	}
}
