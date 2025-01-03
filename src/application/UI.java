package application;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UI {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static ChessPosition readChessPosition(Scanner sc) {
        try {
            String s = sc.nextLine();
            char column = s.toLowerCase().charAt(0);
            int row = Integer.parseInt(s.substring(1));
            return new ChessPosition(column, row);
        }
        catch (RuntimeException e) {
            throw new InputMismatchException("  Erro ao ler posicao de xadrez. Valores validos sao de a1 a h8.");
        }
    }

    public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
        printBoard(chessMatch.getPieces());
        printCapturedPieces(captured);
        System.out.println();
        System.out.println();
        System.out.println("  Turno: " + chessMatch.getTurn());
        if (!chessMatch.getCheckMate()) {
            if (Color.WHITE == chessMatch.getCurrentPlayer()) {
                System.out.println("  Aguardando jogador: " + chessMatch.getCurrentPlayer());
            }
            else {
                System.out.println("  Aguardando jogador: " + ANSI_YELLOW + chessMatch.getCurrentPlayer() + ANSI_RESET);
            }
            if (chessMatch.getCheck()) {
                System.out.println("  CHECK!");
            }
        }
        else {
            System.out.println("  CHECKMATE!");
            if (Color.WHITE == chessMatch.getCurrentPlayer()) {
                System.out.println("  Vencedor: " + chessMatch.getCurrentPlayer());
            }
            else {
                System.out.println("  Vencedor: " + ANSI_YELLOW + chessMatch.getCurrentPlayer() + ANSI_RESET);
            }
        }
    }

    public static void printBoard(ChessPiece[][] pieces) {
        System.out.println();
        System.out.println(ANSI_PURPLE + "      A   B   C   D   E   F   G   H" + ANSI_RESET);
        System.out.println("    +---+---+---+---+---+---+---+---+");

        for (int i=0; i<pieces.length; i++) {
            System.out.print("  " + ANSI_PURPLE + (8 - i) + ANSI_RESET + " |");
            for (int j=0; j< pieces.length; j++) {
                printPiece(pieces[i][j], false);
                System.out.print("|");
            }
            System.out.println(" " + ANSI_PURPLE + (8 - i) + ANSI_RESET);
            System.out.println("    +---+---+---+---+---+---+---+---+");
        }
        System.out.println(ANSI_PURPLE + "      A   B   C   D   E   F   G   H" + ANSI_RESET);
    }

    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
        System.out.println();
        System.out.println(ANSI_PURPLE + "      A   B   C   D   E   F   G   H" + ANSI_RESET);
        System.out.println("    +---+---+---+---+---+---+---+---+");

        for (int i=0; i<pieces.length; i++) {
            System.out.print("  " + ANSI_PURPLE + (8 - i) + ANSI_RESET + " |");
            for (int j=0; j< pieces.length; j++) {
                printPiece(pieces[i][j], possibleMoves[i][j]);
                System.out.print("|");
            }
            System.out.println(" " + ANSI_PURPLE + (8 - i) + ANSI_RESET);
            System.out.println("    +---+---+---+---+---+---+---+---+");
        }
        System.out.println(ANSI_PURPLE + "      A   B   C   D   E   F   G   H" + ANSI_RESET);
    }

    private static void printPiece(ChessPiece piece, boolean background) {
        if (background) {
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        if (piece == null) {
            System.out.printf("%3s", "   " + ANSI_RESET);
        }
        else {
            if (piece.getColor() == Color.WHITE) {
                System.out.printf(ANSI_WHITE + " %s " + ANSI_RESET, piece);
            } else {
                System.out.printf(ANSI_YELLOW + " %s " + ANSI_RESET, piece);
            }
        }
    }

    private  static void printCapturedPieces(List<ChessPiece> captured) {
        List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).toList();
        List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).toList();
        System.out.println();
        System.out.println("  Pecas capturadas:");
        System.out.print("  Brancas: ");
        System.out.print(ANSI_WHITE);
        System.out.println(Arrays.toString(white.toArray()));
        System.out.print(ANSI_RESET);
        System.out.print("  Pretas: ");
        System.out.print(ANSI_YELLOW);
        System.out.println(Arrays.toString(black.toArray()));
        System.out.print(ANSI_RESET);
    }
}