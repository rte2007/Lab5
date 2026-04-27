package data;

/**
 * Класс, хранящий координаты музыкальной группы.
 */
public class Coordinates {
    private Integer x;
    private Long y;

    /**
     * Создаёт объект координат.
     *
     * @param x координата x; не может быть null, максимальное значение 854
     * @param y координата y; не может быть null
     */
    public Coordinates(Integer x, Long y) {
        setX(x);
        setY(y);
    }

    /**
     * @return значение координаты x
     */
    public Integer getX() { return x; }

    /**
     * @return значение координаты y
     */
    public Long getY() { return y; }

    /**
     * Устанавливает координату x.
     *
     * @param x новое значение x
     */
    public void setX(Integer x) {
        if (x == null || x > 854) {
            throw new IllegalArgumentException("x не может быть null и должен быть не больше 854");
        }
        this.x = x;
    }

    /**
     * Устанавливает координату y.
     *
     * @param y новое значение y
     */
    public void setY(Long y) {
        if (y == null) throw new IllegalArgumentException("y не может быть null");
        this.y = y;
    }

    /**
     * @return строковое представление координат
     */
    @Override
    public String toString() {
        return "Координаты{x=" + x + ", y=" + y + '}';
    }
}
