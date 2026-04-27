package data;

/**
 * Класс, хранящий информацию о музыкальном лейбле.
 */
public class Label implements Comparable<Label> {
    private String name;
    private long bands;
    private Float sales;

    /**
     * Создаёт объект лейбла.
     *
     * @param name название лейбла; может быть null
     * @param bands количество групп у лейбла
     * @param sales продажи лейбла; не могут быть null, значение должно быть больше 0
     */
    public Label(String name, long bands, Float sales) {
        this.name = name;
        this.bands = bands;
        setSales(sales);
    }

    /**
     * @return название лейбла
     */
    public String getName() { return name; }

    /**
     * @return количество групп
     */
    public long getBands() { return bands; }

    /**
     * @return продажи лейбла
     */
    public Float getSales() { return sales; }

    /**
     * Устанавливает название лейбла.
     *
     * @param name новое название
     */
    public void setName(String name) { this.name = name; }

    /**
     * Устанавливает количество групп.
     *
     * @param bands новое количество групп
     */
    public void setBands(long bands) { this.bands = bands; }

    /**
     * Устанавливает продажи лейбла.
     *
     * @param sales новое значение продаж
     */
    public void setSales(Float sales) {
        if (sales == null || sales <= 0) {
            throw new IllegalArgumentException("sales не может быть null и должен быть больше 0");
        }
        this.sales = sales;
    }

    /**
     * Сравнивает лейблы по продажам, количеству групп и названию.
     *
     * @param other другой лейбл
     * @return результат сравнения
     */
    @Override
    public int compareTo(Label other) {
        int bySales = this.sales.compareTo(other.sales);
        if (bySales != 0) return bySales;
        int byBands = Long.compare(this.bands, other.bands);
        if (byBands != 0) return byBands;
        if (this.name == null && other.name == null) return 0;
        if (this.name == null) return -1;
        if (other.name == null) return 1;
        return this.name.compareTo(other.name);
    }

    /**
     * @return строковое представление лейбла
     */
    @Override
    public String toString() {
        return "Лейбл{название='" + name + "', групп=" + bands + ", продажи=" + sales + '}';
    }
}
