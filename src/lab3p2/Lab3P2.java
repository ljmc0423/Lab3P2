/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab3p2;


/**
 *
 * @author ljmc2
 */
public class Lab3P2{

    private int[][] problema() {
        return new int[][] {
            {0,0,8, 0,0,0, 0,0,0},
            {0,0,0, 0,0,3, 0,0,2},
            {0,7,0, 0,8,0, 0,1,0},

            {0,0,0, 6,0,0, 0,0,0},
            {0,0,0, 0,4,0, 0,0,0},
            {0,0,0, 0,0,1, 0,0,0},

            {0,1,0, 0,6,0, 0,7,0},
            {5,0,0, 2,0,0, 0,0,0},
            {0,0,0, 0,0,0, 3,0,0}
        };
    }

    private int[][] solucionProblema() {
        return new int[][] {
            {1,2,8, 4,3,7, 6,5,9},
            {4,5,6, 1,9,3, 7,8,2},
            {9,7,3, 5,8,6, 2,1,4},

            {3,4,1, 6,2,9, 8,2,5},
            {2,6,5, 7,4,8, 1,9,3},
            {7,8,9, 3,5,1, 4,2,6},

            {8,1,2, 9,6,4, 5,7,8},
            {5,3,4, 2,1,9, 9,6,7},
            {6,9,7, 8,7,5, 3,4,1}
        };
    }

    public static void main(String[] args) {
        
    }
    
}
