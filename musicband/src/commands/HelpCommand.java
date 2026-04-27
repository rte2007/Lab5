package commands;

import java.util.Map;

/**
 * Выводит справку по всем доступным командам.
 */
public class HelpCommand implements Command {
    private final Map<String, Command> commands;

    /**
     * Создаёт объект команды.
     *
     * @param commands список зарегистрированных команд
     */
    public HelpCommand(Map<String, Command> commands) {
        this.commands = commands;
    }

    /**
     * @return имя команды
     */
    public String getName() { return "help"; }

    /**
     * @return описание команды
     */
    public String getDescription() { return "вывести справку по командам"; }

    /**
     * Выполняет команду.
     *
     * @param argument аргумент команды
     * @return true, если программу нужно завершить
     */
    public boolean execute(String argument) {

        for (Command command : commands.values()) {
            System.out.println(command.getName() + " : " + command.getDescription());
        }
        return false;
    }
}
