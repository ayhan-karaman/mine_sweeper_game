package com.example;

import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    private int rowNumber;
    private int colNumber;
    private int size;
    private int successResult = 0;
    private String[][] board;
    private String[][] map;

     public MineSweeper(int row, int col) {
        this.rowNumber = row;
        this.colNumber = col;
        this.size = row * col;
        this.board = new String[row][col];
        this.map = new String[row][col];
     }
     
     public void run()
     {
        createBoardAndMap();
        changeMap();
        System.out.println("\nMayınların Konumu");
        write(map);
        
        Scanner input = new Scanner(System.in);
        boolean runing = true;
        System.out.println("\n============================\n");
        System.out.println("Mayın Tarlası Oyununa Hoşgeldiniz\n");
        while (runing) {

            write(board);
            System.out.println("\n============================\n");
            System.out.print("Satır numarasını giriniz: ");
            int row = input.nextInt();
            System.out.print("Sütun numarasını giriniz: ");
            int col = input.nextInt();

            
            if ((row < 1 || row > rowNumber) || (col < 1 || col > colNumber)) {
                 System.out.println("Geçersiz Kordinat girdiniz!");
                 continue;
            }

            row--;
            col--;
            if(map[row][col] != "*")
            {
                checkMine(row, col);
                successResult++;
                if (successResult == (size - (size / 4))) {
                    System.out.println("\nTebrikler kazandınız :) ");
                     break;
                }
            }
            else
            {
                 runing = false;
                 System.out.println("\nÜzgünüm Mayına Bastınız :(\nOyun Bitti!");
            }


        }



     }


     private void checkMine(int row, int col)
     {
        int counter = 0;
        if (map[row][col] == "-") 
        {
            if((col > 0) && map[row][col -1] == "*")
                counter++;


            if((col < colNumber - 1) && map[row][col + 1] == "*")
                counter++;

            if((row > 0 ) && map[row -1][col] == "*")
                 counter++;
            if((row < rowNumber-1) && map[row + 1][col] == "*")
                 counter++;



            if((row > 0 && col > 0) && map[row - 1][col - 1] == "*")
                 counter++;
            if((row > 0 && col < colNumber - 1) && map[row - 1][col + 1] == "*")
                 counter++;



            if((row < rowNumber - 1 && col > 0 ) && map[row + 1][col - 1] == "*")
                 counter++;

            if((row < rowNumber - 1 && col < colNumber -1) && map[row + 1][col + 1] == "*")
                 counter++;
            
            board[row][col] = counter+"";
        }
     }

     private void changeMap()
     {
        int row, col, count = 0;
        Random rnd = new Random();
        while (count != (size / 4)) {
            row = rnd.nextInt(rowNumber);
            col = rnd.nextInt(colNumber);
            if (map[row][col] != "*") {
                map[row][col] = "*";
                count++;
            }
            
        }
     }
     
     private void createBoardAndMap()
     {
         for (int i = 0; i < rowNumber; i++) {
             for (int j = 0; j < colNumber; j++) {
                  board[i][j] = "-";
                  map[i][j] = "-";
             }
         }
     }

     private void write(String[][] arr)
     {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "  ");
            }
            System.out.println("");
        }
     }




}