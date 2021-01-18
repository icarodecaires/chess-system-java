package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Rook;


public class ChessMatch {
	
	private int turn;
	private Color currentPlayer;
	private Board board;
	private boolean check;
	private boolean checkMate;
	
	//lista de pe�as que est�o no tabuleiro
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	//lista de pe�as capturadas
	private List<Piece> capturedPieces = new ArrayList<>();

	public ChessMatch() {
		// Definindo o tamnho do tabuleiro
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.BRANCO;
		check = false;
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
	
	public boolean getCheckMate() {
		return checkMate;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	//Retorna uma matriz de movimentos possiveis
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		
		return board.piece(position).possibleMoves();
	}
	
	//Opera��o para mover a pe�� no tabuleiro
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
		//validando posi��o de origem
		validateSourcePosition(source);
		
		//validando posi��o de destino
		validateTargetPosition(source, target);
	
		Piece capturedPiece = makeMove(source,target);
		
		//verifica se o prorio jogador se colocou em check
		if(testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("Voce Nao Pode se Colocar em Check");
		}
		
		//verifica se o oponente entrou em cheque
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		
		//verifica se o oponente entrou em cheque mate
		if (testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		}
		else {
			nextTurn();
		}
		
		return (ChessPiece) capturedPiece;
	}
	
	//movendo a pe�a no tabuleiro
	private Piece makeMove(Position source, Position target) {
		ChessPiece p = (ChessPiece) board.removePiece(source);
		p.increaseMoveCount();
		Piece capturedPiece = board.removePiece(target);
		
		//Caso o movimento retorne uma pe�a capturada esta sera removida da lista de pe�as do tabuleiro e adicionada a lista de pe�as capturadas
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		board.placePiece(p, target);
		
		return capturedPiece;
	}
	
	//Desfazer o movimento, caso o jogador fa�o um movimento ilegal este dever� ser desfeito
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		ChessPiece p = (ChessPiece) board.removePiece(target);
		p.decreaseMoveCount();
		board.placePiece(p, source);
		
		if(capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
	}
	
	//validando se a posi��o origem existe pe�a
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("Nao existe pe�a na posicao de origem");
		}
		
		if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
			throw new ChessException("A Peca Escolhida nao e tua");
		}
		
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("Nao existe Movimentos Possiveis para a peca escolhida");
		}
	}
	
	//validando a posi��o destino se existe pe�a
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
	
	// Devolve o Oponente de uma cor
	private Color opponent(Color color) {
		return (color == Color.BRANCO) ? Color.PRETO : Color.BRANCO;
	}
	
	//Localiza o Rei de determinada cor no tabuleiro
	private ChessPiece king(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() ==  color).collect(Collectors.toList());
		
		for (Piece p : list) {
			if (p instanceof King) {
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("Nao Existe um rei da cor: "+color+" No Tabuleiro ");
	}
	
	//Verifica se o rei de determinada cor esta em cheque
	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		//Retorna todas as pe�as no tabuleiro do oponente
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() ==  opponent(color)).collect(Collectors.toList());
		
		//verifica os movimentos possiveis de todas as pe�as do opnente se alguma delas pode dar cheque no rei
		for (Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			if(mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		
		return false;
	}
	
	//Teste se um jogador esta em cheque mate
	private boolean testCheckMate(Color color) {
		
		//se um jogador n esta em cheque ele tb n esta em cheque mate
		if (!testCheck(color)) {
			return false;
		}
		
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() ==  color).collect(Collectors.toList());
		
		for (Piece p : list ) {
			boolean[][] mat = p.possibleMoves();
			
			for (int i=0; i < board.getRows(); i++) {
				for (int j=0; j < board.getColumns(); j++) {
					if (mat[i][j]) {
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i,j);
						
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(color);
						undoMove(source,target,capturedPiece);
						
						if(!testCheck) {
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}
	

	// Adiciona a pe�a em determinada posi��o do tabuleiro
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}

	// Iniciar uma partida
	private void initialSetup() {
		placeNewPiece('a', 1, new Rook(board, Color.BRANCO));
		placeNewPiece('c', 1, new Bishop(board, Color.BRANCO));
		placeNewPiece('e', 1, new King(board, Color.BRANCO));
		placeNewPiece('f', 1, new Bishop(board, Color.BRANCO));
		placeNewPiece('h', 1, new Rook(board, Color.BRANCO));	
		placeNewPiece('a', 2, new Pawn(board, Color.BRANCO));
		placeNewPiece('b', 2, new Pawn(board, Color.BRANCO));
		placeNewPiece('c', 2, new Pawn(board, Color.BRANCO));
		placeNewPiece('d', 2, new Pawn(board, Color.BRANCO));
		placeNewPiece('e', 2, new Pawn(board, Color.BRANCO));
		placeNewPiece('f', 2, new Pawn(board, Color.BRANCO));
		placeNewPiece('g', 2, new Pawn(board, Color.BRANCO));
		placeNewPiece('h', 2, new Pawn(board, Color.BRANCO));
	
		placeNewPiece('a', 8, new Rook(board, Color.PRETO));
		placeNewPiece('c', 8, new Bishop(board, Color.PRETO));
		placeNewPiece('e', 8, new King(board, Color.PRETO));
		placeNewPiece('f', 8, new Bishop(board, Color.PRETO));
		placeNewPiece('h', 8, new Rook(board, Color.PRETO));
		placeNewPiece('a', 7, new Pawn(board, Color.PRETO));
		placeNewPiece('b', 7, new Pawn(board, Color.PRETO));
		placeNewPiece('c', 7, new Pawn(board, Color.PRETO));
		placeNewPiece('d', 7, new Pawn(board, Color.PRETO));
		placeNewPiece('e', 7, new Pawn(board, Color.PRETO));
		placeNewPiece('f', 7, new Pawn(board, Color.PRETO));
		placeNewPiece('g', 7, new Pawn(board, Color.PRETO));
		placeNewPiece('h', 7, new Pawn(board, Color.PRETO));
		
	}
	
		
}
