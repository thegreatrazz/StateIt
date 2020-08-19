package nz.thegreatrazz.stateit.parser

import nz.thegreatrazz.stateit.ast.*
import java.io.File
import java.io.InputStream
import java.util.*
import java.util.regex.Pattern

class ParserContext {
    companion object {
        private const val KW_MACHINE = "machine"
        private const val KW_STATE = "state"
        private const val KW_THIS = "this"
        private const val KW_GLOBAL = "_"

        private val OP_TRANSITION = Regex(">");
        private val OP_SCOPE = Regex(":")

        private const val OPERATORS = "{}(),>;:"
        private const val EOL = ";"

        private val LBRACE = Regex("\\{")
        private val RBRACE = Regex("}")
        private val LPARAN = Regex("\\(")
        private val RPARAN = Regex("\\)")
        private val IDENTIFIER = Regex("[A-Za-z_][A-Za-z0-9_]*")
    }

    private val scanner: Scanner

    constructor(input: File) {
        scanner = Scanner(input)
        scannerInit()
    }

    constructor(input: InputStream) {
        scanner = Scanner(input)
        scannerInit()
    }

    constructor(input: Readable) {
        scanner = Scanner(input)
        scannerInit()
    }

    constructor(input: String) {
        scanner = Scanner(input)
        scannerInit()
    }

    private fun scannerInit() {
        scanner.useDelimiter("\\s+|(?=[$OPERATORS])|(?<=[$OPERATORS])")
    }

    fun parseSource(): SourceNode {
        val machines = ArrayList<MachineNode>()
        while (scanner.hasNext()) {
            machines.add(parseMachine())
            require(EOL)
        }
        return SourceNode(machines)
    }

    fun parseMachine(): MachineNode {
        // take the name
        require(KW_MACHINE)
        val name = parseIdentifier()

        // get the machine contents
        val states = ArrayList<StateNode>()
        require(LBRACE)
        while (!scanner.hasNext(RBRACE.toPattern())) {
            when {
                scanner.hasNext(KW_MACHINE) -> states.add(StateMachineNode(parseMachine()))
                scanner.hasNext(KW_STATE) -> states.add(parseState())
                else -> fail("Expected \"machine\" or \"state\", got \"${scanner.next()}\"")
            }
            require(EOL)
        }
        require(RBRACE)

        return MachineNode(name, states)
    }

    fun parseState(): StateNode {
        // take the name
        require(KW_STATE)
        val name = parseIdentifier()

        // get the state contents
        val transitions = ArrayList<TransitionNode>()
        require(LBRACE)
        while (!scanner.hasNext(RBRACE.toPattern())) {
            transitions.add(parseTransition())
            require(EOL)
        }
        require(RBRACE)

        return StateNode(name, transitions)
    }

    fun parseStateName(): StateNameNode {
        val scope = ArrayList<String>()

        // if there is no name to begin with, make it the global scope
        if (checkFor(OP_SCOPE)) {
            scope.add("_")
            scanner.next()
        }

        // read in the first scope bit
        scope.add(parseIdentifier())

        // keep scanning until we run out of scopes
        while (checkFor(OP_SCOPE)) {
            require(OP_SCOPE)
            scope.add(parseIdentifier())
        }

        // the last one will be the name, take it out
        val name = scope.removeAt(scope.size - 1)

        return StateNameNode(name, scope)
    }

    fun parseTransition(): TransitionNode {
        val step = parseIdentifier()
        require(OP_TRANSITION)
        val state = parseStateName()

        return TransitionNode(step, state)
    }

    fun parseIdentifier(): String = require(IDENTIFIER)

    // utility functions

    private fun fail(message: String, cause: Throwable? = null): Nothing {
        // exhaust the remaining tokens
        val remainingTokens = ArrayList<String>()
        while (scanner.hasNext()) remainingTokens.add(scanner.next())

        // throw the exception
        throw ParserException(remainingTokens, message, cause)
    }

    private fun require(pattern: Pattern, message: String? = null): String =
        if (scanner.hasNext(pattern))
            scanner.next()
        else
            fail(
                message ?: (
                        "Expected \"$pattern\", got \"${if (scanner.hasNext()) scanner.next() else "EOF"}\".")
            )

    private fun require(string: String, message: String? = null) = require(Pattern.compile(string), message)
    private fun require(regex: Regex, message: String? = null) = require(regex.toPattern(), message)

    private fun checkFor(pattern: Pattern) = scanner.hasNext(pattern)
    private fun checkFor(string: String) = checkFor(Pattern.compile(string))
    private fun checkFor(regex: Regex) = checkFor(regex.toPattern())
}