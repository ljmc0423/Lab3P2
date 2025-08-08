/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab3p2;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;

/**
 *
 * @author ljmc2
 */

public class Lab3P2 extends JFrame {

    private SudokuLogica logica;
    private JTextField[][] cells = new JTextField[SudokuLogica.SIZE][SudokuLogica.SIZE];
    private int intentosRestantes = 5; // NUEVO: contador de intentos

    public Lab3P2() {
        logica = new SudokuLogica();
        GUI();
    }

    private void GUI() {
        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));
        add(createBoardPanel(), BorderLayout.CENTER);
        add(createControlPanel(), BorderLayout.EAST);
        setSize(720, 540);
        setLocationRelativeTo(null);
    }

    private JPanel createBoardPanel() {
        JPanel board = new JPanel(new GridLayout(3, 3));
        board.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        for (int br = 0; br < 3; br++) {
            for (int bc = 0; bc < 3; bc++) {
                JPanel block = new JPanel(new GridLayout(3, 3));
                block.setBorder(new LineBorder(Color.BLACK, 2));
                for (int r = br * 3; r < br * 3 + 3; r++) {
                    for (int c = bc * 3; c < bc * 3 + 3; c++) {
                        JTextField tf = new JTextField();
                        tf.setHorizontalAlignment(JTextField.CENTER);
                        tf.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
                        tf.setBorder(new LineBorder(Color.GRAY, 1));
                        final int rr = r, cc = c;
                        tf.addKeyListener(new KeyAdapter() {
                            @Override
                            public void keyTyped(KeyEvent e) {
                                if (!tf.isEditable()) {
                                    e.consume();
                                    return;
                                }
                                char ch = e.getKeyChar();
                                if (ch < '1' || ch > '9') {
                                    e.consume();
                                } else {
                                    tf.setText(String.valueOf(ch));
                                    logica.set(rr, cc, ch - '0');
                                    e.consume();
                                }
                            }
                        });
                        cells[r][c] = tf;
                        block.add(tf);
                    }
                }
                board.add(block);
            }
        }
        return board;
    }

    private JPanel createControlPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JButton resetBtn = new JButton("Limpiar tablero");
        resetBtn.setAlignmentX(CENTER_ALIGNMENT);
        resetBtn.addActionListener(e -> {
            logica.cargar(vacio(), vacio());
            refrescar();
            intentosRestantes = 5;
            habilitarCeldas(true);
        });

        JButton loadBtn = new JButton("Cargar problema");
        loadBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadBtn.addActionListener(e -> {
            logica.cargar(problema(), solucionProblema());
            refrescar();
            intentosRestantes = 5;
            habilitarCeldas(true);
        });

        JButton validateBtn = new JButton("Validar");
        validateBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        validateBtn.addActionListener(e -> {
            if (logica.resuelto()) {
                JOptionPane.showMessageDialog(this, "¡Correcto! Has resuelto el Sudoku.");
            } else {
                marcarErrores();
                intentosRestantes--;
                if (intentosRestantes > 0) {
                    JOptionPane.showMessageDialog(this, "Aún no es correcto. Intentos restantes: " + intentosRestantes);
                } else {
                    JOptionPane.showMessageDialog(this, "Has perdido. No te quedan intentos.");
                    habilitarCeldas(false);
                }
            }
        });

        JButton giveUpBtn = new JButton("Rendirse");
        giveUpBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        giveUpBtn.addActionListener(e -> {
            logica.revelarSolucion();
            refrescar();
            habilitarCeldas(false);
        });

        p.add(loadBtn);
        p.add(Box.createVerticalStrut(10));
        p.add(validateBtn);
        p.add(Box.createVerticalStrut(10));
        p.add(giveUpBtn);
        p.add(Box.createVerticalStrut(10));
        p.add(resetBtn);
        p.add(Box.createVerticalStrut(10));

        return p;
    }

    private void marcarErrores() {
        for (int r = 0; r < SudokuLogica.SIZE; r++) {
            for (int c = 0; c < SudokuLogica.SIZE; c++) {
                int valor = logica.get(r, c);
                if (valor == 0) {
                    cells[r][c].setBackground(Color.WHITE);
                } else if (valor == logica.getSolucion(r, c)) {
                    cells[r][c].setBackground(Color.GREEN);
                    cells[r][c].setEditable(false); // bloquea celda correcta
                } else {
                    cells[r][c].setBackground(Color.PINK);
                }
            }
        }
    }

    private void habilitarCeldas(boolean habilitar) {
        for (int r = 0; r < SudokuLogica.SIZE; r++) {
            for (int c = 0; c < SudokuLogica.SIZE; c++) {
                cells[r][c].setEditable(habilitar);
            }
        }
    }

    private void refrescar() {
        for (int r = 0; r < SudokuLogica.SIZE; r++) {
            for (int c = 0; c < SudokuLogica.SIZE; c++) {
                int v = logica.get(r, c);
                cells[r][c].setText(v == 0 ? "" : String.valueOf(v));
                cells[r][c].setBackground(Color.WHITE);
            }
        }
    }

    private int[][] problema() {
        return new int[][]{
            {0, 0, 8, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 3, 0, 0, 2},
            {0, 7, 0, 0, 8, 0, 0, 1, 0},
            {0, 0, 0, 6, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 4, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 1, 0, 0, 6, 0, 0, 7, 0},
            {5, 0, 0, 2, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 3, 0, 0}
        };
    }

    private int[][] solucionProblema() {
        return new int[][]{
            {1, 2, 8, 4, 3, 7, 6, 5, 9},
            {4, 5, 6, 1, 9, 3, 7, 8, 2},
            {9, 7, 3, 5, 8, 6, 2, 1, 4},
            {3, 4, 1, 6, 2, 9, 8, 7, 5},
            {2, 6, 5, 7, 4, 8, 1, 9, 3},
            {7, 8, 9, 3, 5, 1, 4, 2, 6},
            {8, 1, 2, 9, 6, 4, 5, 7, 8},
            {5, 3, 4, 2, 1, 9, 9, 6, 7},
            {6, 9, 7, 8, 7, 5, 3, 4, 1}
        };
    }

    private int[][] vacio() {
        return new int[9][9];
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Lab3P2 ventana = new Lab3P2();
            ventana.setVisible(true);
        });
    }
}
