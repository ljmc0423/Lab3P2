/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3p2;

/**
 *
 * @author ljmc2
 */

public class SudokuLogica extends LogicaAbstract {

    public static final int SIZE = 9;
    private int[][] tablero;
    private int[][] solucion;

    public SudokuLogica() {
        tablero = new int[SIZE][SIZE];
        solucion = new int[SIZE][SIZE];
        clear();
    }

    @Override
    public void clear() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                tablero[r][c] = 0;
            }
        }
    }

    @Override
    public void set(int row, int col, int val) {
        tablero[row][col] = val;
    }

    @Override
    public int get(int row, int col) {
        return tablero[row][col];
    }

    public int getSolucion(int row, int col) { // NUEVO: para comparar
        return solucion[row][col];
    }

    @Override
    public void cargar(int[][] puzzle, int[][] sol) {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                tablero[r][c] = puzzle[r][c];
                solucion[r][c] = sol[r][c];
            }
        }
    }

    @Override
    public boolean esValido(int row, int col, int val) {
        if (val < 1 || val > 9) {
            return false;
        }
        for (int c = 0; c < SIZE; c++) {
            if (tablero[row][c] == val && c != col) {
                return false;
            }
        }
        for (int r = 0; r < SIZE; r++) {
            if (tablero[r][col] == val && r != row) {
                return false;
            }
        }
        int br = (row / 3) * 3, bc = (col / 3) * 3;
        for (int r = br; r < br + 3; r++) {
            for (int c = bc; c < bc + 3; c++) {
                if (tablero[r][c] == val && (r != row || c != col)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean resuelto() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (tablero[r][c] != solucion[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void revelarSolucion() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                tablero[r][c] = solucion[r][c];
            }
        }
    }
}
