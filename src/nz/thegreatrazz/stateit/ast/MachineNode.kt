package nz.thegreatrazz.stateit.ast

import java.lang.StringBuilder

class MachineNode(
    val name: String,
    val states: List<StateNode>
) : Node() {
    override fun toString(): String {
        val sb = StringBuilder("machine $name {\n")
        for (state in states) {
            sb.append(state.toString().prependIndent("  ")).append(";\n")
        }
        sb.append("}")
        return sb.toString()
    }
}