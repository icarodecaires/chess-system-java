package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//torre
public class Queen extends ChessPiece{

	public Queen(Board board, Color color) {
		super(board, color);
	}
	
	
	@Override
	public String toString() {
		return "Q";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		//A RAINHA � UMA MISTURA DE MOVIMENTOS DA TORRE E DO BISPO
		//MOVIMENTOS DA TORRE
		//cria uma matriz com a mesma dimens�o do tabuleiro com todas as posi��es marcadas como falsa
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0,0);		
		
		//VALIDANDO MOVIMENTOS PARA CIMA DO TABULEIRO --------------------------
		//Enquanto existir posi��o v�lida e n�o existir pe�a na posi��o ir� setar a posi��o da matriz como verdadeira
		p.setValues(position.getRow() - 1, position.getColumn());
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() - 1);
		}
		
		//v�lida se a ultima posi��o que parou no la�o � v�lida e se existe pe�a do oponente nela
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//VALIDANDO MOVIMENOS PARA A ESQUERDA DO TABULEIRO ---------------
		//Enquanto existir posi��o v�lida e n�o existir pe�a na posi��o ir� setar a posi��o da matriz como verdadeira
		p.setValues(position.getRow(), position.getColumn() - 1);
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}
		
		//v�lida se a ultima posi��o que parou no la�o � v�lida e se existe pe�a do oponente nela
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//VALIDANDO MOVIMENOS PARA A DIREITA DO TABULEIRO --------------------
		//Enquanto existir posi��o v�lida e n�o existir pe�a na posi��o ir� setar a posi��o da matriz como verdadeira
		p.setValues(position.getRow(), position.getColumn() +1);
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}
		
		//v�lida se a ultima posi��o que parou no la�o � v�lida e se existe pe�a do oponente nela
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		//VALIDANDO MOVIMENTOS PARA CIMA DO TABULEIRO ------------------
		//Enquanto existir posi��o v�lida e n�o existir pe�a na posi��o ir� setar a posi��o da matriz como verdadeira
		p.setValues(position.getRow() + 1, position.getColumn());
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);
		}
		
		//v�lida se a ultima posi��o que parou no la�o � v�lida e se existe pe�a do oponente nela
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//MOVIMENTOS DO BISPO
		//NW - VALIDANDO MOVIMENTOS DIAGONAL ESQUERDA ACIMA
				//Enquanto existir posi��o v�lida e n�o existir pe�a na posi��o ir� setar a posi��o da matriz como verdadeira
				p.setValues(position.getRow() - 1, position.getColumn() - 1);
				while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
					p.setValues(p.getRow() - 1, p.getColumn() - 1);
				}
				
				//v�lida se a ultima posi��o que parou no la�o � v�lida e se existe pe�a do oponente nela
				if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
				
				//VALIDANDO MOVIMENOS DIAGONAL DIREITA ACIMA
				//Enquanto existir posi��o v�lida e n�o existir pe�a na posi��o ir� setar a posi��o da matriz como verdadeira
				p.setValues(position.getRow() -1, position.getColumn() + 1);
				while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
					p.setValues(p.getRow() - 1, p.getColumn() + 1);
				}
				
				//v�lida se a ultima posi��o que parou no la�o � v�lida e se existe pe�a do oponente nela
				if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
				
				//VALIDANDO MOVIMENOS DIAGONAL DIREITA ABAIXO --------------------
				//Enquanto existir posi��o v�lida e n�o existir pe�a na posi��o ir� setar a posi��o da matriz como verdadeira
				p.setValues(position.getRow() +1 , position.getColumn() +1);
				while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
					p.setValues(p.getRow() + 1, p.getColumn() + 1);
				}
				
				//v�lida se a ultima posi��o que parou no la�o � v�lida e se existe pe�a do oponente nela
				if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
				
				
				//VALIDANDO MOVIMENOS DIAGONAL ESQUERDA ACIMA ------------------
				//Enquanto existir posi��o v�lida e n�o existir pe�a na posi��o ir� setar a posi��o da matriz como verdadeira
				p.setValues(position.getRow() + 1, position.getColumn() - 1);
				while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
					p.setValues(p.getRow() + 1, p.getColumn() - 1);
				}
				
				//v�lida se a ultima posi��o que parou no la�o � v�lida e se existe pe�a do oponente nela
				if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
				
		return mat;
	}
	
}
