package by.epamtc.restaurant.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epamtc.restaurant.controller.command.impl.LoginCommand;
import by.epamtc.restaurant.controller.command.impl.LogoutCommand;
import by.epamtc.restaurant.controller.command.impl.PlaceOrderCommand;
import by.epamtc.restaurant.controller.command.impl.RegistrationCommand;
import by.epamtc.restaurant.controller.command.impl.UpdateUserDataCommand;
import by.epamtc.restaurant.controller.command.impl.add_to_order.AddDesertToOrderCommand;
import by.epamtc.restaurant.controller.command.impl.add_to_order.AddDishToOrderCommand;
import by.epamtc.restaurant.controller.command.impl.add_to_order.AddDrinkToOrderCommand;
import by.epamtc.restaurant.controller.command.impl.go_to.GoToAboutUsPageCommand;
import by.epamtc.restaurant.controller.command.impl.go_to.GoToContactPageCommand;
import by.epamtc.restaurant.controller.command.impl.go_to.GoToErrorPageCommand;
import by.epamtc.restaurant.controller.command.impl.go_to.GoToGratitudePageCommand;
import by.epamtc.restaurant.controller.command.impl.go_to.GoToLoginPageCommand;
import by.epamtc.restaurant.controller.command.impl.go_to.GoToMenuPageCommand;
import by.epamtc.restaurant.controller.command.impl.go_to.GoToOrderPageCommand;
import by.epamtc.restaurant.controller.command.impl.go_to.GoToPersonalAccountPageCommand;
import by.epamtc.restaurant.controller.command.impl.go_to.GoToRegistrationPageCommand;
import by.epamtc.restaurant.controller.command.impl.go_to.GoToStockPageCommand;
import by.epamtc.restaurant.controller.command.impl.go_to.GoToUpdateUserDataPageCommand;
import by.epamtc.restaurant.controller.command.impl.go_to.GoToUserOrderPageCommand;
import by.epamtc.restaurant.controller.command.impl.go_to.GoToWelcomePageCommand;

public class CommandProvider {

	private static final CommandProvider instance = new CommandProvider();
	private Map<ParameterName, Command> commands = new HashMap<ParameterName, Command>();

	public CommandProvider() {
		commands.put(ParameterName.LOGIN, new LoginCommand());
		commands.put(ParameterName.REGISTRATION, new RegistrationCommand());
		commands.put(ParameterName.LOGOUT, new LogoutCommand());
		commands.put(ParameterName.UPDATE_USER_DATA, new UpdateUserDataCommand());
		
		commands.put(ParameterName.GO_TO_WELCOME_PAGE, new GoToWelcomePageCommand());
		commands.put(ParameterName.GO_TO_LOGIN_PAGE, new GoToLoginPageCommand());
		commands.put(ParameterName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPageCommand());
		commands.put(ParameterName.GO_TO_ERROR_PAGE, new GoToErrorPageCommand());
		commands.put(ParameterName.GO_TO_MENU_PAGE, new GoToMenuPageCommand());
		commands.put(ParameterName.GO_TO_STOCK_PAGE, new GoToStockPageCommand());
		commands.put(ParameterName.GO_TO_ABOUT_US_PAGE, new GoToAboutUsPageCommand());
		commands.put(ParameterName.GO_TO_CONTACT_PAGE, new GoToContactPageCommand());
		commands.put(ParameterName.GO_TO_PERSONAL_ACCOUNT_PAGE, new GoToPersonalAccountPageCommand());
		commands.put(ParameterName.GO_TO_UPDATE_USER_DATA_PAGE, new GoToUpdateUserDataPageCommand());
		commands.put(ParameterName.GO_TO_ORDER_PAGE, new GoToOrderPageCommand());
		commands.put(ParameterName.GO_TO_GRATITUDE_PAGE, new GoToGratitudePageCommand());
		commands.put(ParameterName.GO_TO_USER_ORDER_PAGE, new GoToUserOrderPageCommand());
		
		commands.put(ParameterName.ADD_DISH_TO_ORDER, new AddDishToOrderCommand());
		commands.put(ParameterName.ADD_DRINK_TO_ORDER, new AddDrinkToOrderCommand());
		commands.put(ParameterName.ADD_DESERT_TO_ORDER, new AddDesertToOrderCommand());
		commands.put(ParameterName.PLACE_ORDER, new PlaceOrderCommand());
	}

	public Command getCommand(String commandName) {
		Command command;
		ParameterName valueName;

		commandName = commandName.toUpperCase();
		valueName = ParameterName.valueOf(commandName);

		command = commands.get(valueName);
		return command;
	}

	public static CommandProvider getInstance() {
		return instance;
	}
}
