import java.awt.Point;

import javax.swing.JButton;
import java.util.*;
public class Square extends JButton{
	private boolean peicePresent;
	private Piece peice;
	private Point point;
	public Square(int x,int y) {
		super();
		point = new Point(x,y);
		peicePresent =false;
	}
	public Square(int x,int y,Piece peice) {
		point = new Point(x,y);
		peicePresent = true;
		this.peice =  peice;
		this.setText(peice.getName()+(peice.getTeam()==Piece.WHITE_TEAM?" White":" black"));
	}
	public void addPiece(Piece peice) {
		peicePresent = true;
		this.peice = peice;
		this.setText(peice.getName()+(peice.getTeam()==Piece.WHITE_TEAM?" White":" black"));
	}
	public Piece getPeice() {
		return peice;
	}
	public void removePeice() {
		peicePresent =false;
		peice = null;
		this.setText("");
	}
	
	public boolean containsPeice() {
		return peicePresent;
	}
	public Point getPoint() {
		return point;
	}
	public int getXP() {
		return point.x;
	}
	public int getYP() {
		return point.y;
	}
	public void movePeice(ArrayList<ArrayList<Square>> grid,Square dst) {
		if(dst.containsPeice() && dst.getPeice().getClass() == King.class) {
			grid.forEach(s->s.forEach(r->r.removeActionListener(r.getActionListeners()[0])));
			System.out.println(this.getPeice().getTeam() ==Piece.WHITE_TEAM?"white wins":"Black wins");
			
		}
		dst.addPiece(this.getPeice());
		
		this.getPeice().raiseTurn();
		
		this.removePeice();
	}
	
	
}
