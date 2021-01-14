package boardgame;

public class Board {
	
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	
	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("Erro ao Criar o Tabuleiro é necessário pelo menos uma linha e uma coluna");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}


	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	public Piece piece(int row, int column) {
		if (!positionExists(row, column)) {
			throw new BoardException("Posição não Existe");
		}
		return pieces[row][column];
	}
	
	//verifica se existe uma peça em determinada posição
	public Piece piece(Position position) {
		//verifica se a posição é válida
		if (!positionExists(position)) {
			throw new BoardException("Posição não Existe");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	//colocar determinada peça em determinada posição
	public void placePiece(Piece piece, Position position) {
		//verifica se ja existe outra peça nesta posição
		if (thereIsAPiece(position)) {
			throw new BoardException("Existe outra peça na posição selecionada "+position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	//verifica se existe uma posição no tabuleiro em determinada linha e culuna
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	//verifica se existe uma posição no tabuleiro através da posição
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(),position.getColumn());
	}
	
	//verifica se existe uma peça em determinada posição
	public boolean thereIsAPiece(Position position) {
		//verifica se a posição é válida
		if (!positionExists(position)) {
			throw new BoardException("Posição não Existe");
		}
		return piece(position) != null;
	}

}
