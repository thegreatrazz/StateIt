package nz.thegreatrazz.stateit.ast

class TransitionNode(val step: String, val state: StateNameNode) : Node() {
    override fun toString(): String {
        return "$step > $state"
    }
}