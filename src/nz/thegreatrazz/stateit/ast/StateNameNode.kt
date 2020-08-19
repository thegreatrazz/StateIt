package nz.thegreatrazz.stateit.ast

import java.lang.StringBuilder

class StateNameNode(
    val name : String,
    val scope : List<String>
) : Node() {
    override fun toString(): String {
        val sb = StringBuilder()
        for (part in scope) {
            sb.append(part).append(":")
        }
        sb.append(name);
        return sb.toString()
    }
}