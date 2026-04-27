package commands;

import managers.MusicBandManager;
import utility.InputReader;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

/**
 * Класс, который обрабатывает введённые пользователем команды
 * и передаёт выполнение соответствующим классам команд.
 */
public class CommandProcessor {
    private final Map<String, Command> commands = new LinkedHashMap<>();
    private final Scanner scanner;

    /**
     * Создаёт обработчик команд.
     *
     * @param manager менеджер коллекции
     * @param scanner объект для чтения пользовательского ввода
     */
    public CommandProcessor(MusicBandManager manager, Scanner scanner) {
        this(manager, scanner, new HashSet<>());
    }

    /**
     * Создаёт обработчик команд с учётом выполняемых скриптов.
     *
     * @param manager менеджер коллекции
     * @param scanner объект для чтения пользовательского ввода
     * @param executingScripts набор файлов скриптов, которые уже выполняются
     */
    public CommandProcessor(MusicBandManager manager, Scanner scanner, Set<String> executingScripts) {
        this.scanner = scanner;
        InputReader inputReader = new InputReader(scanner);

        register(new HelpCommand(commands));
        register(new InfoCommand(manager));
        register(new ShowCommand(manager));
        register(new AddCommand(manager, inputReader));
        register(new UpdateCommand(manager, inputReader));
        register(new RemoveByIdCommand(manager));
        register(new ClearCommand(manager));
        register(new SaveCommand(manager));
        register(new ExecuteScriptCommand(manager, executingScripts));
        register(new ExitCommand());
        register(new AddIfMinCommand(manager, inputReader));
        register(new ShuffleCommand(manager));
        register(new RemoveGreaterCommand(manager, inputReader));
        register(new CountLessThanNumberOfParticipantsCommand(manager));
        register(new FilterLessThanLabelCommand(manager, inputReader));
        register(new PrintDescendingCommand(manager));
    }

    /**
     * Регистрирует команду в списке доступных команд.
     *
     * @param command команда для регистрации
     */
    private void register(Command command) {
        commands.put(command.getName(), command);
    }

    /**
     * Запускает интерактивный режим программы.
     */
    public void start() {
        System.out.println("Введите help, чтобы увидеть список команд.");
        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) break;
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            if (execute(line)) break;
        }
    }

    /**
     * Выполняет одну строку команды.
     *
     * @param line строка, введённая пользователем или прочитанная из скрипта
     * @return true, если программу нужно завершить
     */
    public boolean execute(String line) {
        String[] parts = line.split("\\s+", 2);
        String commandName = parts[0];
        String argument = parts.length > 1 ? parts[1].trim() : "";
        Command command = commands.get(commandName);
        if (command == null) {
            System.out.println("Неизвестная команда. Введите help.");
            return false;
        }
        try {
            return command.execute(argument);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            return false;
        }
    }
}
