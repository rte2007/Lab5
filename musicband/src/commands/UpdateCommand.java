package commands;

import data.MusicBand;
import managers.MusicBandManager;
import utility.InputReader;

/**
 * Команда обновления элемента коллекции по id.
 */
public class UpdateCommand implements Command {
    private final MusicBandManager manager;
    private final InputReader inputReader;

    /**
     * Создаёт объект команды.
     *
     * @param manager менеджер коллекции
     * @param inputReader объект для чтения данных
     */
    public UpdateCommand(MusicBandManager manager, InputReader inputReader) {
        this.manager = manager;
        this.inputReader = inputReader;
    }

    public String getName() { return "update"; }
    public String getDescription() { return "обновить элемент коллекции по id"; }

    /**
     * Обновляет элемент с заданным id.
     *
     * @param argument id элемента
     * @return false, так как команда не завершает программу
     */
    public boolean execute(String argument) {
        if (argument.isEmpty()) {
            System.out.println("Использование: update id");
            return false;
        }
        int id = Integer.parseInt(argument);
        if (manager.findById(id) == null) {
            System.out.println("Элемент с таким id не найден.");
            return false;
        }
        MusicBand band = inputReader.readMusicBand(id);
        if (manager.update(id, band)) System.out.println("Элемент обновлён.");
        return false;
    }
}
