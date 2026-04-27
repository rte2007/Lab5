package commands;

import managers.MusicBandManager;

/**
 * Выводит информацию о коллекции.
 */
public class InfoCommand implements Command {
    private final MusicBandManager manager;

    /**
     * Создаёт объект команды.
     *
     * @param manager менеджер коллекции
     */
    public InfoCommand(MusicBandManager manager) { this.manager = manager; }

    /**
     * @return имя команды
     */
    public String getName() { return "info"; }

    /**
     * @return описание команды
     */
    public String getDescription() { return "вывести информацию о коллекции"; }

    /**
     * Выполняет команду.
     *
     * @param argument аргумент команды
     * @return true, если программу нужно завершить
     */
    public boolean execute(String argument) {
        manager.info();
        return false;
    }
}
