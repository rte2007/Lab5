package commands;

/**
 * Завершает программу без сохранения.
 */
public class ExitCommand implements Command {
    /**
     * @return имя команды
     */
    public String getName() { return "exit"; }

    /**
     * @return описание команды
     */
    public String getDescription() { return "завершить программу без сохранения"; }

    /**
     * Выполняет команду.
     *
     * @param argument аргумент команды
     * @return true, если программу нужно завершить
     */
    public boolean execute(String argument) {
        return true;
    }
}
