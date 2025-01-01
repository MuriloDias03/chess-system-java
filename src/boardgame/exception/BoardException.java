package boardgame.exception;

public class BoardException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public BoardException(String msg) {
        super(ANSI_RED + msg + ANSI_RESET);
    }

}