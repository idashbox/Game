package Code;


import util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import static util.JTableUtils.writeArrayToJTable;

public class MainForm extends JFrame {
    private JPanel panelMain;
    private JTable tableGameField;
    private JLabel labelStatus;
    private JButton Green;
    private JButton Blue;
    private JButton Orange;
    private JButton Yellow;
    private JButton Red;
    private JButton Violet;
    private JButton GameStart;
    private JLabel Moves;
    private JLabel AllMoves;

    private static final int DEFAULT_COL_COUNT = 10;
    private static final int DEFAULT_ROW_COUNT = 10;
    private static final int DEFAULT_COLOR_COUNT = 6;

    private static final int DEFAULT_GAP = 8;
    private static final int DEFAULT_CELL_SIZE = 70;

    static int[][] colors = {{0, 0, 255}, {34, 139, 34}, {255, 255, 0}, {178, 34, 34}, {255, 140, 0}, {148, 0, 211}};

    private GameParams params = new GameParams(DEFAULT_ROW_COUNT, DEFAULT_COL_COUNT, DEFAULT_COLOR_COUNT);
    private Game game = new Game();

    private double moves = 0;

    private final Random generator = new Random();
    private int[][] arr = new int[DEFAULT_ROW_COUNT][DEFAULT_COL_COUNT];
    private ParamsDialog dialogParams;
    private static Color getColor(){
    final Random generator = new Random();
    int randomIndex = generator.nextInt(colors.length);
    return new Color(colors[randomIndex][0], colors[randomIndex][1], colors[randomIndex][2]);
    }

    boolean isRadom(int row, int col, int color, int cnt){
        if (row == 0 && col == 0){
            return true;
        }
        if (Integer.parseInt(tableGameField.getValueAt(row, col).toString()) != color) {
            return false;
        } else {
            if (row > 0 && Integer.parseInt(tableGameField.getValueAt(row - 1, col).toString()) == color) {
                cnt++;
                if (cnt == 10){
                    if (row < tableGameField.getRowCount() - 1 && Integer.parseInt(tableGameField.getValueAt(row + 1, col).toString()) == color) {
                        return isRadom(row + 1, col, color, cnt);
                    } else if(col > 0 && Integer.parseInt(tableGameField.getValueAt(row, col - 1).toString()) == color){
                        return isRadom(row, col - 1, color, cnt);
                    } else if (col < tableGameField.getColumnCount() - 1 && Integer.parseInt(tableGameField.getValueAt(row, col + 1).toString()) == color){
                        return isRadom(row, col + 1, color, cnt);
                    }
                } else if (cnt > 25) {
                    return false;
                }
                return isRadom(row - 1, col, color, cnt);
            }
            if (col > 0 && Integer.parseInt(tableGameField.getValueAt(row, col - 1).toString()) == color) {
                cnt++;
                if (cnt == 10){
                    if (row < tableGameField.getRowCount() - 1 && Integer.parseInt(tableGameField.getValueAt(row + 1, col).toString()) == color) {
                        return isRadom(row + 1, col, color, cnt);
                    } else if(row > 0 && Integer.parseInt(tableGameField.getValueAt(row - 1, col).toString()) == color){
                        return isRadom(row - 1, col, color, cnt);
                    } else if (col < tableGameField.getColumnCount() - 1 && Integer.parseInt(tableGameField.getValueAt(row, col + 1).toString()) == color){
                        return isRadom(row, col + 1, color, cnt);
                    }
                } else if (cnt > 25) {
                    return false;
                }
                return isRadom(row, col - 1, color, cnt);
            }
            else {
                return false;
            }
        }
    }


    public MainForm() {
        this.setTitle("Перекраска");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        setJMenuBar(createMenuBar());
        this.pack();

        SwingUtils.setShowMessageDefaultErrorHandler();

        tableGameField.setRowHeight(DEFAULT_CELL_SIZE);
        JTableUtils.initJTableForArray(tableGameField, DEFAULT_CELL_SIZE, false, false, false, false);
        tableGameField.setIntercellSpacing(new Dimension(0, 0));
        tableGameField.setEnabled(false);
        AllMoves.setText("Ходов выполнено: " + moves);
        newGame();
        updateWindowSize();
        updateView();

        dialogParams = new ParamsDialog(params, tableGameField, e -> newGame());
        GameStart.setBackground(new Color(178, 34, 34));


        // Blue Green Yellow Red Orange Violet
        Green.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.state = Game.GameState.PLAYING;
                tableGameField.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                    final class DrawComponent extends Component {
                        private int row = 0;
                        private int column = 0;
                        final int first = Integer.parseInt(tableGameField.getValueAt(0, 0).toString());

                        @Override
                        public void paint(Graphics gr) {
                            Graphics2D g2d = (Graphics2D) gr;
                            int width = getWidth();
                            int height = getHeight();
                            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            int size = Math.min(width, height);
                            Color color;
                            if (isRadom(row, column, first, 0)){
                                color = new Color(colors[1][0], colors[1][1], colors[1][2]);
                                arr[row][column] = 1;
                            }else{
                                color = new Color(colors[Integer.parseInt(tableGameField.getValueAt(row, column).toString())][0],
                                        colors[Integer.parseInt(tableGameField.getValueAt(row, column).toString())][1],
                                        colors[Integer.parseInt(tableGameField.getValueAt(row, column).toString())][2]);
                                arr[row][column] = Integer.parseInt(tableGameField.getValueAt(row, column).toString());
                            }
                            g2d.setColor(color);
                            g2d.fillRect(0, 0, size, size);
                        }
                    }


                    DrawComponent comp = new DrawComponent();

                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        comp.row = row;
                        comp.column = column;

                        return comp;
                    }
                });
                writeArrayToJTable(tableGameField, arr);
                moves += 0.5;
                updateView();
            }
        });
        Blue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.state = Game.GameState.PLAYING;
                tableGameField.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                    final class DrawComponent extends Component {
                        private int row = 0;
                        private int column = 0;
                        final int nu = Integer.parseInt(tableGameField.getValueAt(0, 0).toString());

                        @Override
                        public void paint(Graphics gr) {
                            Graphics2D g2d = (Graphics2D) gr;
                            int width = getWidth();
                            int height = getHeight();
                            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            int size = Math.min(width, height);
                            Color color;
                            if (isRadom(row, column, nu, 0)){
                                color = new Color(colors[0][0], colors[0][1], colors[0][2]);
                                arr[row][column] = 0;
                            }else{
                                color = new Color(colors[Integer.parseInt(tableGameField.getValueAt(row, column).toString())][0],
                                        colors[Integer.parseInt(tableGameField.getValueAt(row, column).toString())][1],
                                        colors[Integer.parseInt(tableGameField.getValueAt(row, column).toString())][2]);
                                arr[row][column] = Integer.parseInt(tableGameField.getValueAt(row, column).toString());
                            }
                            g2d.setColor(color);
                            g2d.fillRect(0, 0, size, size);
                        }
                    }


                    DrawComponent comp = new DrawComponent();

                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        comp.row = row;
                        comp.column = column;

                        return comp;
                    }
                });
                writeArrayToJTable(tableGameField, arr);
                moves += 0.5;
                updateView();
            }
        });
        Orange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.state = Game.GameState.PLAYING;
                tableGameField.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                    final class DrawComponent extends Component {
                        private int row = 0;
                        private int column = 0;
                        final int fo = Integer.parseInt(tableGameField.getValueAt(0, 0).toString());

                        @Override
                        public void paint(Graphics gr) {
                            Graphics2D g2d = (Graphics2D) gr;
                            int width = getWidth();
                            int height = getHeight();
                            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            int size = Math.min(width, height);
                            Color color;
                            if (isRadom(row, column, fo, 0)){
                                color = new Color(colors[4][0], colors[4][1], colors[4][2]);
                                arr[row][column] = 4;
                            }else{
                                color = new Color(colors[Integer.parseInt(tableGameField.getValueAt(row, column).toString())][0],
                                        colors[Integer.parseInt(tableGameField.getValueAt(row, column).toString())][1],
                                        colors[Integer.parseInt(tableGameField.getValueAt(row, column).toString())][2]);
                                arr[row][column] = Integer.parseInt(tableGameField.getValueAt(row, column).toString());
                            }
                            g2d.setColor(color);
                            g2d.fillRect(0, 0, size, size);
                        }
                    }


                    DrawComponent comp = new DrawComponent();

                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        comp.row = row;
                        comp.column = column;

                        return comp;
                    }
                });
                writeArrayToJTable(tableGameField, arr);
                moves += 0.5;
                updateView();
            }
        });
        Yellow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.state = Game.GameState.PLAYING;
                tableGameField.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                    final class DrawComponent extends Component {
                        private int row = 0;
                        private int column = 0;
                        final int se = Integer.parseInt(tableGameField.getValueAt(0, 0).toString());

                        @Override
                        public void paint(Graphics gr) {
                            Graphics2D g2d = (Graphics2D) gr;
                            int width = getWidth();
                            int height = getHeight();
                            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            int size = Math.min(width, height);
                            Color color;
                            if (isRadom(row, column, se, 0)){
                                color = new Color(colors[2][0], colors[2][1], colors[2][2]);
                                arr[row][column] = 2;
                            }else{
                                color = new Color(colors[Integer.parseInt(tableGameField.getValueAt(row, column).toString())][0],
                                        colors[Integer.parseInt(tableGameField.getValueAt(row, column).toString())][1],
                                        colors[Integer.parseInt(tableGameField.getValueAt(row, column).toString())][2]);
                                arr[row][column] = Integer.parseInt(tableGameField.getValueAt(row, column).toString());
                            }
                            g2d.setColor(color);
                            g2d.fillRect(0, 0, size, size);
                        }
                    }


                    DrawComponent comp = new DrawComponent();

                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        comp.row = row;
                        comp.column = column;

                        return comp;
                    }
                });
                writeArrayToJTable(tableGameField, arr);
                moves += 0.5;
                updateView();
            }
        });
        Red.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.state = Game.GameState.PLAYING;
                tableGameField.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                    final class DrawComponent extends Component {
                        private int row = 0;
                        private int column = 0;
                        final int th = Integer.parseInt(tableGameField.getValueAt(0, 0).toString());

                        @Override
                        public void paint(Graphics gr) {
                            Graphics2D g2d = (Graphics2D) gr;
                            int width = getWidth();
                            int height = getHeight();
                            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            int size = Math.min(width, height);
                            Color color;
                            if (isRadom(row, column, th, 0)){
                                color = new Color(colors[3][0], colors[3][1], colors[3][2]);
                                arr[row][column] = 3;
                            }else{
                                color = new Color(colors[Integer.parseInt(tableGameField.getValueAt(row, column).toString())][0],
                                        colors[Integer.parseInt(tableGameField.getValueAt(row, column).toString())][1],
                                        colors[Integer.parseInt(tableGameField.getValueAt(row, column).toString())][2]);
                                arr[row][column] = Integer.parseInt(tableGameField.getValueAt(row, column).toString());
                            }
                            g2d.setColor(color);
                            g2d.fillRect(0, 0, size, size);
                        }
                    }


                    DrawComponent comp = new DrawComponent();

                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        comp.row = row;
                        comp.column = column;

                        return comp;
                    }
                });
                writeArrayToJTable(tableGameField, arr);
                moves += 0.5;
                updateView();
            }
        });
        Violet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.state = Game.GameState.PLAYING;
                tableGameField.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                    final class DrawComponent extends Component {
                        private int row = 0;
                        private int column = 0;
                        final int fu = Integer.parseInt(tableGameField.getValueAt(0, 0).toString());

                        @Override
                        public void paint(Graphics gr) {
                            Graphics2D g2d = (Graphics2D) gr;
                            int width = getWidth();
                            int height = getHeight();
                            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            int size = Math.min(width, height);
                            Color color;
                            if (isRadom(row, column, fu, 0)){
                                color = new Color(colors[5][0], colors[5][1], colors[5][2]);
                                arr[row][column] = 5;
                            }else{
                                color = new Color(colors[Integer.parseInt(tableGameField.getValueAt(row, column).toString())][0],
                                        colors[Integer.parseInt(tableGameField.getValueAt(row, column).toString())][1],
                                        colors[Integer.parseInt(tableGameField.getValueAt(row, column).toString())][2]);
                                arr[row][column] = Integer.parseInt(tableGameField.getValueAt(row, column).toString());
                            }
                            g2d.setColor(color);
                            g2d.fillRect(0, 0, size, size);
                        }
                    }


                    DrawComponent comp = new DrawComponent();

                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        comp.row = row;
                        comp.column = column;

                        return comp;
                    }
                });
                writeArrayToJTable(tableGameField, arr);
                moves += 0.5;
                updateView();
            }
        });
        Blue.setBackground(new Color(0, 0, 255));
        Green.setBackground(new Color(34, 139, 34));
        Orange.setBackground(new Color(255, 140, 0));
        Yellow.setBackground(new Color(255, 255, 0));
        Red.setBackground(new Color(178, 34, 34));
        Violet.setBackground(new Color(148, 0, 211));
        Blue.setPreferredSize(new Dimension(10, 50));
        Green.setPreferredSize(new Dimension(10, 50));
        Yellow.setPreferredSize(new Dimension(10, 50));
        Red.setPreferredSize(new Dimension(10, 50));
        Orange.setPreferredSize(new Dimension(10, 50));
        Violet.setPreferredSize(new Dimension(10, 50));
        GameStart.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                GameStart.setBackground(Color.GRAY);
                game.state = Game.GameState.PLAYING;
                Green.setEnabled(true);
                Blue.setEnabled(true);
                Yellow.setEnabled(true);
                Red.setEnabled(true);
                Violet.setEnabled(true);
                Orange.setEnabled(true);
                for (int row = 0; row < arr.length; row++) {
                    for (int col = 0; col < arr[0].length; col++) {
                        int randomIndex = generator.nextInt(colors.length);
                        arr[row][col] = randomIndex;
                    }
                }
                writeArrayToJTable(tableGameField, arr);


                tableGameField.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                    final class DrawComponent extends Component {
                        private int row = 0, column = 0;

                        @Override
                        public void paint(Graphics gr) {
                            Graphics2D g2d = (Graphics2D) gr;
                            int width = getWidth();
                            int height = getHeight();
                            paintCell(row, column, g2d, width, height);
                        }
                    }

                    DrawComponent comp = new DrawComponent();

                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value,
                                                                   boolean isSelected, boolean hasFocus, int row, int column) {
                        comp.row = row;
                        comp.column = column;

                        return comp;
                    }
                });

                newGame();
                updateView();
            }
        });
    }

    private JMenuItem createMenuItem(String text, String shortcut, Character mnemonic, ActionListener listener) {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.addActionListener(listener);
        if (shortcut != null) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(shortcut.replace('+', ' ')));
        }
        if (mnemonic != null) {
            menuItem.setMnemonic(mnemonic);
        }
        return menuItem;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBarMain = new JMenuBar();

        JMenu menuGame = new JMenu("Игра");
        menuBarMain.add(menuGame);
        menuGame.add(createMenuItem("Новая", "ctrl+N", null, e -> {
            newGame();
        }));
        menuGame.add(createMenuItem("Параметры", "ctrl+P", null, e -> {
            dialogParams.updateView();
            dialogParams.setVisible(true);
        }));
        menuGame.addSeparator();
        menuGame.add(createMenuItem("Выход", "ctrl+X", null, e -> {
            System.exit(0);
        }));

        JMenu menuView = new JMenu("Вид");
        menuBarMain.add(menuView);
        menuView.add(createMenuItem("Подогнать размер окна", null, null, e -> {
            updateWindowSize();
        }));
        menuView.addSeparator();
        SwingUtils.initLookAndFeelMenu(menuView);

        JMenu menuHelp = new JMenu("Справка");
        menuBarMain.add(menuHelp);
        menuHelp.add(createMenuItem("Правила", "ctrl+R", null, e -> {
            SwingUtils.showInfoMessageBox("Здесь должно быть краткое описание правил ...", "Правила");
        }));
        menuHelp.add(createMenuItem("О программе", "ctrl+A", null, e -> {
            SwingUtils.showInfoMessageBox(
                    "Игра Перекраска" +
                            "\n\nАвтор: Меркушина Д. Г." +
                            "\nE-mail: dmerkusina@gmail.com",
                    "О программе"
            );
        }));

        return menuBarMain;
    }

    private void updateWindowSize() {
        int menuSize = this.getJMenuBar() != null ? this.getJMenuBar().getHeight() : 0;
        SwingUtils.setFixedSize(
                this,
                tableGameField.getWidth() + 2 * DEFAULT_GAP + 60,
                tableGameField.getHeight() + panelMain.getY() + labelStatus.getHeight() +
                        menuSize + 1 * DEFAULT_GAP + 2 * DEFAULT_GAP + 60
        );
        this.setMaximumSize(null);
        this.setMinimumSize(null);
    }

    private void updateView() {
        AllMoves.setText("Ходов выполнено: " + moves);
        boolean flag = false;
        int color = arr[0][0];
        for (int[] ints : arr) {
            for (int j = 0; j < arr[0].length; j++) {
                if (ints[j] == color) {
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            }
        }
        if (moves == 0.0){
            game.state = Game.GameState.NOT_STARTED;
        } else if (flag){
            game.state = Game.GameState.WIN;
        } else if (moves > 0.0 && moves < 25.0){
            game.state = Game.GameState.PLAYING;
        } else if (moves >= 25.0){
            game.state = Game.GameState.FAIL;
        }
        if (game.state == Game.GameState.PLAYING) {
            labelStatus.setText("Игра идёт..");
        }else if (game.state == Game.GameState.NOT_STARTED){
            labelStatus.setText("Начните игру!");
        } else {
            labelStatus.setText("");
            if (game.state == Game.GameState.WIN){
                labelStatus.setText("Победа! :)" + "\nНачните новую игру");
                GameStart.setBackground(new Color(178, 34, 34));
                Green.setEnabled(false);
                Blue.setEnabled(false);
                Yellow.setEnabled(false);
                Red.setEnabled(false);
                Violet.setEnabled(false);
                Orange.setEnabled(false);
            }else if (game.state == Game.GameState.FAIL){
                labelStatus.setText("Поражение :(" + "\nНачните новую игру");
                GameStart.setBackground(new Color(178, 34, 34));
                Green.setEnabled(false);
                Blue.setEnabled(false);
                Yellow.setEnabled(false);
                Red.setEnabled(false);
                Violet.setEnabled(false);
                Orange.setEnabled(false);
            }
        }
        tableGameField.repaint();
    }



    private void paintCell(int row, int column, Graphics2D g2d, int cellWidth, int cellHeight) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color color = new Color(colors[Integer.parseInt(tableGameField.getValueAt(row, column).toString())][0], colors[Integer.parseInt(tableGameField.getValueAt(row, column).toString())][1], colors[Integer.parseInt(tableGameField.getValueAt(row, column).toString())][2]);
        g2d.setColor(color);
        int size = Math.min(cellWidth, cellHeight);
        g2d.fillRect(0, 0, size, size);
    }


    private void newGame() {
        game.newGame(params.getRowCount(), params.getColCount(), params.getColorCount());
        JTableUtils.resizeJTable(tableGameField,
                game.getRowCount(), game.getColCount(),
                tableGameField.getRowHeight(), tableGameField.getRowHeight()

        );
        moves = 0;
        updateView();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, 10));
        final JScrollPane scrollPane1 = new JScrollPane();
        panelMain.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tableGameField = new JTable();
        scrollPane1.setViewportView(tableGameField);
        AllMoves = new JLabel();
        AllMoves.setText("Label");
        labelStatus = new JLabel();
        labelStatus.setText("Label");
        panelMain.add(labelStatus, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

}
