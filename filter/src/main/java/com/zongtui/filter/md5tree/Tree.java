package com.zongtui.filter.md5tree;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: Tree <br/>
 * Function:  树结构 <br/>
 * Date:2015-4-9上午10:31:10
 *
 * @author Jason
 * @since JDK 1.7
 */
public class Tree {

    //根节点
    private Node root;

    public Tree() {
        this.root = new Node();
    }

    //节点类
    private class Node {
        private int count;
        //记录子节点
        private Map<Character, Node> son;

        Node() {
            count = 0;
            son = new HashMap<Character, Node>();
        }
    }

    //插入
    public void add(String s) {
        Node node = root;
        char[] letters = s.toCharArray();
        for (char c : letters) {
            if (!node.son.containsKey(c)) {
                node.son.put(c, new Node());
            }
            node = node.son.get(c);
        }
        node.count++;
    }

    //是否含有字符串
    public boolean contain(String word) {
        Node node = root;
        char[] letters = word.toCharArray();
        for (char c : letters) {
            if (!node.son.containsKey(c)) {
                return false;
            } else {
                node = node.son.get(c);
            }
        }
        return true;
    }
}
