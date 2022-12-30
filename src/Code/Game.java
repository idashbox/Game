package Code;

import java.util.Random;

/**
 * Класс, реализующий логику игры
 */
public class Game {
    /**
     * объект Random для генерации случайных чисел
     * (можно было бы объявить как static)
     */
    private final Random rnd = new Random();

    /**
     * двумерный массив для хранения игрового поля
     * (в данном случае цветов, 0 - пусто; создается / пересоздается при старте игры)
     */
    private int[][] field = null;
    /**
     * Максимальное кол-во цветов
     */
    private int colorCount = 0;

    public Game() {
    }

    public void newGame(int rowCount, int colCount, int colorCount) {
        // создаем поле
        field = new int[rowCount][colCount];
        this.colorCount = colorCount;
    }

//    public void leftMouseClick(int row, int col) {
//        int rowCount = getRowCount(), colCount = getColCount();
//        if (row < 0 || row >= rowCount || col < 0 || col >= colCount) {
//            return;
//        }
//
//        field[row][col] = rnd.nextInt(getColorCount()) + 1;
//    }

//    public void rightMouseClick(int row, int col) {
//        int rowCount = getRowCount(), colCount = getColCount();
//        if (row < 0 || row >= rowCount || col < 0 || col >= colCount) {
//            return;
//        }
//
//        field[row][col] = 0;
//    }

    public int getRowCount() {
        return field == null ? 0 : field.length;
    }

    public int getColCount() {
        return field == null ? 0 : field[0].length;
    }

    public int getColorCount() {
        return colorCount;
    }

    public int getCell(int row, int col) {
        return (row < 0 || row >= getRowCount() || col < 0 || col >= getColCount()) ? 0 : field[row][col];
    }
}
