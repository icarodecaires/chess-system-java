package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//torre
public class Bishop extends ChessPiece {

	public Bishop(Board board, Color color) {
		super(board, color);
	}
	
	
	@Override
	public String toString() {
		return "B";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		//cria uma matriz com a mesma dimensão do tabuleiro com todas as posições marcadas como falsa
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0,0);		
		
		//NW - VALIDANDO MOVIMENTOS DIAGONAL ESQUERDA ACIMA
		//Enquanto existir posição válida e não existir peça na posição irá setar a posição da matriz como verdadeira
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() - 1);
		}
		
		//válida se a ultima posição que parou no laço é válida e se existe peça do oponente nela
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//VALIDANDO MOVIMENOS DIAGONAL DIREITA ACIMA
		//Enquanto existir posição válida e não existir peça na posição irá setar a posição da matriz como verdadeira
		p.setValues(position.getRow() -1, position.getColumn() + 1);
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() + 1);
		}
		
		//válida se a ultima posição que parou no laço é válida e se existe peça do oponente nela
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//VALIDANDO MOVIMENOS DIAGONAL DIREITA ABAIXO --------------------
		//Enquanto existir posição válida e não existir peça na posição irá setar a posição da matriz como verdadeira
		p.setValues(position.getRow() +1 , position.getColumn() +1);
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() + 1);
		}
		
		//válida se a ultima posição que parou no laço é válida e se existe peça do oponente nela
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		//VALIDANDO MOVIMENOS DIAGONAL ESQUERDA ACIMA ------------------
		//Enquanto existir posição válida e não existir peça na posição irá setar a posição da matriz como verdadeira
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() - 1);
		}
		
		//válida se a ultima posição que parou no laço é válida e se existe peça do oponente nela
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		return mat;
	}
	
}
