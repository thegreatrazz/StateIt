package nz.thegreatrazz.stateit.ast

/**
 * A node representing the entire source code.
 *
 * @param machines The top-level machines included in the source.
 */
class SourceNode(
    machines: List<MachineNode>
) : Node() {
    /**
     * The top-level machines inside the source.
     */
    val machines = ArrayList(machines)

    override fun toString(): String {
        val sb = StringBuilder("\n")
        for (machine in machines) sb.append(machine).append(";")
        return sb.toString()
    }
}