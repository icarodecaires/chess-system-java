package boardgame;


public abstract class Piece {
	
	protected  Position position;
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	protected Board getBoard() {
		return board;
	}

	//M�todo abstrato, as subclasses dever�o implementar	
	public abstract boolean[][] possibleMoves();
	
	//Retorna possiveis movimentos da pe�a
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	//Retorna se existe pelo menos um movimento util para a pe�a
	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();
		
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}

}
