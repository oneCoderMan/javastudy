package com.java.study.algorithm.microsoft.m202405;

/**
 * @Author： yijun
 * @DATE: 2024/5/29 22:00
 * @Description
 * https://leetcode.cn/problems/implement-trie-prefix-tree/description/
 * 仅由小写英文字母组成
 */
public class Lc208 {
    public static void main(String[] args) {
        Lc208 lc208 = new Lc208();
        lc208.test();

    }

    public void test() {
        Trie trie = new Trie();
        String word = "apple";
        String prefix = "app";
        trie.insert(word);
        boolean param_2 = trie.search(word);
        boolean param_3 = trie.startsWith(prefix);
        System.out.println(param_2);
        System.out.println(param_3);
        trie.insert(prefix);
        System.out.println(trie.search(prefix));
    }

    class Trie {
        private Trie[] child;
        private boolean isEnd;

        public Trie() {
            child = new Trie[26];
            isEnd = false;
        }

        /**
         * 向前缀树中插入字符串 word
         * @param word
         */
        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chars = word.toCharArray();
            Trie node = this;
            for (int i = 0; i < chars.length; i++) {
                if (node.child[chars[i] - 'a'] == null) {
                    node.child[chars[i] - 'a'] = new Trie();
                }
                node = node.child[chars[i] - 'a'];
            }
            node.isEnd = true;
        }

        /**
         * 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；
         * 否则，返回 false 。
         * @param word
         * @return
         */
        public boolean search(String word) {
            if (word == null) {
                return true;
            }
            Trie node = this;
            char[] chars = word.toCharArray();
            boolean fit = true;
            for (int i = 0; i < chars.length; i++) {
                if (node.child[chars[i] - 'a'] != null) {
                    node = node.child[chars[i] - 'a'];
                } else {
                    fit = false;
                    break;
                }
            }
            return fit && node.isEnd;
        }

        /**
         * 如果之前已经插入的字符串 word 的前缀之一为 prefix ，
         * 返回 true ；否则，返回 false 。
         * @param prefix
         * @return
         */
        public boolean startsWith(String prefix) {
            if (prefix == null) {
                return true;
            }
            Trie node = this;
            char[] chars = prefix.toCharArray();
            boolean fit = true;
            for (int i = 0; i < chars.length; i++) {
                if (node.child[chars[i] - 'a'] != null) {
                    node = node.child[chars[i] - 'a'];
                } else {
                    fit = false;
                    break;
                }
            }
            return fit;

        }
    }
}
