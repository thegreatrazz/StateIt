package nz.thegreatrazz.stateit.parser

import nz.thegreatrazz.stateit.ast.Node
import org.omg.CORBA.Environment
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import javax.swing.JFileChooser

object Parser {
    @JvmStatic
    fun parse(file: File): Node {
        val lineBoundary = HashSet<Int>()
        return ParserContext(FileInputStream(file)).parseSource()
    }

    @JvmStatic
    fun getLineStarts(stream: InputStream): Set<Int> {
        val lines = HashSet<Int>(listOf(0))

        var length = 0
        var lastChar = 0.toChar()
        while (true) {
            val c = stream.read()
            if (c < 0) break

            // get the char
            val char = c.toChar()

            // count the line if the last characters were either LF or CR,
            // but not CRLF
            if (lastChar == '\r' && char == '\n') {
                // handling Windows' line ending FTW
            } else if (lastChar == '\r' || lastChar == '\n') {
                lines.add(length)
            }

            // increment the length
            length++
            lastChar = char
        }

        return lines
    }

    @JvmStatic
    fun getLineForPosition(lines: Collection<Int>, position: Int): Int {
        return -1
    }
}

private fun main() {
    val fileChooser = JFileChooser()
    fileChooser.currentDirectory = File(System.getProperty("user.dir"))
    val result = fileChooser.showOpenDialog(null)
    if (result != JFileChooser.APPROVE_OPTION) {
        println("User cancelled.")
        return
    }

    try {
        println(Parser.parse(fileChooser.selectedFile))
    } catch (e: ParserException) {
        println("Message: ${e.message}")
        if (e.cause != null) println("Caused by ${e.cause}")
        println("Remaining Tokens:")
        for (token in e.remainingTokens) {
            println("- $token")
        }
        e.printStackTrace()
    }
    println()
}
