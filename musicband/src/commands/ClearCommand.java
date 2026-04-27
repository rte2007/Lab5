package commands;

import managers.MusicBandManager;

/**
 * Очищает коллекцию.
 */
public class ClearCommand implements Command {
    private final MusicBandManager manager;

    /**
     * Создаёт объект команды.
     *
     * @param manager менеджер коллекции
     */
    public ClearCommand(MusicBandManager manager) { this.manager = manager; }

    /**
     * @return имя команды
     */
    public String getName() { return "clear"; }

    /**
     * @return описание команды
     */
    public String getDescription() { return "очистить коллекцию"; }

    /**
     * Выполняет команду.
     *
     * @param argument аргумент команды
     * @return true, если программу нужно завершить
     */
    public boolean execute(String argument) {
        manager.clear();
        System.out.println("Коллекция очищена.");
        return false;
    }
}
