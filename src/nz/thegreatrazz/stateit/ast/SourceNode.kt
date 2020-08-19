package nz.thegreatrazz.stateit.ast

class SourceNode(machines: List<MachineNode>) : Node() {
    val machines = ArrayList(machines)

    override fun toString(): String {
        val sb = StringBuilder("\n")
        for (machine in machines) sb.append(machine).append(";")
        return sb.toString()
    }
}