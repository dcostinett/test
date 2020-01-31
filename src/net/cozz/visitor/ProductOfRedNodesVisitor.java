package net.cozz.visitor;

public class ProductOfRedNodesVisitor extends TreeVis {
    private long product = 1;
    private final int MODULO = 1000000007;

    @Override
    public int getResult() {
        return (int) product;
    }

    @Override
    public void visitNode(TreeNode node) {
        if (node.getColor().equals(Color.RED)) {
            product = (product * node.getValue()) % MODULO;
        }
    }

    @Override
    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor().equals(Color.RED)) {
            product = (product * leaf.getValue()) % MODULO;
        }
    }
}
