package Code;

/**
 * Класс для хранения параметров игры
 */
public class GameParams {
    private int rowCount;
    private int colCount;
    private int colorCount;

    public GameParams(int rowCount, int colCount, int colorCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.colorCount = colorCount;
    }

    public GameParams() {
        this(7, 7, 7);
    }

    /**
     * @return the colCount
     */
    public int getColCount() {
        return colCount;
    }

    /**
     * @param colCount the colCount to set
     */
    public void setColCount(int colCount) {
        this.colCount = colCount;
    }

    /**
     * @return the rowCount
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * @param rowCount the rowCount to set
     */
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    /**
     * @return the colorCount
     */
    public int getColorCount() {
        return colorCount;
    }

    /**
     * @param colorCount the colorCount to set
     */
    public void setColorCount(int colorCount) {
        this.colorCount = colorCount;
    }
}
