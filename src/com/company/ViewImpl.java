package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


public class ViewImpl implements View{
    private final int width;
    private final int height;
    private JFrame frame;
    private JButton[][] buttons;
    private Presenter presenter;

    private final Map<String, BufferedImage> imageTable = new HashMap<>();

    private void addKeyToImageTable(String key) {
        try {
            imageTable.put(key, ImageIO.read(ClassLoader.getSystemResource("images/" + key + ".jpg")));
        } catch (Exception e) {
        }
    }

    public ViewImpl(int size) {
        for (int i = 1; i <= 64; i++) {
            StringBuilder key = new StringBuilder();
            if ((i & 0x20) != 0) key.append("f");
            if ((i & 0x10) != 0) key.append("d");
            if ((i & 0x8) != 0) key.append("n");
            if ((i & 0x4) != 0) key.append("e");
            if ((i & 0x2) != 0) key.append("s");
            if ((i & 0x1) != 0) key.append("w");
            if (key.length() > 0) addKeyToImageTable(key.toString());
        }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();
        frame = new JFrame("Level");

        initField(frame, size);
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void drawCell(int row, int column, String imageKey) {
        JButton button = buttons[row][column];
        button.setIcon(new ImageIcon(imageTable.get(imageKey)));
    }

    public void victory() {
        JOptionPane.showMessageDialog(frame,
                "Congratulations! You won!");
    }

    private void initField(JFrame frame, int size) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0,width, height);
        frame.setSize(600, 600);
        frame.getContentPane().setBackground(new java.awt.Color(255,204,229));
        frame.setLayout(new SpringLayout());
        JPanel centralPanel= new JPanel();
        centralPanel.setBackground(new java.awt.Color(255,204,229));
        centralPanel.setLayout(new SpringLayout());
        JButton button;
        buttons = new JButton[size][size];
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                button = new JButton(null, new ImageIcon(imageTable.get("d")));
                button.setPreferredSize(new Dimension(100, 100));
                frame.add(button, new SpringLayout.Constraints(Spring.constant(100*j), Spring.constant(100*i), Spring.constant(100), Spring.constant(100)));
                final int row = i;
                final int column = j;
                button.addActionListener(e -> presenter.onCellClick(row, column));
                buttons[row][column] = button;
            }
        }


        frame.setVisible(true);
    }
}
