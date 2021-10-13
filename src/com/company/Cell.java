package com.company;

public class Cell {
    boolean root;
    int column;
    int row;
    boolean taken; //if the cell is available for filling
    int exit;
    boolean maxedOut; // maximal number of exits achieved
    boolean isNorth;
    boolean isEast;
    boolean isSouth;
    boolean isWest;
    boolean isAlive;

    public Cell(int row, int column) {
        this.root = false;
        this.column = column;
        this.row = row;
        this.taken = false;
        this.exit = 0;
        this.isNorth = false;
        this.isEast = false;
        this.isSouth = false;
        this.isWest = false;
        this.maxedOut = false;
        this.isAlive = false;

    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    public boolean isRoot() {
        return root;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public int getCol() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void setNorth(boolean north) {
        isNorth = north;
    }

    public void setEast(boolean east) {
        isEast = east;
    }

    public void setSouth(boolean south) {
        isSouth = south;
    }

    public void setWest(boolean west) {
        isWest = west;
    }

    public void setMaxedOut(boolean maxedOut) {
        this.maxedOut = maxedOut;
    }

    public String getCode() {
        String code = new String();
        if (this.isAlive) {
            code += "1";
        } else {
            code += "0";
        }
        if (this.isNorth) {
            code += "1";
        } else {
            code += "0";
        }
        if (this.isEast) {
            code += "1";
        } else {
            code += "0";
        }
        if (this.isSouth) {
            code += "1";
        } else {
            code += "0";
        }
        if (this.isWest) {
            code += "1";
        } else {
            code += "0";
        }

        return code;
    }

    public void turn() {

        boolean tempNorth = isNorth;
        boolean tempEast = isEast;
        boolean tempSouth = isSouth;
        boolean tempWest = isWest;
        isNorth = tempWest;
        isEast = tempNorth;
        isSouth = tempEast;
        isWest = tempSouth;
    }


}
