package org.rshh;

import javax.swing.*;
import java.awt.*;

/**
 * snake game start
 * @author zsw
 * @date 2022/3/5
 */
public class App extends JFrame {
    public App(String title) throws HeadlessException {
        super(title);
        this.setLocation(500, 300);
        this.setSize(550, 550);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        App app = new App("Snake");
        Container container = app.getContentPane();
        GamePanel panel = new GamePanel();
        container.add(panel);
    }
}
