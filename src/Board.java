import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import java.util.*;
public class Board {
	JFrame frame;
	Square selectedSqaure;
	John john;
	private int turn;
	boolean isSquareSelected;
	private ArrayList<ArrayList<Square>> grid;
	public Board() {
		grid = new ArrayList<ArrayList<Square>>();
		turn =1;
		isSquareSelected = false;
		frame = new JFrame();
		frame.setLayout(new GridLayout(0,8));
		ActionListener move = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isSquareSelected&& ((Square)e.getSource()).containsPeice()&&((turn%2 == 0 &&((Square)e.getSource()).getPeice().getTeam() ==1) ||(turn%2 == 1 &&((Square)e.getSource()).getPeice().getTeam() ==-1))){
					isSquareSelected =true;
					

					selectedSqaure = ((Square)e.getSource());
					selectedSqaure.setText(selectedSqaure.getText()+"sec");
				}
				else if((isSquareSelected  &&selectedSqaure.getPeice().canMove(grid,selectedSqaure,((Square)e.getSource())))) {
					selectedSqaure.movePeice(grid,((Square)e.getSource()));
					isSquareSelected =false;
					
					
					john.random();			
				}
				else if(isSquareSelected&&selectedSqaure == e.getSource()) {
					isSquareSelected =false;
					selectedSqaure.setText(selectedSqaure.getText().replace("sec", ""));
				}else if(isSquareSelected&&((Square)e.getSource()).containsPeice() &&selectedSqaure.getPeice().getTeam() == ((Square)e.getSource()).getPeice().getTeam()) {
					
					selectedSqaure.setText(selectedSqaure.getText().replace("sec", ""));
				
					
					selectedSqaure = ((Square)e.getSource());
					selectedSqaure.setText(selectedSqaure.getText()+"sec");
				}
				
			}
			
		};
		
		for(int i=0;i<8;i++) {
			grid.add(new ArrayList());
			for(int ii=0; ii< 8;ii++) {
			Square sqr = new Square(i,ii);
			sqr.addActionListener(move);
			grid.get(i).add(sqr);
			frame.add(sqr);
		}
	}
		for(int i=0; i<8;i++) {
			grid.get(1).get(i).addPiece(new Pawn(1));
			grid.get(6).get(i).addPiece(new Pawn(-1));
		}
		grid.get(0).get(3).addPiece(new Queen(1));
		grid.get(7).get(3).addPiece(new Queen(-1));
		grid.get(0).get(0).addPiece(new Rook(1));
		grid.get(0).get(7).addPiece(new Rook(1));
		grid.get(7).get(7).addPiece(new Rook(-1));
		grid.get(7).get(0).addPiece(new Rook(-1));
		grid.get(0).get(2).addPiece(new Bishop(1));
		grid.get(0).get(5).addPiece(new Bishop(1));
		grid.get(7).get(2).addPiece(new Bishop(-1));
		grid.get(7).get(5).addPiece(new Bishop(-1));
		grid.get(0).get(1).addPiece(new Knight(1));
		grid.get(0).get(6).addPiece(new Knight(1));
		grid.get(7).get(1).addPiece(new Knight(-1));
		grid.get(7).get(6).addPiece(new Knight(-1));
		grid.get(0).get(4).addPiece(new King(1));
		grid.get(7).get(4).addPiece(new King(-1));
		john = new John(grid);
		frame.setSize(400, 400);
		frame.setVisible(true);
	}

}
