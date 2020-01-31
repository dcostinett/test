package net.cozz.visitor;

public class FancyVisitor extends TreeVis {
    private int evenDepthNonLeaf = 0;
    private int greenLeafSum = 0;

    @Override
    public int getResult() {
        return Math.abs(evenDepthNonLeaf - greenLeafSum);
    }

    @Override
    public void visitNode(TreeNode node) {
        if (node.getDepth() % 2 == 0) {
            evenDepthNonLeaf += node.getValue();
        }
    }

    @Override
    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor().equals(Color.GREEN)) {
            greenLeafSum += leaf.getValue();
        }
    }
}
