package commands;

import managers.MusicBandManager;

/**
 * Команда подсчёта элементов, у которых количество участников меньше заданного.
 */
public class CountLessThanNumberOfParticipantsCommand implements Command {
    private final MusicBandManager manager;

    /**
     * Создаёт объект команды.
     *
     * @param manager менеджер коллекции
     */
    public CountLessThanNumberOfParticipantsCommand(MusicBandManager manager) { this.manager = manager; }

    public String getName() { return "count_less_than_number_of_participants"; }
    public String getDescription() { return "вывести количество элементов с числом участников меньше заданного"; }

    /**
     * Выводит количество подходящих элементов.
     *
     * @param argument заданное количество участников
     * @return false, так как команда не завершает программу
     */
    public boolean execute(String argument) {
        if (argument.isEmpty()) {
            System.out.println("Использование: count_less_than_number_of_participants numberOfParticipants");
            return false;
        }
        long value = Long.parseLong(argument);
        System.out.println(manager.countLessThanParticipants(value));
        return false;
    }
}
