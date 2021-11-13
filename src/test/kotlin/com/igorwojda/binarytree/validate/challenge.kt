package com.igorwojda.binarytree.validate

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.util.*
import javax.print.attribute.IntegerSyntax
import kotlin.Comparator
import kotlin.collections.ArrayDeque
import kotlin.collections.HashSet

// ---------Tree------------
//
//           10
//          /
//         5
//        / \
//       1   6
//      / \
//     0  999
// --------------------------
// min of the right side should be greater than the current node's value.
// max of the left side should be smaller than the current node's value.
private fun isValidSearchBinaryTree(node: Node<Int>, min: Int = Integer.MIN_VALUE, max: Int = Integer.MAX_VALUE): Boolean {
    if (node.data > max || node.data < min) {
        return false
    }

    val validLeft = if (node.left == null) true else isValidSearchBinaryTree(node.left!!, min, node.data)
    val validRight = if (node.right == null) true else isValidSearchBinaryTree(node.right!!, node.data, max)

    return validLeft && validRight
}

private class Test {
    @Test
    fun `Validate recognizes a valid BST`() {
        // -- -------Tree------------
        //
        //           10
        //          /  \
        //         5    15
        //        /       \
        //       0         20
        // --------------------------

        val node = Node(10)
        node.insert(5)
        node.insert(15)
        node.insert(0)
        node.insert(20)

        isValidSearchBinaryTree(node) shouldBeEqualTo true
    }

    @Test
    fun `Validate recognizes an invalid BST`() {
        // -- -------Tree------------
        //
        //           10
        //          /  \
        //         5    15
        //        /       \
        //       0         20
        //        \
        //        999
        // --------------------------

        val node = Node(10)
        node.insert(5)
        node.insert(15)
        node.insert(0)
        node.insert(20)
        node.left?.left?.right = Node(999)

        isValidSearchBinaryTree(node) shouldBeEqualTo false
    }
}

private data class Node<E : Comparable<E>>(
    var data: E,
    var left: Node<E>? = null,
    var right: Node<E>? = null
) {
    fun insert(e: E) {
        if (e < data) { // left node
            if (left == null) {
                left = Node(e)
            } else {
                left?.insert(e)
            }
        } else if (e > data) { // right node
            if (right == null) {
                right = Node(e)
            } else {
                right?.insert(e)
            }
        }
    }
}
