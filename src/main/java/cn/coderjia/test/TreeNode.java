package cn.coderjia.test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.util.Objects.isNull;

/**
 * If you can access a binary tree {@link TreeNode} from its {@param root}, could your find the
 * {@return length} of the longest path  that appear in the array of given {@param nodeValues}.
 *
 * A "path" is a one-way linked list that starts from a parent node and ends with one of its
 * sub nodes.
 *
 * For example 1:
 *                            3
 *                          /  \
 *                         5   4
 *                       /  \   \
 *                      7  11   2
 *
 *      root = TreeNode{ val = 3, left=TreeNode(5), right = TreeNode(4) }
 *      nodeValues = int[] {5, 7, 11, 4}
 *
 *      result: 2
 *      longest path: 5 -> 7 and 5 -> 11
 *
 * For example 2:
 *                           11
 *                         /   \
 *                        1     5
 *                      /  \
 *                     3   8
 *                          \
 *                          4
 *
 *      root = TreeNode{ val = 11, left=TreeNode(1), right = TreeNode(5) }
 *      nodeValues = int[] {4, 1, 11, 5, 8}
 *
 *      result: 4
 *      longest path: 11 -> 1 -> 8 -> 4
 *
 *
 *
 * Please note:
 *      1. {@link TreeNode#val} of every {@link TreeNode} is unique.
 *      2. Values of {@link TreeNode#val} are random.
 *      3. Values in {@param nodeValues} are in random sequence, only part of tree nodes
 *         are given in the array.
 *      3. Every value present in {@param nodeValues} must have a valid {@link TreeNode}
 *         in the tree.
 *      4. Just return the {@return length} is enough, no need to find the exact path.
 *
 */
public class TreeNode {

    private static final List<String> PATH_STRS = new LinkedList<>();

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    // add data structure your need

    /**
     * Definition for a binary tree node
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public int longestPath(TreeNode root, int[] nodeValues) {
        // clear path
        PATH_STRS.clear();
        // find all path way
        findAllWay(root, nodeValues, new LinkedList<>());
        // split path
        final int[] max = {0};
        PATH_STRS.forEach(valStr -> {
            int len = valStr.split("@").length;
            if (len > max[0]) {
                max[0] = len;
            }
        });
        return max[0];
    }

    /**
     * 寻找树的所有路径，节点val不在nodeValues中过滤掉。
     */
    private static void findAllWay(TreeNode root, int[] nodeValues, LinkedList<Integer> path){

        if(root == null){
            return;
        }

        // 过滤节点val不在nodeValues中
        if (path.size() != 0 && containsVal(nodeValues, root.val)) {
            return;
        }

        path.add(root.val);

        if(isNull(root.left) && isNull(root.right)) {
            StringBuilder strBuilder = new StringBuilder();
            path.forEach(val -> strBuilder.append(val).append("@"));
            PATH_STRS.add(strBuilder.toString());
        } else {
            findAllWay(root.left, nodeValues, path);
            findAllWay(root.right, nodeValues, path);
        }
    }

    /**
     * 判断该val是否在nodeValues中
     */
    private static boolean containsVal(int[] nodeValues, int val){
        return Arrays.asList(nodeValues).contains(val);
    }

}