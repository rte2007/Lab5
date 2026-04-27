package commands;

import managers.MusicBandManager;
import utility.InputReader;

/**
 * Команда добавления элемента, если он меньше минимального элемента коллекции.
 */
public class AddIfMinCommand implements Command {
    private final MusicBandManager manager;
    private final InputReader inputReader;

    /**
     * Создаёт объект команды.
     *
     * @param manager менеджер коллекции
     * @param inputReader объект для чтения данных
     */
    public AddIfMinCommand(MusicBandManager manager, InputReader inputReader) {
        this.manager = manager;
        this.inputReader = inputReader;
    }

    public String getName() { return "add_if_min"; }
    public String getDescription() { return "добавить элемент, если он меньше минимального"; }

    /**
     * Считывает элемент и добавляет его только при выполнении условия.
     *
     * @param argument аргумент команды
     * @return false, так как команда не завершает программу
     */
    public boolean execute(String argument) {
        manager.addIfMin(inputReader.readMusicBand(manager.generateId()));
        return false;
    }
}
