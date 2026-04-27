package commands;

/**
 * Базовый интерфейс для всех консольных команд.
 */
public interface Command {
    /**
     * @return имя команды
     */
    String getName();

    /**
     * @return описание команды
     */
    String getDescription();

    /**
     * Выполняет команду.
     *
     * @param argument аргумент команды
     * @return true, если после выполнения команды нужно завершить программу
     */
    boolean execute(String argument);
}
