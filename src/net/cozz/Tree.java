package net.cozz;

import java.util.Stack;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 7/15/14
 * Time: 8:39 PM
 */
public class Tree {
    private static final Logger LOGGER = Logger.getLogger(Tree.class.getName());

    private Node root;

    public Tree(Node root) {
        this.root = root;
    }


    public Node find(int key) {

        Node current = root;

        while (current.data != key) {
            if (key < current.data) {
                current = current.left;
            } else {
                current = current.right;
            }

            if (current == null) {
                return null;
            }
        }

        return current;
    }


    public void insert(int data) {
        Node node = new Node(data);

        if (root == null) {
            root = node;
        } else {
            Node current = root;
            Node parent;
            while (find(data) == null) {
                parent = current;
                if (data < current.data) {
                    current = current.left;
                    if (current == null) {
                        parent.left = node;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = node;
                        return;
                    }
                }
            }
        }
    }


    public boolean delete(int key) {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;

        while (current.data != key) {
            parent = current;
            if (key < current.data) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }

            if (current == null) {
                return false;
            }
        }

        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.left = null;         //disconnect node from parent
            } else {
                parent.right = null;
            }
        } else if (current.right == null) { // if no right child, replace with left subtree
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else {                            // most complicated case with both left and right subtrees
            Node successor = getSuccessor(current);

            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }

            successor.left = current.left;
        }

        return true;
    }


    private Node getSuccessor(Node toDelete) {
        Node successorParent = toDelete;
        Node successor = toDelete;
        Node current = toDelete.right;

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.left;
        }

        if (successor != toDelete.right) {
            successorParent.left = successor.right;
            successor.right = toDelete.right;
        }

        return successor;
    }


    public void preOrder(Node root) {
        if (root != null) {
            root.print();
            preOrder(root.left);
            preOrder(root.right);
        }
    }


    public void inOrder(Node root) {
        if (root != null) {
            inOrder(root.left);
            root.print();
            inOrder(root.right);
        }
    }


    public void postOrder(Node root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            root.print();
        }
    }


    public void displayTree() {         // row by row traversal
        Stack<Node> globalStack = new Stack<Node>();
        globalStack.push(root);

        int indent = 32;
        boolean isEmptyRow = false;
        LOGGER.info(String.format("================================"));

        while (!isEmptyRow) {
            Stack<Node> localStack = new Stack<Node>();
            isEmptyRow = true;

            for (int i = 0; i < indent; i++) {
                LOGGER.info(" ");
            }

            while (!globalStack.isEmpty()) {
                Node temp = globalStack.pop();
                if (temp != null) {
                    LOGGER.info(String.format("%d",temp.data));
                    localStack.push(temp.left);
                    localStack.push(temp.right);

                    if (temp.left != null || temp.right != null) {
                        isEmptyRow = false;
                    } else {
                        LOGGER.info("--");
                        localStack.push(null);
                        localStack.push(null);
                    }

                    for (int i = 0; i < indent * 2 - 2; i++) {
                        LOGGER.info(" ");
                    }
                }
            }

            LOGGER.info("");
            indent /= 2;

            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }
        }
    }

    public static int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public static int minDepth(Node root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }

    public static boolean isBalanced(Node root) {
        return maxDepth(root) - minDepth(root) <= 1;
    }

    private static Node loadTree(int[] arr, int start, int end) {
        if (end < start) {
            return null;
        }

        int mid = (start + end) / 2;
        Node n = new Node(arr[mid]);
        n.left = loadTree(arr, start, mid - 1);
        n.right = loadTree(arr, mid + 1, end);

        return n;
    }

    public static Node createBalancedTree(int[] arr) {
        return loadTree(arr, 0, arr.length - 1);
    }


    public static class Node {
        int data;
        Node left;
        Node right;


        public Node(int data) {
            this.data = data;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;

            Node node = (Node) o;

            if (data != node.data) return false;

            return true;
        }


        public void print() {
            LOGGER.info(String.format("{%d}", data));
        }
    }
}
