package com.company;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class Starter {


    public static int[] rooting(Field field) {
        int row = (int) (Math.random() * 4);
        int column = (int) (Math.random() * 4);

        field.getField()[row][column].setRoot(true);
        field.getField()[row][column].setAlive(true);
        int[] coordinates = new int[2];
        coordinates[0] = row;
        coordinates[1] = column;
        return coordinates;

    }

    public static void growth(Field field) {
        Cell cell = field.getRoot();
        Queue <Cell> queue= new ArrayDeque<>();
        queue.add(cell);
        cell.setTaken(true);
        while(!queue.isEmpty()){
            Cell currentCell = queue.poll();
            if(currentCell.getRow()>0){
                Cell northCell = field.getField()[currentCell.getRow()-1][currentCell.getCol()];
                if(!northCell.isTaken() && growNorth(currentCell, northCell)){
                    queue.add(northCell);
                }
            }
            if(currentCell.getCol()<4){
                Cell eastCell = field.getField()[currentCell.getRow()][currentCell.getCol()+1];
                if(!eastCell.isTaken() && growEast(currentCell, eastCell)){
                    queue.add(eastCell);
                }

            }
            if(currentCell.getRow()<4){
                Cell southCell = field.getField()[currentCell.getRow()+1][currentCell.getCol()];
                if(!southCell.isTaken() && growSouth(currentCell, southCell)){
                    queue.add(southCell);
                }

            }
            if(currentCell.getCol()>0){
                Cell westCell = field.getField()[currentCell.getRow()][currentCell.getCol()-1];
                if(!westCell.isTaken() && growWest(currentCell, westCell)) {
                    queue.add(westCell);
                }

            }
        }
    }

    public static boolean growNorth(Cell cell, Cell cellDest) {
        if (shouldGrow()) {
            cell.setNorth(true);
            cellDest.setSouth(true);
            cellDest.setTaken(true);
            return true;
        }

        return false;

    }

    private static boolean shouldGrow() {
        return Math.random() * 2 > 1;
    }

    public static boolean growEast(Cell cell, Cell cellDest) {
        if (shouldGrow()) {
            cell.setEast(true);
            cellDest.setWest(true);
            cellDest.setTaken(true);
            return true;
        }

        return false;
    }
    public static boolean growSouth(Cell cell, Cell cellDest) {
        if (shouldGrow()) {
            cell.setSouth(true);
            cellDest.setNorth(true);
            cellDest.setTaken(true);
            return true;
        }

        return false;
    }
    public static boolean growWest(Cell cell, Cell cellDest) {
        if (shouldGrow()) {
            cell.setWest(true);
            cellDest.setEast(true);
            cellDest.setTaken(true);
            return true;
        }
        return false;
    }




}
