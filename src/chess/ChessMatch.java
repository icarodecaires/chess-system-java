package chess;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;


public class ChessMatch {
	
	private int turn;
	private Color currentPlayer;
	private Board board;
	
	//lista de peças que estão no tabuleiro
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	//lista de peças capturadas
	private List<Piece> capturedPieces = new ArrayList<>();

	public ChessMatch() {
		// Definindo o tamnho do tabuleiro
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.BRANCO;
		initialSetup();
	}

	public ChessPiece[][] getPieces() {

		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	//Retorna uma matriz de movimentos possiveis
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		
		return board.piece(position).possibleMoves();
	}
	
	//Operação para mover a peçã no tabuleiro
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
		//validando posição de origem
		validateSourcePosition(source);
		
		//validando posição de destino
		validateTargetPosition(source, target);
	
		Piece capturedPiece = makeMove(source,target);
		
		nextTurn();
		return (ChessPiece) capturedPiece;
	}
	
	//movendo a peça no tabuleiro
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		
		//Caso o movimento retorne uma peça capturada esta sera removida da lista de peças do tabuleiro e adicionada a lista de peças capturadas
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		board.placePiece(p, target);
		
		return capturedPiece;
	}
	
	//validando se a posição origem existe peça
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("Nao existe peça na posicao de origem");
		}
		
		if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
			throw new ChessException("A Peca Escolhida nao e tua");
		}
		
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("Nao existe Movimentos Possiveis para a peca escolhida");
		}
	}
	
	//validando a posição destino se existe peça
	public void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("A Peca escolhida nao pode se mover para a posicao de destino");
		}		
	}
	
	
	//Alterna a vez de cada jogador
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.BRANCO) ? Color.PRETO : Color.BRANCO;
	}

	// Adiciona a peça em determinada posição do tabuleiro
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}

	// Iniciar uma partida
	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.BRANCO));
		placeNewPiece('c', 2, new Rook(board, Color.BRANCO));
		placeNewPiece('d', 2, new Rook(board, Color.BRANCO));
		placeNewPiece('e', 2, new Rook(board, Color.BRANCO));
		placeNewPiece('e', 1, new Rook(board, Color.BRANCO));
		placeNewPiece('d', 1, new King(board, Color.BRANCO));

		placeNewPiece('c', 7, new Rook(board, Color.PRETO));
		placeNewPiece('c', 8, new Rook(board, Color.PRETO));
		placeNewPiece('d', 7, new Rook(board, Color.PRETO));
		placeNewPiece('e', 7, new Rook(board, Color.PRETO));
		placeNewPiece('e', 8, new Rook(board, Color.PRETO));
		placeNewPiece('d', 8, new King(board, Color.PRETO));
	}
}
