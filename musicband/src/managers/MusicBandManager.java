package managers;

import data.Coordinates;
import data.Label;
import data.MusicBand;
import data.MusicGenre;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс управления коллекцией объектов MusicBand.
 * Хранит коллекцию ArrayList, выполняет загрузку, сохранение,
 * сортировку и основные операции над элементами.
 */
public class MusicBandManager {
    private final ArrayList<MusicBand> collection = new ArrayList<>();
    private final Date initializationDate = new Date();
    private final String fileName;
    private int nextId = 1;

    /**
     * Создаёт менеджер коллекции.
     *
     * @param fileName имя XML-файла для загрузки и сохранения коллекции
     */
    public MusicBandManager(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return коллекция музыкальных групп
     */
    public ArrayList<MusicBand> getCollection() { return collection; }

    /**
     * Генерирует новый уникальный id.
     *
     * @return новый id
     */
    public int generateId() { return nextId++; }

    /**
     * Добавляет элемент в коллекцию.
     *
     * @param band добавляемая музыкальная группа
     */
    public void add(MusicBand band) {
        if (findById(band.getId()) != null) throw new IllegalArgumentException("id уже существует");
        collection.add(band);
        if (band.getId() >= nextId) nextId = band.getId() + 1;
        sort();
    }

    /**
     * Ищет элемент коллекции по id.
     *
     * @param id идентификатор элемента
     * @return найденный элемент или null
     */
    public MusicBand findById(int id) {
        for (MusicBand band : collection) {
            if (band.getId() == id) return band;
        }
        return null;
    }

    /**
     * Обновляет элемент коллекции по id.
     *
     * @param id id обновляемого элемента
     * @param newBand новый объект
     * @return true, если элемент был обновлён
     */
    public boolean update(int id, MusicBand newBand) {
        MusicBand old = findById(id);
        if (old == null) return false;
        collection.remove(old);
        newBand.setId(id);
        collection.add(newBand);
        sort();
        return true;
    }

    /**
     * Удаляет элемент коллекции по id.
     *
     * @param id id удаляемого элемента
     * @return true, если элемент был удалён
     */
    public boolean removeById(int id) {
        MusicBand band = findById(id);
        if (band == null) return false;
        collection.remove(band);
        return true;
    }

    /**
     * Очищает коллекцию.
     */
    public void clear() { collection.clear(); }

    /**
     * Сортирует коллекцию по умолчанию.
     */
    public void sort() { Collections.sort(collection); }

    /**
     * Перемешивает элементы коллекции.
     */
    public void shuffle() { Collections.shuffle(collection); }

    /**
     * Выводит элементы коллекции в порядке убывания.
     */
    public void printDescending() {
        ArrayList<MusicBand> copy = new ArrayList<>(collection);
        copy.sort(Collections.reverseOrder());
        for (MusicBand band : copy) System.out.println(band);
    }

    /**
     * Удаляет элементы, которые больше заданного.
     *
     * @param element элемент для сравнения
     */
    public void removeGreater(MusicBand element) {
        collection.removeIf(band -> band.compareTo(element) > 0);
    }

    /**
     * Считает элементы, у которых количество участников меньше заданного.
     *
     * @param value заданное количество участников
     * @return количество подходящих элементов
     */
    public long countLessThanParticipants(long value) {
        return collection.stream().filter(b -> b.getNumberOfParticipants() < value).count();
    }

    /**
     * Выводит элементы, у которых label меньше заданного.
     *
     * @param label заданный лейбл
     */
    public void filterLessThanLabel(Label label) {
        for (MusicBand band : collection) {
            if (band.getLabel().compareTo(label) < 0) System.out.println(band);
        }
    }

    /**
     * Добавляет элемент, если он меньше минимального элемента коллекции.
     *
     * @param element добавляемый элемент
     */
    public void addIfMin(MusicBand element) {
        if (collection.isEmpty() || element.compareTo(Collections.min(collection)) < 0) {
            add(element);
            System.out.println("Элемент добавлен.");
        } else {
            System.out.println("Элемент не добавлен: он не меньше минимального.");
        }
    }

    /**
     * Выводит информацию о коллекции.
     */
    public void info() {
        System.out.println("Тип коллекции: java.util.ArrayList<MusicBand>");
        System.out.println("Дата инициализации: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(initializationDate));
        System.out.println("Количество элементов: " + collection.size());
    }

    /**
     * Выводит все элементы коллекции.
     */
    public void show() {
        if (collection.isEmpty()) System.out.println("Коллекция пустая.");
        for (MusicBand band : collection) System.out.println(band);
    }

    /**
     * Загружает коллекцию из XML-файла.
     * Чтение выполняется через BufferedInputStream.
     */
    public void load() {
        File file = new File(fileName);
        if (!file.exists()) return;
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            String xml = new String(bis.readAllBytes(), StandardCharsets.UTF_8);
            Pattern p = Pattern.compile("<musicBand>(.*?)</musicBand>", Pattern.DOTALL);
            Matcher m = p.matcher(xml);
            while (m.find()) {
                try {
                    MusicBand band = parseBand(m.group(1));
                    add(band);
                } catch (Exception ignored) {
                    System.out.println("Некорректный элемент пропущен.");
                }
            }
            sort();
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    /**
     * Преобразует XML-фрагмент в объект MusicBand.
     *
     * @param s XML-фрагмент элемента
     * @return объект MusicBand
     * @throws Exception если данные некорректны
     */
    private MusicBand parseBand(String s) throws Exception {
        int id = Integer.parseInt(tag(s, "id"));
        String name = unescape(tag(s, "name"));
        int x = Integer.parseInt(tag(s, "x"));
        long y = Long.parseLong(tag(s, "y"));
        Date creationDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(tag(s, "creationDate"));
        long participants = Long.parseLong(tag(s, "numberOfParticipants"));
        MusicGenre genre = MusicGenre.valueOf(tag(s, "genre"));
        String labelName = unescape(tag(s, "labelName"));
        long bands = Long.parseLong(tag(s, "bands"));
        float sales = Float.parseFloat(tag(s, "sales"));
        return new MusicBand(id, name, new Coordinates(x, y), creationDate,
                participants, genre, new Label(labelName.isEmpty() ? null : labelName, bands, sales));
    }

    /**
     * Получает значение указанного XML-тега.
     *
     * @param s XML-строка
     * @param tag название тега
     * @return значение тега
     * @throws Exception если тег не найден
     */
    private String tag(String s, String tag) throws Exception {
        Matcher m = Pattern.compile("<" + tag + ">(.*?)</" + tag + ">", Pattern.DOTALL).matcher(s);
        if (!m.find()) throw new Exception("Не найден тег " + tag);
        return m.group(1).trim();
    }

    /**
     * Сохраняет коллекцию в XML-файл.
     * Запись выполняется через PrintWriter.
     */
    public void save() {
        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8))) {
            pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            pw.println("<musicBands>");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            for (MusicBand b : collection) {
                pw.println("  <musicBand>");
                pw.println("    <id>" + b.getId() + "</id>");
                pw.println("    <name>" + escape(b.getName()) + "</name>");
                pw.println("    <x>" + b.getCoordinates().getX() + "</x>");
                pw.println("    <y>" + b.getCoordinates().getY() + "</y>");
                pw.println("    <creationDate>" + df.format(b.getCreationDate()) + "</creationDate>");
                pw.println("    <numberOfParticipants>" + b.getNumberOfParticipants() + "</numberOfParticipants>");
                pw.println("    <genre>" + b.getGenre() + "</genre>");
                pw.println("    <labelName>" + escape(b.getLabel().getName() == null ? "" : b.getLabel().getName()) + "</labelName>");
                pw.println("    <bands>" + b.getLabel().getBands() + "</bands>");
                pw.println("    <sales>" + b.getLabel().getSales() + "</sales>");
                pw.println("  </musicBand>");
            }
            pw.println("</musicBands>");
            System.out.println("Коллекция сохранена.");
        } catch (IOException e) {
            System.out.println("Ошибка записи файла: " + e.getMessage());
        }
    }

    /**
     * Экранирует специальные символы XML.
     *
     * @param s исходная строка
     * @return безопасная строка для XML
     */
    private String escape(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    /**
     * Возвращает экранированные XML-символы обратно.
     *
     * @param s исходная строка
     * @return обычная строка
     */
    private String unescape(String s) {
        return s.replace("&lt;", "<").replace("&gt;", ">").replace("&amp;", "&");
    }
}
