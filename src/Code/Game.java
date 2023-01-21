package Code;


/**
 * Класс, реализующий логику игры
 */
public class Game {
    public  enum GameState{
        NOT_STARTED,
        PLAYING,
        WIN,
        FAIL

    }
    GameState state = GameState.NOT_STARTED;

    /**
     * двумерный массив для хранения игрового поля
     * (в данном случае цветов, 0 - пусто; создается / пересоздается при старте игры)
     */
    private int[][] field = null;

    private int colorCount = 0;

    public Game() {
    }

    public void newGame(int rowCount, int colCount, int colorCount) {
        // создаем поле
        field = new int[rowCount][colCount];
        this.colorCount = colorCount;
    }


    public int getRowCount() {
        return field == null ? 0 : field.length;
    }

    public int getColCount() {
        return field == null ? 0 : field[0].length;
    }

}
