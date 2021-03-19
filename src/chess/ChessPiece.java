package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
	
	private Color color;
	private int moveCount;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	
	//Incrementa a quantidade de movimentos da peça
	public void increaseMoveCount() {
		moveCount++;
	}
	
	//Devrementa a quantidade de movimentos da peça
		public void decreaseMoveCount() {
			moveCount--;
		}
	
	//Verifica se existe uma peça adversária em determinada posição
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p.getColor() != color;
	}
	
	//Retorna A posição da peça no formato do tabuleiro 
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
}

