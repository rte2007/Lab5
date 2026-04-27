import commands.CommandProcessor;
import managers.MusicBandManager;

import java.util.Scanner;

/**
 * Главный класс приложения.
 * Проверяет наличие имени XML-файла в аргументах командной строки,
 * загружает коллекцию из файла и запускает интерактивный режим.
 */
public class Main {
    /**
     * Точка входа в программу.
     *
     * @param args аргументы командной строки; первым аргументом должен быть путь к XML-файлу
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Использование: java Main file.xml");
            return;
        }
        MusicBandManager manager = new MusicBandManager(args[0]);
        manager.load();
        CommandProcessor processor = new CommandProcessor(manager, new Scanner(System.in));
        processor.start();
    }
}
