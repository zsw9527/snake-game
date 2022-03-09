package org.rshh;

import java.util.LinkedList;
import java.util.List;

import static java.awt.event.KeyEvent.*;

/**
 * 蛇
 */
public class Snake {
    private List<Node> nodeList;

    public List<Node> getNodeList() {
        return nodeList;
    }

    public Snake() {
        this.nodeList = new LinkedList<>();

        Node head = new Node(400, 450, Direction.LEFT);
        nodeList.add(head);
    }

    //蛇移动 (每次就是向链表头部插入一个新节点，然后再删除尾部节点)
    public void move() {
        Node head = nodeList.get(0);

        Node newNode = null;
        switch (head.direction) {
            case LEFT:
                newNode = new Node(head.x - 50, head.y, Direction.LEFT);
                break;
            case RIGHT:
                newNode = new Node(head.x + 50, head.y, Direction.RIGHT);
                break;
            case TOP:
                newNode = new Node(head.x, head.y - 50, Direction.TOP);
                break;
            case DOWN:
                newNode = new Node(head.x, head.y + 50, Direction.DOWN);
                break;
            default:
                throw new RuntimeException("unknown direction!");
        }
        nodeList.add(0, newNode);
        nodeList.remove(nodeList.size() - 1);
    }

    //蛇改变方向
    public void changeDirection(int e) {
        Node head = nodeList.get(0);
        switch (e) {
            case VK_UP:
                if (head.direction != Direction.DOWN) {
                    head.direction = Direction.TOP;
                }
                break;
            case VK_DOWN:
                if (head.direction != Direction.TOP) {
                    head.direction = Direction.DOWN;
                }
                break;
            case VK_LEFT:
                if (head.direction != Direction.RIGHT) {
                    head.direction = Direction.LEFT;
                }
                break;
            case VK_RIGHT:
                if (head.direction != Direction.LEFT) {
                    head.direction = Direction.RIGHT;
                }
                break;
            default:
        }
    }

    public boolean hitWall() {
        Node head = nodeList.get(0);

        if (head.x < 0 || head.x > 450 || head.y < 0 || head.y > 500) {
            return true;
        }

        return false;
    }

    public static class Node {
        public int x;
        public int y;
        public Direction direction;

        public Node(int x, int y, Direction direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }

    public void grow() {
        Node head = nodeList.get(0);
        Node newNode = null;
        switch (head.direction) {
            case LEFT:
                newNode = new Node(head.x - 50, head.y, Direction.LEFT);
                break;
            case RIGHT:
                newNode = new Node(head.x + 50, head.y, Direction.RIGHT);
                break;
            case TOP:
                newNode = new Node(head.x, head.y - 50, Direction.TOP);
                break;
            case DOWN:
                newNode = new Node(head.x, head.y + 50, Direction.DOWN);
                break;
            default:
                throw new RuntimeException("unknown direction!");
        }
        nodeList.add(0, newNode);
    }
}
