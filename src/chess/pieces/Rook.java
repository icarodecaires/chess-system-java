package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//torre
public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}
	
	
	@Override
	public String toString() {
		return "T";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		//cria uma matriz com a mesma dimensão do tabuleiro com todas as posições marcadas como falsa
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0,0);		
		
		//VALIDANDO MOVIMENTOS PARA CIMA DO TABULEIRO --------------------------
		//Enquanto existir posição válida e não existir peça na posição irá setar a posição da matriz como verdadeira
		p.setValues(position.getRow() - 1, position.getColumn());
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() - 1);
		}
		
		//válida se a ultima posição que parou no laço é válida e se existe peça do oponente nela
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//VALIDANDO MOVIMENOS PARA A ESQUERDA DO TABULEIRO ---------------
		//Enquanto existir posição válida e não existir peça na posição irá setar a posição da matriz como verdadeira
		p.setValues(position.getRow(), position.getColumn() - 1);
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}
		
		//válida se a ultima posição que parou no laço é válida e se existe peça do oponente nela
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//VALIDANDO MOVIMENOS PARA A DIREITA DO TABULEIRO --------------------
		//Enquanto existir posição válida e não existir peça na posição irá setar a posição da matriz como verdadeira
		p.setValues(position.getRow(), position.getColumn() +1);
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}
		
		//válida se a ultima posição que parou no laço é válida e se existe peça do oponente nela
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		//VALIDANDO MOVIMENTOS PARA CIMA DO TABULEIRO ------------------
		//Enquanto existir posição válida e não existir peça na posição irá setar a posição da matriz como verdadeira
		p.setValues(position.getRow() + 1, position.getColumn());
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);
		}
		
		//válida se a ultima posição que parou no laço é válida e se existe peça do oponente nela
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		return mat;
	}
	
}
