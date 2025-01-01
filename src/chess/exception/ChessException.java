package chess.exception;

import boardgame.exception.BoardException;

public class ChessException extends BoardException {
    private static final long serialVersionUID = 1L;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public ChessException(String msg) {
        super(ANSI_RED + msg + ANSI_RESET);
    }
}