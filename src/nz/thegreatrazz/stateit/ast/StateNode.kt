package nz.thegreatrazz.stateit.ast

import java.lang.StringBuilder

open class StateNode(val name: String, val transitions: List<TransitionNode>) : Node() {
    override fun toString(): String {
        val sb = StringBuilder("state $name {\n")
        for (transition in transitions) {
            sb.append("  $transition;\n")
        }
        sb.append("}")
        return sb.toString()
    }
}