package nz.thegreatrazz.stateit.parser

class ParserException(
    val remainingTokens: List<String>,
    message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause)
