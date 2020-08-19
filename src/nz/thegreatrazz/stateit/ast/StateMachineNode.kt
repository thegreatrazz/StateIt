package nz.thegreatrazz.stateit.ast

/**
 * State wrapper for a machine to be used inside of another machine.
 */
class StateMachineNode(
    /**
     * The machine to act as a super-state.
     */
    val machine: MachineNode
) : StateNode(machine.name, allTransitions(machine)) {
    companion object {
        private fun allTransitions(machine: MachineNode): List<TransitionNode> {
            val transitions = ArrayList<TransitionNode>()
            for (state in machine.states) {
                for (transition in state.transitions) {
                    transitions.add(TransitionNode("__sub_${state.name}_${transition.step}", transition.state))
                }
            }
            return transitions
        }
    }

    override fun toString() = machine.toString()
}
