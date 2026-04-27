package utility;

import data.Coordinates;
import data.Label;
import data.MusicBand;
import data.MusicGenre;

import java.util.Date;
import java.util.Scanner;

/**
 * Класс для чтения и проверки пользовательского ввода.
 */
public class InputReader {
    private final Scanner scanner;

    /**
     * Создаёт объект для чтения ввода.
     *
     * @param scanner объект Scanner
     */
    public InputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Считывает объект MusicBand с клавиатуры.
     * Поля id и creationDate создаются автоматически.
     *
     * @param id автоматически сгенерированный id
     * @return новый объект MusicBand
     */
    public MusicBand readMusicBand(int id) {
        String name = readString("Введите name: ", false);
        Coordinates coordinates = readCoordinates();
        Long participants = readLong("Введите numberOfParticipants (> 0): ", 1L, null);
        MusicGenre genre = readGenre();
        Label label = readLabel();
        return new MusicBand(id, name, coordinates, new Date(), participants, genre, label);
    }

    /**
     * Считывает координаты.
     *
     * @return объект Coordinates
     */
    public Coordinates readCoordinates() {
        Integer x = readInteger("Введите coordinates.x (<= 854): ", null, 854);
        Long y = readLong("Введите coordinates.y: ", null, null);
        return new Coordinates(x, y);
    }

    /**
     * Считывает лейбл.
     *
     * @return объект Label
     */
    public Label readLabel() {
        String name = readString("Введите label.name: ", true);
        long bands = readLong("Введите label.bands: ", null, null);
        Float sales = readFloat("Введите label.sales (> 0): ", 0.000001f, null);
        return new Label(name.isEmpty() ? null : name, bands, sales);
    }

    /**
     * Считывает строку.
     *
     * @param prompt приглашение к вводу
     * @param allowEmpty разрешена ли пустая строка
     * @return введённая строка
     */
    public String readString(String prompt, boolean allowEmpty) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            if (allowEmpty || !line.trim().isEmpty()) return line.trim();
            System.out.println("Ошибка: значение не может быть пустым.");
        }
    }

    /**
     * Считывает целое число Integer.
     *
     * @param prompt приглашение к вводу
     * @param min минимальное значение или null
     * @param max максимальное значение или null
     * @return введённое число
     */
    public Integer readInteger(String prompt, Integer min, Integer max) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if ((min != null && value < min) || (max != null && value > max)) {
                    System.out.println("Ошибка: значение вне допустимого диапазона.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }

    /**
     * Считывает число типа Long.
     *
     * @param prompt приглашение к вводу
     * @param min минимальное значение или null
     * @param max максимальное значение или null
     * @return введённое число
     */
    public Long readLong(String prompt, Long min, Long max) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                long value = Long.parseLong(line);
                if ((min != null && value < min) || (max != null && value > max)) {
                    System.out.println("Ошибка: значение вне допустимого диапазона.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число типа long.");
            }
        }
    }

    /**
     * Считывает число типа Float.
     *
     * @param prompt приглашение к вводу
     * @param min минимальное значение или null
     * @param max максимальное значение или null
     * @return введённое число
     */
    public Float readFloat(String prompt, Float min, Float max) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                float value = Float.parseFloat(line);
                if ((min != null && value < min) || (max != null && value > max)) {
                    System.out.println("Ошибка: значение вне допустимого диапазона.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число типа float.");
            }
        }
    }

    /**
     * Считывает музыкальный жанр.
     *
     * @return выбранный MusicGenre
     */
    public MusicGenre readGenre() {
        while (true) {
            System.out.println("Доступные жанры:");
            for (MusicGenre genre : MusicGenre.values()) System.out.println("- " + genre);
            System.out.print("Введите genre: ");
            String line = scanner.nextLine().trim();
            try {
                return MusicGenre.valueOf(line);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: неверное название жанра.");
            }
        }
    }
}
