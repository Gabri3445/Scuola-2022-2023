import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GameOfLife extends JFrame {

    private JButton[][] cells;
    private boolean[][] alive;
    private final int ROWS = 50;
    private final int COLS = 50;

    public GameOfLife() {
        setTitle("Game of Life");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(ROWS, COLS));

        cells = new JButton[ROWS][COLS];
        alive = new boolean[ROWS][COLS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                cells[i][j] = new JButton();
                cells[i][j].addActionListener(new CellListener());
                add(cells[i][j]);
            }
        }
    }

    private class CellListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    if (e.getSource() == cells[i][j]) {
                        alive[i][j] = !alive[i][j];
                        if (alive[i][j]) {
                            cells[i][j].setBackground(java.awt.Color.black);
                        } else {
                            cells[i][j].setBackground(java.awt.Color.white);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        GameOfLife game = new GameOfLife();
        game.setVisible(true);
    }
}
