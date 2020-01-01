import java.awt.GridLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import java.util.*;
public class Board extends ArrayList<ArrayList<Square>>{
		
	public final int WHITE_TEAM = -1;
	public final int BLACK_TEAM = 1;
	public static final String OPPONENT_TYPE_RANDOM="random";
	public static final String OPPONENT_TYPE_PLAYER= "player";
	
	
	
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
				if(!SquareisSelected&& ((Square)e.getSource()).containsPeice()&&((turn%2 == 0 &&((Square)e.getSource()).getPeice().getTeam() ==BLACK_TEAM) ||(turn%2 == 1 &&((Square)e.getSource()).getPeice().getTeam() ==WHITE_TEAM))){
					SquareisSelected =true;
					

					selectedSqaure = ((Square)e.getSource());
					selectedSqaure.setText(selectedSqaure.getText()+"sec");
				}
				else if((SquareisSelected  &&selectedSqaure.getPeice().canMove(board,selectedSqaure,((Square)e.getSource())))) {
					selectedSqaure.movePeice(board,((Square)e.getSource()));
					SquareisSelected =false;
					
					turn++;
					try {
						john.run(turn);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
						// TODO Auto-generated catch block
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
		john = new John(this,opponiteType);
		
	}
	
	
	public void run() {
		MoveListener move = new MoveListener(this);
		
		for(int i=0;i<8;i++) {
			this.add(new ArrayList<Square>());
			for(int ii=0; ii< 8;ii++) {
				Square sqr = new Square(i,ii);
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
			this.get(1).get(i).addPiece(new Pawn(BLACK_TEAM));
			this.get(6).get(i).addPiece(new Pawn(WHITE_TEAM));
		}
		this.get(0).get(3).addPiece(new Queen(BLACK_TEAM));
		this.get(7).get(3).addPiece(new Queen(WHITE_TEAM));
		this.get(0).get(0).addPiece(new Rook(BLACK_TEAM));
		this.get(0).get(7).addPiece(new Rook(BLACK_TEAM));
		this.get(7).get(7).addPiece(new Rook(WHITE_TEAM));
		this.get(7).get(0).addPiece(new Rook(WHITE_TEAM));
		this.get(0).get(2).addPiece(new Bishop(BLACK_TEAM));
		this.get(0).get(5).addPiece(new Bishop(BLACK_TEAM));
		this.get(7).get(2).addPiece(new Bishop(WHITE_TEAM));
		this.get(7).get(5).addPiece(new Bishop(WHITE_TEAM));
		this.get(0).get(1).addPiece(new Knight(BLACK_TEAM));
		this.get(0).get(6).addPiece(new Knight(BLACK_TEAM));
		this.get(7).get(1).addPiece(new Knight(WHITE_TEAM));
		this.get(7).get(6).addPiece(new Knight(WHITE_TEAM));
		this.get(0).get(4).addPiece(new King(BLACK_TEAM));
		this.get(7).get(4).addPiece(new King(WHITE_TEAM));
	}

}
