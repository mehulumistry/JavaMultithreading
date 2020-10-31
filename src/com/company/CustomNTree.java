package com.company;

/**
 * Date: 9/20/20
 * Time: 1:38 PM
 */

/**
 * N-Array Tree can have number of children
 * Traversal -> If Binary tree then you have 4 traversals possible
 *              -> PreOrder
 *              -> InOrder
 *              -> PostOrder
 *              -> LevelOrder
 *
 *              For binary tree -> 2 child possible for each node and traversal -> 3 + 1(level traversal)
 *              Fro N tree -> N+1 traversal and + 1 for level -> total N + 2 traversal
 *
 *         *pre(t1) (0) *post(t2)
 *               / *in \
 *             (1)     (2) *level(t4)
 *            /  \     /  \
 *         (3)   (4) (5)  (6)
 *
 *
 *                    *1 () *4
 *                     / | \
 *                   / *2|*3\ *level(5)
 *                 /     |   \
 *                ()     ()   ()
 *               /|\    /|\   /|\
 *             ()()() ()()() ()()()
 *
 *
 *
* */
public class CustomNTree {
}
