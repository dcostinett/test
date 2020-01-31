package net.cozz.visitor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {
    private static int[] values;
    private static Color[] colors;
    private static HashMap<Integer, Set<Integer>> map;

    public static Tree solve() {
        Scanner scanner = new Scanner(System.in);
        int nodeCount = scanner.nextInt();

        values = new int[nodeCount];
        colors = new Color[nodeCount];
        map = new HashMap<>(nodeCount);

        for (int i = 0; i < nodeCount; i++) {
            values[i] = scanner.nextInt();
        }
        for (int i = 0; i < nodeCount; i++) {
            colors[i] = scanner.nextInt() == 0 ? Color.RED : Color.GREEN;
        }

        for (int i = 0; i < nodeCount - 1; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();

            Set<Integer> uChildren = map.get(u);
            if (uChildren == null) {
                uChildren = new HashSet<>();
            }
            uChildren.add(v);
            map.put(u, uChildren);

            Set<Integer> vChildren = map.get(v);
            if (vChildren == null) {
                vChildren = new HashSet<>();
            }
            vChildren.add(u);
            map.put(v, vChildren);
        }

        TreeNode root = new TreeNode(values[0], colors[0], 0);
        addChildren(root, 1);

        return root;
    }

    private static void addChildren(TreeNode parent, Integer parentKey) {
        for (Integer key : map.get(parentKey)) {
            map.get(key).remove(parentKey);

            Set<Integer> children = map.get(key);
            Tree tree;
            if (children != null && !children.isEmpty()) {
                tree = new TreeNode(values[key - 1], colors[key - 1], parent.getDepth() + 1);
            } else {
                tree = new TreeLeaf(values[key - 1], colors[key - 1], parent.getDepth() + 1);
            }
            parent.addChild(tree);

            if (tree instanceof TreeNode) {
                addChildren((TreeNode) tree, key);
            }
        }
    }

    public static void main(String[] args) {
        Tree root = solve();
        SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
        ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
        FancyVisitor vis3 = new FancyVisitor();

        root.accept(vis1);
        root.accept(vis2);
        root.accept(vis3);

        int res1 = vis1.getResult();
        int res2 = vis2.getResult();
        int res3 = vis3.getResult();

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }
}
