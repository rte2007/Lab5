package commands;

import managers.MusicBandManager;

/**
 * Перемешивает элементы коллекции.
 */
public class ShuffleCommand implements Command {
    private final MusicBandManager manager;

    /**
     * Создаёт объект команды.
     *
     * @param manager менеджер коллекции
     */
    public ShuffleCommand(MusicBandManager manager) { this.manager = manager; }

    /**
     * @return имя команды
     */
    public String getName() { return "shuffle"; }

    /**
     * @return описание команды
     */
    public String getDescription() { return "перемешать элементы коллекции"; }

    /**
     * Выполняет команду.
     *
     * @param argument аргумент команды
     * @return true, если программу нужно завершить
     */
    public boolean execute(String argument) {
        manager.shuffle();
        System.out.println("Коллекция перемешана.");
        return false;
    }
}
