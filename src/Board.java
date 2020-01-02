import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import java.util.*;
public class Board extends ArrayList<ArrayList<Square>>{
		
	
	
	public static final String OPPONENT_TYPE_RANDOM="random";
	public static final String OPPONENT_TYPE_PLAYER= "player";
	public static final String OPPONENT_TYPE_DECITIONS= "decide";
	
	
	private JFrame frame;
	private Square selectedSqaure;
	private John john;
	private int turn;
	private boolean SquareisSelected;
	private class MoveListener implements ActionListener{
		private Board board;
		public MoveListener(Board board){
			this.board = board;
		}

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!SquareisSelected&& ((Square)e.getSource()).containsPeice()&&((turn%2 == 0 &&((Square)e.getSource()).getPeice().getTeam() ==Piece.BLACK_TEAM) ||(turn%2 == 1 &&((Square)e.getSource()).getPeice().getTeam() ==Piece.WHITE_TEAM))){
					SquareisSelected =true;
					

					selectedSqaure = ((Square)e.getSource());
					selectedSqaure.setText(selectedSqaure.getText()+"sec");
				}
				else if((SquareisSelected  &&selectedSqaure.getPeice().canMove(selectedSqaure,((Square)e.getSource())))) {
					selectedSqaure.movePeice(((Square)e.getSource()));
					SquareisSelected =false;
					turn++;
					
					try {
						turn =john.run(turn);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
						e1.printStackTrace();
					}	
					
					
				}
				else if(SquareisSelected&&selectedSqaure == e.getSource()) {
					SquareisSelected =false;
					selectedSqaure.setText(selectedSqaure.getText().replace("sec", ""));
				}else if(SquareisSelected&&((Square)e.getSource()).containsPeice() &&selectedSqaure.getPeice().getTeam() == ((Square)e.getSource()).getPeice().getTeam()) {
					
					selectedSqaure.setText(selectedSqaure.getText().replace("sec", ""));
				
					
					selectedSqaure = ((Square)e.getSource());
					selectedSqaure.setText(selectedSqaure.getText()+"sec");
				}
				
			}
			
		}
	
	
	public Board(String opponiteType) throws NoSuchMethodException, SecurityException {
		//initialize varibles
		turn =1;
		SquareisSelected = false;
		frame = new JFrame();
		frame.setLayout(new GridLayout(0,8));
		frame.setDefaultCloseOperation(3);
		john = new John(this,opponiteType);
		
		
	}
	
	
	public void run() {
		MoveListener move = new MoveListener(this);
		
		for(int i=0;i<8;i++) {
			this.add(new ArrayList<Square>());
			for(int ii=0; ii< 8;ii++) {
				Square sqr = new Square(this,i,ii);
				sqr.addActionListener(move);
				this.get(i).add(sqr);
				frame.add(sqr);
			}
		}
		BoardSetUp();
		frame.setSize(400, 400);
		frame.setVisible(true);
	}
	
	
	
	private void BoardSetUp() {
		for(int i=0; i<8;i++) {
			this.get(1).get(i).addPiece(new Pawn(Piece.BLACK_TEAM,this));
			this.get(6).get(i).addPiece(new Pawn(Piece.WHITE_TEAM,this));
		}
		this.get(0).get(3).addPiece(new Queen(Piece.BLACK_TEAM,this));
		this.get(7).get(3).addPiece(new Queen(Piece.WHITE_TEAM,this));
		this.get(0).get(0).addPiece(new Rook(Piece.BLACK_TEAM,this));
		this.get(0).get(7).addPiece(new Rook(Piece.BLACK_TEAM,this));
		this.get(7).get(7).addPiece(new Rook(Piece.WHITE_TEAM,this));
		this.get(7).get(0).addPiece(new Rook(Piece.WHITE_TEAM,this));
		this.get(0).get(2).addPiece(new Bishop(Piece.BLACK_TEAM,this));
		this.get(0).get(5).addPiece(new Bishop(Piece.BLACK_TEAM,this));
		this.get(7).get(2).addPiece(new Bishop(Piece.WHITE_TEAM,this));
		this.get(7).get(5).addPiece(new Bishop(Piece.WHITE_TEAM,this));
		this.get(0).get(1).addPiece(new Knight(Piece.BLACK_TEAM,this));
		this.get(0).get(6).addPiece(new Knight(Piece.BLACK_TEAM,this));
		this.get(7).get(1).addPiece(new Knight(Piece.WHITE_TEAM,this));
		this.get(7).get(6).addPiece(new Knight(Piece.WHITE_TEAM,this));
		this.get(0).get(4).addPiece(new King(Piece.BLACK_TEAM,this));
		this.get(7).get(4).addPiece(new King(Piece.WHITE_TEAM,this));
	}

}
