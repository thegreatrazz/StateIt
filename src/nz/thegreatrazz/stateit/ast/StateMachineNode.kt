package nz.thegreatrazz.stateit.ast

class StateMachineNode(val machine: MachineNode) : StateNode(machine.name, allTransitions(machine)) {
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
