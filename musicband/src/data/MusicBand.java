package data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Основной класс элемента коллекции.
 * Хранит данные о музыкальной группе.
 */
public class MusicBand implements Comparable<MusicBand> {
    private Integer id;
    private String name;
    private Coordinates coordinates;
    private Date creationDate;
    private Long numberOfParticipants;
    private MusicGenre genre;
    private Label label;

    /**
     * Создаёт объект музыкальной группы.
     *
     * @param id уникальный идентификатор; не может быть null, должен быть больше 0
     * @param name название группы; не может быть null или пустым
     * @param coordinates координаты группы; не могут быть null
     * @param creationDate дата создания объекта; не может быть null
     * @param numberOfParticipants количество участников; не может быть null, должно быть больше 0
     * @param genre музыкальный жанр; не может быть null
     * @param label лейбл; не может быть null
     */
    public MusicBand(Integer id, String name, Coordinates coordinates, Date creationDate,
                     Long numberOfParticipants, MusicGenre genre, Label label) {
        setId(id);
        setName(name);
        setCoordinates(coordinates);
        setCreationDate(creationDate);
        setNumberOfParticipants(numberOfParticipants);
        setGenre(genre);
        setLabel(label);
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public Coordinates getCoordinates() { return coordinates; }
    public Date getCreationDate() { return creationDate; }
    public Long getNumberOfParticipants() { return numberOfParticipants; }
    public MusicGenre getGenre() { return genre; }
    public Label getLabel() { return label; }

    /**
     * Устанавливает id музыкальной группы.
     *
     * @param id новое значение id
     */
    public void setId(Integer id) {
        if (id == null || id <= 0) throw new IllegalArgumentException("id не может быть null и должен быть больше 0");
        this.id = id;
    }

    /**
     * Устанавливает название музыкальной группы.
     *
     * @param name новое название
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("name не может быть пустым");
        this.name = name.trim();
    }

    /**
     * Устанавливает координаты.
     *
     * @param coordinates новые координаты
     */
    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) throw new IllegalArgumentException("coordinates не может быть null");
        this.coordinates = coordinates;
    }

    /**
     * Устанавливает дату создания.
     *
     * @param creationDate новая дата создания
     */
    public void setCreationDate(Date creationDate) {
        if (creationDate == null) throw new IllegalArgumentException("creationDate не может быть null");
        this.creationDate = creationDate;
    }

    /**
     * Устанавливает количество участников.
     *
     * @param numberOfParticipants новое количество участников
     */
    public void setNumberOfParticipants(Long numberOfParticipants) {
        if (numberOfParticipants == null || numberOfParticipants <= 0) {
            throw new IllegalArgumentException("numberOfParticipants не может быть null и должен быть больше 0");
        }
        this.numberOfParticipants = numberOfParticipants;
    }

    /**
     * Устанавливает музыкальный жанр.
     *
     * @param genre новый жанр
     */
    public void setGenre(MusicGenre genre) {
        if (genre == null) throw new IllegalArgumentException("genre не может быть null");
        this.genre = genre;
    }

    /**
     * Устанавливает лейбл.
     *
     * @param label новый лейбл
     */
    public void setLabel(Label label) {
        if (label == null) throw new IllegalArgumentException("label не может быть null");
        this.label = label;
    }

    /**
     * Сравнение музыкальных групп для сортировки по умолчанию.
     *
     * @param other другая музыкальная группа
     * @return результат сравнения по id
     */
    @Override
    public int compareTo(MusicBand other) {
        return this.id.compareTo(other.id);
    }

    /**
     * @return строковое представление музыкальной группы
     */
    @Override
    public String toString() {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(creationDate);
        return "Музыкальная группа{" +
                "id=" + id +
                ", название='" + name + '\'' +
                ", координаты=" + coordinates +
                ", датаСоздания=" + date +
                ", количествоУчастников=" + numberOfParticipants +
                ", жанр=" + genre +
                ", лейбл=" + label +
                '}';
    }
}
