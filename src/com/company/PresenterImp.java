package com.company;


import static com.company.Starter.growth;
import static com.company.Starter.rooting;

public class PresenterImp implements Presenter {
    Field field;
    ViewImpl view;

    public PresenterImp(Field field, ViewImpl view) {
        this.field = field;
        this.view = view;
        view.setPresenter(this);
        start();
    }

    private void start(){
       rooting(field);
       growth(field);
       field.shuffle();
       for (int i = 0; i < field.field.length; i++) {
           for (int j = 0; j < field.field.length; j++) {
               view.drawCell(i, j, mapCellToImageFileName(field.getField()[i][j]));
           }
       }
       field.print();
       field.draw();
   }

    @Override
    public void onCellClick(int row, int column) {
        field.turnSquare(row, column);
        field.draw();
        view.drawCell(row, column, mapCellToImageFileName(field.getField()[row][column]));
        for (int i = 0; i < field.field.length; i++) {
            for (int j = 0; j < field.field.length; j++) {
                view.drawCell(i, j, mapCellToImageFileName(field.getField()[i][j]));
            }
        }
        var victory = field.checkVictory();
        if(victory){
            view.victory();
        }
    }

    private String mapCellToImageFileName(Cell cell) {
        StringBuilder stringBuilder = new StringBuilder();
        if(!cell.isAlive){
            stringBuilder.append('d');
        }
        if(cell.isNorth){
            stringBuilder.append('n');
        }
        if(cell.isEast){
            stringBuilder.append('e');
        }
        if(cell.isSouth){
            stringBuilder.append('s');
        }
        if(cell.isWest){
            stringBuilder.append('w');
        }

        return stringBuilder.toString();
    }

}
