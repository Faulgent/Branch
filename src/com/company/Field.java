package com.company;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

public class Field {
    Cell[][] field;
    static HashMap<String, String> template = new HashMap<>();


    public Field(int size) {
        this.field = new Cell[size][];
        for (int i = 0; i < size; i++) {
            Cell[] row = new Cell[size];
            for (int j = 0; j < size; j++) {
                row[j] = new Cell(i, j);
            }
            field[i] = row;
        }


        template.put("11100", "┗");
        template.put("10110", "┏");
        template.put("10011", "┓");
        template.put("11001", "┛");
        template.put("11110", "┣");
        template.put("10111", "┳");
        template.put("11011", "┫");
        template.put("11101", "┻");
        template.put("11010", "┃");
        template.put("10101", "━");
        template.put("11000", "╹");
        template.put("10100", "╺");
        template.put("10010", "╻");
        template.put("10001", "╸");
        template.put("10000", " ");
        template.put("11111", "x");
        template.put("01100", "└");
        template.put("00110", "┌");
        template.put("00011", "┐");
        template.put("01001", "┘");
        template.put("01110", "├");
        template.put("00111", "┬");
        template.put("01011", "┤");
        template.put("01101", "┴");
        template.put("01010", "│");
        template.put("00101", "─");
        template.put("01000", "╵");
        template.put("00100", "╶");
        template.put("00010", "╷");
        template.put("00001", "╴");
        template.put("00000", " ");
        template.put("01111", "x");
        template.put(null, " ");


    }

    public Cell[][] getField() {
        return field;
    }

    public Cell getRoot() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[i][j].root) {
                    return field[i][j];
                }

            }
        }
        return null;
    }

    public void print() {
        for (int i = 0; i < field.length; i++) {
            for (int a = 0; a < field.length; a++) {
                System.out.print(field[i][a].isRoot() ? "R" : "c");
            }
            System.out.println();
        }
    }

    public void draw() {
        joinedToRoot();
        System.out.println(" 01234");
        for (int i = 0; i < field.length; i++) {
            System.out.print(i);
            for (int a = 0; a < field.length; a++) {
                System.out.print(template.get(field[i][a].getCode()));
            }
            System.out.println();
        }
    }

    public boolean turnSquare(int row, int column) {

        if (field[row][column] != null) {
            field[row][column].turn();
            joinedToRoot();
            return true;
        }
        joinedToRoot();
        return false;
    }

    public boolean joinedToRoot() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j].setAlive(false);
            }
        }
        Cell cell = getRoot();
        cell.setAlive(true);
        Queue<Cell> queue = new ArrayDeque<>();
        queue.add(cell);
        while (!queue.isEmpty()) {
            cell = queue.poll();
            if (cell.isNorth && cell.getRow() > 0) {
                Cell northCell = getField()[cell.getRow() - 1][cell.getCol()];
                if (northCell.isSouth) {
                    if (!northCell.isAlive) {
                        northCell.setAlive(true);
                        queue.add(northCell);
                    }
                }
            }
            if (cell.isEast && cell.getCol() < 4) {
                Cell eastCell = getField()[cell.getRow()][cell.getCol() + 1];
                if (eastCell.isWest) {
                    if (!eastCell.isAlive) {
                        eastCell.setAlive(true);
                        queue.add(eastCell);
                    }
                }

            }
            if (cell.isSouth && cell.getRow() < 4) {
                Cell southCell = getField()[cell.getRow() + 1][cell.getCol()];
                if (southCell.isNorth) {
                    if (!southCell.isAlive) {
                        southCell.setAlive(true);
                        queue.add(southCell);
                    }
                }

            }
            if (cell.isWest && cell.getCol() > 0) {
                Cell westCell = getField()[cell.getRow()][cell.getCol() - 1];
                if (westCell.isEast) {
                    if (!westCell.isAlive) {
                        westCell.setAlive(true);
                        queue.add(westCell);
                    }
                }
            }
        }
        boolean victoryCondition =true;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[i][j].isTaken()&& !field[i][j].isAlive) {
                    victoryCondition = false;
                    return false;
                }
            }
        }
        if(victoryCondition){
            System.out.println("Victory!");
            return true;
        }
        return false;
    }

    public boolean checkVictory() {
        return joinedToRoot();
    }


    public void shuffle() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                for (int a = (int) (Math.random()*3); a == 0; a--) {
                    field[i][j].turn();
                }
            }
        }
    }
}

