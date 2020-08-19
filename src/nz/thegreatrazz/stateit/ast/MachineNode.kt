package nz.thegreatrazz.stateit.ast

import java.lang.StringBuilder

/**
 * A node representing a machine.
 */
class MachineNode(
    /**
     * The name of the machine.
     */
    val name: String,

    /**
     * The states the machine could be in.
     */
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