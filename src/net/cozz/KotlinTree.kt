package net.cozz

import java.util.*
import java.util.logging.Logger

private val LOGGER = Logger.getLogger(Tree::class.java.name)

class KotlinTree {
    private var root: Node? = null

    fun Tree(root: Node?) {
        this.root = root
    }


    fun find(key: Int): Node? {
        var current = root
        while (current!!.data != key) {
            current = if (key < current.data) {
                current.left
            } else {
                current.right
            }
            if (current == null) {
                return null
            }
        }
        return current
    }


    fun insert(data: Int) {
        val node = Node(data)
        if (root == null) {
            root = node
        } else {
            var current = root
            var parent: Node?
            while (find(data) == null) {
                parent = current
                if (data < current!!.data) {
                    current = current.left
                    if (current == null) {
                        parent!!.left = node
                        return
                    }
                } else {
                    current = current.right
                    if (current == null) {
                        parent!!.right = node
                        return
                    }
                }
            }
        }
    }


    fun delete(key: Int): Boolean {
        var current = root
        var parent = root
        var isLeftChild = true

        while (current!!.data != key) {
            parent = current
            if (key < current.data) {
                isLeftChild = true
                current = current.left
            } else {
                isLeftChild = false
                current = current.right
            }
            if (current == null) {
                return false
            }
        }

        if (current.left == null && current.right == null) {
            if (current === root) {
                root = null
            } else if (isLeftChild) {
                parent!!.left = null //disconnect node from parent
            } else {
                parent!!.right = null
            }
        } else if (current.right == null) { // if no right child, replace with left subtree
            if (current === root) {
                root = current.left
            } else if (isLeftChild) {
                parent!!.left = current.left
            } else {
                parent!!.right = current.left
            }
        } else if (current.left == null) {
            if (current === root) {
                root = current.right
            } else if (isLeftChild) {
                parent!!.left = current.right
            } else {
                parent!!.right = current.right
            }
        } else { // most complicated case with both left and right subtrees
            val successor = getSuccessor(current)
            if (current === root) {
                root = successor
            } else if (isLeftChild) {
                parent!!.left = successor
            } else {
                parent!!.right = successor
            }
            successor!!.left = current.left
        }
        return true
    }


    private fun getSuccessor(toDelete: Node?): Node? {
        var successorParent = toDelete
        var successor = toDelete
        var current = toDelete!!.right
        while (current != null) {
            successorParent = successor
            successor = current
            current = current.left
        }
        if (successor !== toDelete.right) {
            successorParent!!.left = successor!!.right
            successor.right = toDelete.right
        }
        return successor
    }


    fun preOrder(root: Node?) {
        if (root != null) {
            root.print()
            preOrder(root.left)
            preOrder(root.right)
        }
    }


    fun inOrder(root: Node?) {
        if (root != null) {
            inOrder(root.left)
            root.print()
            inOrder(root.right)
        }
    }


    fun postOrder(root: Node?) {
        if (root != null) {
            postOrder(root.left)
            postOrder(root.right)
            root.print()
        }
    }


    fun preOrder(root: CharNode?) {
        if (root != null) {
            root.print()
            preOrder(root.left)
            preOrder(root.right)
        }
    }


    fun inOrder(root: CharNode?) {
        if (root != null) {
            inOrder(root.left)
            root.print()
            inOrder(root.right)
        }
    }


    fun postOrder(root: CharNode?) {
        if (root != null) {
            postOrder(root.left)
            postOrder(root.right)
            root.print()
        }
    }


    fun displayTree() { // row by row traversal
        val globalStack = Stack<Node?>()
        globalStack.push(root)
        var indent = 32
        var isEmptyRow = false
        LOGGER.info(String.format("================================"))
        while (!isEmptyRow) {
            val localStack = Stack<Node?>()
            isEmptyRow = true
            for (i in 0 until indent) {
                LOGGER.info(" ")
            }
            while (!globalStack.isEmpty()) {
                val temp = globalStack.pop()
                if (temp != null) {
                    LOGGER.info(String.format("%d", temp.data))
                    localStack.push(temp.left)
                    localStack.push(temp.right)
                    if (temp.left != null || temp.right != null) {
                        isEmptyRow = false
                    } else {
                        LOGGER.info("--")
                        localStack.push(null)
                        localStack.push(null)
                    }
                    for (i in 0 until indent * 2 - 2) {
                        LOGGER.info(" ")
                    }
                }
            }
            LOGGER.info("")
            indent /= 2
            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop())
            }
        }
    }

    fun maxDepth(root: Node?): Int {
        return if (root == null) {
            0
        } else 1 + Math.max(maxDepth(root.left), maxDepth(root.right))
    }

    fun minDepth(root: Node?): Int {
        return if (root == null) {
            0
        } else 1 + Math.min(minDepth(root.left), minDepth(root.right))
    }

    fun isBalanced(root: Node?): Boolean {
        return maxDepth(root) - minDepth(root) <= 1
    }

    fun reconstruct(inOrder: CharArray, preOrder: CharArray): CharNode? {
        return reconstruct(inOrder, preOrder, 0, inOrder.size - 1)
    }

    var preOrderIndex = 0
    private fun reconstruct(inOrder: CharArray, preOrder: CharArray, startIndex: Int, endIndex: Int): CharNode? {
        if (startIndex > endIndex) {
            return null
        }
        if (preOrderIndex >= preOrder.size) {
            return null
        }
        val node = CharNode(preOrder[preOrderIndex++])
        val inOrderIndex = find(inOrder, node.data)
        node.left = reconstruct(inOrder, preOrder, startIndex, inOrderIndex - 1)
        node.right = reconstruct(inOrder, preOrder, inOrderIndex + 1, endIndex)
        return node
    }

    private fun find(data: CharArray, target: Char): Int {
        return String(data).indexOf(target)
    }

    fun breadthFirstTraversal(root: Node?) {
        if (root == null) {
            return
        }
        val queue: Queue<Node?> = LinkedList()
        queue.add(root)
        while (!queue.isEmpty()) {
            val node = queue.remove()
            node!!.print()
            queue.offer(node.left) // https://softwareengineering.stackexchange.com/questions/190267/what-is-the-difference-of-the-add-and-offer-methods-of-javas-priorityqueue
            queue.offer(node.right)
        }
    }

    fun depthFirstTraversal(root: Node?) {
        inOrder(root)
    }

    private fun loadTree(arr: IntArray, start: Int, end: Int): Node? {
        if (end < start) {
            return null
        }
        val mid = (start + end) / 2
        val n = Node(arr[mid])
        n.left = loadTree(arr, start, mid - 1)
        n.right = loadTree(arr, mid + 1, end)
        return n
    }

    fun createBalancedTree(arr: IntArray): Node? {
        arr.sort()
        return loadTree(arr, 0, arr.size - 1)
    }


    fun nestedRecursionExample(n: Int) : Int {
        println(n)
        if (n > 100) {
            return n - 10
        }

        return nestedRecursionExample(nestedRecursionExample(n + 11))
    }


    class Node(var data: Int) {
        var left: Node? = null
        var right: Node? = null
        override fun equals(o: Any?): Boolean {
            if (this === o) return true
            if (o !is Node) return false
            return if (data != o.data) false else true
        }

        fun print() {
            LOGGER.info(String.format("{%d}", data))
        }

    }

    class CharNode(var data: Char) {
        var left: CharNode? = null
        var right: CharNode? = null
        override fun equals(o: Any?): Boolean {
            if (this === o) return true
            if (o !is Node) return false
            return if (data.toInt() != o.data) false else true
        }

        fun print() {
            print("$data ")
        }

    }

    class Graph internal constructor(var data: Char) {
        var adjacent: List<Graph>

        init {
            adjacent = LinkedList()
        }
    }

}