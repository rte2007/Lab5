package commands;

import managers.MusicBandManager;

/**
 * Команда удаления элемента коллекции по id.
 */
public class RemoveByIdCommand implements Command {
    private final MusicBandManager manager;

    /**
     * Создаёт объект команды.
     *
     * @param manager менеджер коллекции
     */
    public RemoveByIdCommand(MusicBandManager manager) { this.manager = manager; }

    public String getName() { return "remove_by_id"; }
    public String getDescription() { return "удалить элемент коллекции по id"; }

    /**
     * Удаляет элемент с заданным id.
     *
     * @param argument id элемента
     * @return false, так как команда не завершает программу
     */
    public boolean execute(String argument) {
        if (argument.isEmpty()) {
            System.out.println("Использование: remove_by_id id");
            return false;
        }
        int id = Integer.parseInt(argument);
        System.out.println(manager.removeById(id) ? "Элемент удалён." : "Элемент с таким id не найден.");
        return false;
    }
}
