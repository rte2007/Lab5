package commands;

import managers.MusicBandManager;

/**
 * Сохраняет коллекцию в XML-файл.
 */
public class SaveCommand implements Command {
    private final MusicBandManager manager;

    /**
     * Создаёт объект команды.
     *
     * @param manager менеджер коллекции
     */
    public SaveCommand(MusicBandManager manager) { this.manager = manager; }

    /**
     * @return имя команды
     */
    public String getName() { return "save"; }

    /**
     * @return описание команды
     */
    public String getDescription() { return "сохранить коллекцию в файл"; }

    /**
     * Выполняет команду.
     *
     * @param argument аргумент команды
     * @return true, если программу нужно завершить
     */
    public boolean execute(String argument) {
        manager.save();
        return false;
    }
}
