package org.rshh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.List;

/**
 * 游戏面板
 */
public class GamePanel extends JPanel implements KeyListener{
    private Snake snake;

    private Food food;

    //游戏结束标识
    public static boolean gameOver = false;

    public GamePanel() {
        this.setLocation(500, 300);
        this.setSize(500, 500);
        this.setVisible(true);

        snake = new Snake();
        food = new Food();

        run();

        this.addKeyListener(this);

        //如果不加这行无法监听按键事件
        this.setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {
        //清空上次绘制的图形
        g.setColor(Color.white);
        super.paint(g);

        //绘制地图
        drawMap(g);

        //绘制食物
        drawFood(g);

        //绘制蛇身
        drawSnake(g);

        if (gameOver) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("宋体", Font.BOLD, 36));
            g.drawString("游戏结束", 220, 220);
        }
    }

    private void drawSnake(Graphics g) {
        g.setColor(Color.BLUE);

        List<Snake.Node> nodeList = snake.getNodeList();
        Iterator<Snake.Node> it = nodeList.iterator();
        while (it.hasNext()) {
            Snake.Node node = it.next();
            g.fillRect(node.x, node.y, 50, 50);
        }
    }

    private void drawFood(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(food.getX(), food.getY(), 50, 50);
    }

    private void drawMap(Graphics g) {
        int a = 0, b = 0;
        for (int i = 0; i < 11; i++) {
            g.drawLine(0, a, 500, b);
            a += 50;
            b += 50;
        }
        a = 0;
        b = 0;
        for (int i = 0; i < 11; i++ ){
            g.drawLine(a, 0, b, 500);
            a += 50;
            b += 50;
        }
    }

    public void run() {
        new Thread(() -> {
            while (true) {
                //蛇游走
                snake.move();
                if (snake.hitWall()) {
                    gameOver = true;
                    repaint();
                    break;
                }

                //判断是否吃到食物
                if (hitFood(snake, food)) {
                    //蛇长大
                    snake.grow();
                    //食物重生
                    food.reBorn();
                }

                repaint();

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            repaint();

            System.out.println("game over!");
        }).start();
    }

    private boolean hitFood(Snake snake, Food food) {
        Snake.Node head = snake.getNodeList().get(0);
        return head.x == food.getX() && head.y == food.getY();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();//获取按下的键
        System.out.println("" + c);
        snake.changeDirection(c);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
