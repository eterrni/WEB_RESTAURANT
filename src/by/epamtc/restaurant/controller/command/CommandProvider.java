package by.epamtc.restaurant.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epamtc.restaurant.controller.command.impl.GoToAboutUsPageCommand;
import by.epamtc.restaurant.controller.command.impl.GoToContactPageCommand;
import by.epamtc.restaurant.controller.command.impl.GoToErrorPageCommand;
import by.epamtc.restaurant.controller.command.impl.GoToLoginPageCommand;
import by.epamtc.restaurant.controller.command.impl.GoToMenuPageCommand;
import by.epamtc.restaurant.controller.command.impl.GoToPersonalAccountPageCommand;
import by.epamtc.restaurant.controller.command.impl.GoToRegistrationPageCommand;
import by.epamtc.restaurant.controller.command.impl.GoToStockPageCommand;
import by.epamtc.restaurant.controller.command.impl.GoToUpdateUserDataPageCommand;
import by.epamtc.restaurant.controller.command.impl.GoToWelcomePageCommand;
import by.epamtc.restaurant.controller.command.impl.LoginCommand;
import by.epamtc.restaurant.controller.command.impl.LogoutCommand;
import by.epamtc.restaurant.controller.command.impl.RegistrationCommand;
import by.epamtc.restaurant.controller.command.impl.UpdateUserDataCommand;

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
