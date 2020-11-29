package com.company;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Date: 11/25/20
 * Time: 8:24 PM
 */

// Root Node is empty string

// Can make trie with
// 1) array and 2) map


public class Trie {

    private TrieNode root;

    public Trie() {
       this.root = new TrieNode();
    }


    /** Inserts a word into the trie. */
    public void insert(String word) {

       if(!word.isEmpty()) {
           char[] charArr = word.toCharArray();
           TrieNode current = this.root;
           for (char c : charArr) {
               current = current.getChildren().computeIfAbsent(c, t -> new TrieNode());
           }
           current.setEndOfWord(true);
       }

    }



    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        boolean result = searchHelper(word, false);
        System.out.println(result);
        return result;

    }

    public boolean searchHelper(String word, boolean startWith) {
        if(!word.isEmpty()) {
            char[] charArr = word.toCharArray();

            TrieNode curr = root;

            for (char c : charArr) {
                curr = curr.getChildren().get(c);
                if(curr == null) return false;
            }

            return startWith || curr.isEndOfWord();
        } else return false;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        boolean result = searchHelper(prefix, true);

        System.out.println(result);
        return result;

    }

    public boolean deleteNode(String word) {
        if(!word.isEmpty()) {
            char[] charArr = word.toCharArray();

            return deleteHelper(root, charArr, 0);
        } else return false;
    }

    public boolean deleteHelper(TrieNode node, char[] words, int index) {

        if(index == words.length) {
            if(!node.isEndOfWord()) return false;

            node.setEndOfWord(false);

            return node.getChildren().isEmpty();

        }

        TrieNode trie = node.getChildren().get(words[index]);
        if(trie == null) {
            return false;
        }

        boolean shouldDeleteNode = deleteHelper(trie, words, index + 1) && !node.isEndOfWord();

        if(shouldDeleteNode) {
            node.getChildren().remove(words[index]);
            return node.getChildren().isEmpty();
        }

        return false;
    }


}

class Test {
    public static void main(String[] args) {
        Trie obj = new Trie();
        obj.insert("apple");
        obj.insert("aaaaa");
        obj.insert("aa");
        obj.search("aa");
        obj.search("apple");

    }
}


class TrieNode {
    private final HashMap<Character, TrieNode> children = new HashMap<>();;
    private boolean endOfWord;

    Map<Character, TrieNode> getChildren() {
        return children;
    }

    boolean isEndOfWord() {
        return endOfWord;
    }

    void setEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }
}
