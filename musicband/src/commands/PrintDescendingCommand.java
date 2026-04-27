package commands;

import managers.MusicBandManager;

/**
 * Выводит элементы коллекции в порядке убывания.
 */
public class PrintDescendingCommand implements Command {
    private final MusicBandManager manager;

    /**
     * Создаёт объект команды.
     *
     * @param manager менеджер коллекции
     */
    public PrintDescendingCommand(MusicBandManager manager) { this.manager = manager; }

    /**
     * @return имя команды
     */
    public String getName() { return "print_descending"; }

    /**
     * @return описание команды
     */
    public String getDescription() { return "вывести элементы коллекции в порядке убывания"; }

    /**
     * Выполняет команду.
     *
     * @param argument аргумент команды
     * @return true, если программу нужно завершить
     */
    public boolean execute(String argument) {
        manager.printDescending();
        return false;
    }
}
