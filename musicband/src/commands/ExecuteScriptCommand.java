package commands;

import managers.MusicBandManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Команда выполнения команд из указанного файла-скрипта.
 */
public class ExecuteScriptCommand implements Command {
    private final MusicBandManager manager;
    private final Set<String> executingScripts;

    /**
     * Создаёт объект команды.
     *
     * @param manager менеджер коллекции
     * @param executingScripts набор скриптов, которые уже выполняются
     */
    public ExecuteScriptCommand(MusicBandManager manager, Set<String> executingScripts) {
        this.manager = manager;
        this.executingScripts = executingScripts;
    }

    public String getName() { return "execute_script"; }
    public String getDescription() { return "считать и выполнить команды из файла"; }

    /**
     * Выполняет команды из файла.
     *
     * @param argument имя файла скрипта
     * @return false, так как команда не завершает программу напрямую
     */
    public boolean execute(String argument) {
        if (argument.isEmpty()) {
            System.out.println("Использование: execute_script file_name");
            return false;
        }
        File file = new File(argument);
        String path = file.getAbsolutePath();
        if (executingScripts.contains(path)) {
            System.out.println("Рекурсивный вызов скрипта запрещён.");
            return false;
        }
        executingScripts.add(path);
        try (Scanner scriptScanner = new Scanner(file)) {
            CommandProcessor processor = new CommandProcessor(manager, scriptScanner, executingScripts);
            while (scriptScanner.hasNextLine()) {
                String line = scriptScanner.nextLine().trim();
                if (!line.isEmpty()) {
                    System.out.println("> " + line);
                    if (processor.execute(line)) break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл скрипта не найден.");
        } finally {
            executingScripts.remove(path);
        }
        return false;
    }
}
