package com.company;

import static com.company.Starter.rooting;

public class Main {

    public static void main(String[] args) {
        int size = 5;
        PresenterImp presenter = new PresenterImp(new Field(size), new ViewImpl(size));

/*        Field field = new Field(5);
        rooting(field);

        field.print();
        Starter.growth(field);
        field.shuffle();
        field.draw();
        while (true) {
            if(field.turnSquare()){
                field.draw();
            }

        }*/


    }
}
