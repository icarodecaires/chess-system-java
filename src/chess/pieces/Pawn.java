package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;


public class Pawn extends ChessPiece {

	public Pawn(Board board, Color color) {
		super(board, color);
		
	}

	@Override
	public boolean[][] possibleMoves() {
		//cria uma matriz com a mesma dimensão do tabuleiro com todas as posições marcadas como falsa
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0,0);		
		
		//Movimentos peão branco
		if (getColor() == Color.BRANCO) {
			
			//testando o movimento a frente
			p.setValues(position.getRow() - 1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//testando o primeiro movimento de saida com o salto de duas casas
			p.setValues(position.getRow() - 2, position.getColumn());
			Position p2 = new Position(position.getRow() - 1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0){
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//testa peças adversárias na diagonal esquerda
			p.setValues(position.getRow() - 1, position.getColumn() -1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//testa peças adversárias na diagonal esquerda
			p.setValues(position.getRow() - 1, position.getColumn() +1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
		//movimentos peão preto
		else {
			//testando o movimento a frente
			p.setValues(position.getRow() + 1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//testando o primeiro movimento de saida com o salto de duas casas
			p.setValues(position.getRow() + 2, position.getColumn());
			Position p2 = new Position(position.getRow() + 1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0){
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//testa peças adversárias na diagonal esquerda
			p.setValues(position.getRow() + 1, position.getColumn() -1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//testa peças adversárias na diagonal esquerda
			p.setValues(position.getRow() + 1, position.getColumn() +1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
		
		
		return mat;
	}
	@Override
	public String toString() {
		return "P";
	}
	

}
