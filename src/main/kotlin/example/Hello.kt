package example

import org.w3c.dom.*
import kotlin.browser.document

fun main(args: Array<String>) {
    val main = document.getElementById("main")!!
    val allTexts = getAllNodesWithoutLinks(main).filterIsInstance<Text>().filterNot { it.wholeText.isBlank() }
    println("allTexts(ignore links): ${allTexts.size}")
    allTexts.forEachIndexed { index, node ->
        console.log("$index:")
        console.log(node)
    }
}

fun getAllNodesWithoutLinks(element: Element): List<Node> {
    val all = mutableListOf<Node>(element)
    var index = 0
    while (index < all.size) {
        val item = all[index]
        val children = item.childNodes
        for (i in 0 until children.length) {
            children[i]?.run {
                when (this) {
                    is HTMLAnchorElement -> Unit  // ignore links
                    else -> all.add(this)
                }
            }
        }
        index += 1
    }
    return all.toList()
}


