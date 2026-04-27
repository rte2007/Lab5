package commands;

import managers.MusicBandManager;
import utility.InputReader;

/**
 * Команда вывода элементов, значение поля label которых меньше заданного.
 */
public class FilterLessThanLabelCommand implements Command {
    private final MusicBandManager manager;
    private final InputReader inputReader;

    /**
     * Создаёт объект команды.
     *
     * @param manager менеджер коллекции
     * @param inputReader объект для чтения данных
     */
    public FilterLessThanLabelCommand(MusicBandManager manager, InputReader inputReader) {
        this.manager = manager;
        this.inputReader = inputReader;
    }

    public String getName() { return "filter_less_than_label"; }
    public String getDescription() { return "вывести элементы, у которых label меньше заданного"; }

    /**
     * Считывает label и выводит подходящие элементы.
     *
     * @param argument аргумент команды
     * @return false, так как команда не завершает программу
     */
    public boolean execute(String argument) {
        manager.filterLessThanLabel(inputReader.readLabel());
        return false;
    }
}
