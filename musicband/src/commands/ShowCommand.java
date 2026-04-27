package commands;

import managers.MusicBandManager;

/**
 * Выводит все элементы коллекции.
 */
public class ShowCommand implements Command {
    private final MusicBandManager manager;

    /**
     * Создаёт объект команды.
     *
     * @param manager менеджер коллекции
     */
    public ShowCommand(MusicBandManager manager) { this.manager = manager; }

    /**
     * @return имя команды
     */
    public String getName() { return "show"; }

    /**
     * @return описание команды
     */
    public String getDescription() { return "вывести все элементы коллекции"; }

    /**
     * Выполняет команду.
     *
     * @param argument аргумент команды
     * @return true, если программу нужно завершить
     */
    public boolean execute(String argument) {
        manager.show();
        return false;
    }
}
