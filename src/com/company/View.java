package com.company;

import javax.swing.*;
import java.awt.*;

public interface View {
    void drawCell(int row, int column, String imagePath);
    void setPresenter(Presenter presenter);


}
