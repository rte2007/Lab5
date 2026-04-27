package commands;

import managers.MusicBandManager;
import utility.InputReader;

/**
 * Команда удаления всех элементов, превышающих заданный элемент.
 */
public class RemoveGreaterCommand implements Command {
    private final MusicBandManager manager;
    private final InputReader inputReader;

    /**
     * Создаёт объект команды.
     *
     * @param manager менеджер коллекции
     * @param inputReader объект для чтения данных
     */
    public RemoveGreaterCommand(MusicBandManager manager, InputReader inputReader) {
        this.manager = manager;
        this.inputReader = inputReader;
    }

    public String getName() { return "remove_greater"; }
    public String getDescription() { return "удалить элементы, превышающие заданный"; }

    /**
     * Считывает элемент и удаляет все элементы, которые больше него.
     *
     * @param argument аргумент команды
     * @return false, так как команда не завершает программу
     */
    public boolean execute(String argument) {
        manager.removeGreater(inputReader.readMusicBand(manager.generateId()));
        System.out.println("Элементы, превышающие заданный, удалены.");
        return false;
    }
}
