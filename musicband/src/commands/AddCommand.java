package commands;

import managers.MusicBandManager;
import utility.InputReader;

/**
 * Команда добавления нового элемента в коллекцию.
 */
public class AddCommand implements Command {
    private final MusicBandManager manager;
    private final InputReader inputReader;

    /**
     * Создаёт объект команды.
     *
     * @param manager менеджер коллекции
     * @param inputReader объект для чтения данных
     */
    public AddCommand(MusicBandManager manager, InputReader inputReader) {
        this.manager = manager;
        this.inputReader = inputReader;
    }

    public String getName() { return "add"; }
    public String getDescription() { return "добавить новый элемент в коллекцию"; }

    /**
     * Считывает новый элемент и добавляет его в коллекцию.
     *
     * @param argument аргумент команды
     * @return false, так как команда не завершает программу
     */
    public boolean execute(String argument) {
        manager.add(inputReader.readMusicBand(manager.generateId()));
        System.out.println("Элемент добавлен.");
        return false;
    }
}
