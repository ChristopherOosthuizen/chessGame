import java.awt.Point;
import java.util.*;
public abstract class Piece {
	public static final int WHITE_TEAM = -1;
	public static final int BLACK_TEAM = 1;
	private int team;
	private int turns;
	private Board board;
	public Piece(int team,Board board) {
		this.team =team;
		this.board = board;
	}
	
	public abstract boolean canMove(Square one,Square two);
	public abstract String getName();
	public  int getTeam() {
		return team;
	}
	
	public abstract int getValue();

	public int getTurns() {
		return turns+1;
	}
	public void raiseTurn() {
		turns++;
	}
	public ArrayList<Square> movableSpaces(Square sqr){
		ArrayList<Square> list = new ArrayList<Square>();
		for(int i=0; i<8;i++) {
			for(int ii=0; ii<8;ii++) {
				if(canMove(sqr, board.get(i).get(ii))) {
					list.add(board.get(i).get(ii));
				}
			}
		}
		return list;
	}
	public boolean pathClear(Square one,Square two) {
		int xdif =two.getXP()-one.getXP();
		int ydif = two.getYP()-one.getYP();
		int x = one.getXP();
		int y =one.getYP();
		for(int i=0; i< Math.max(Math.abs(ydif),Math.abs(xdif))-1;i++) {
			x +=Integer.signum(xdif);
			y+=Integer.signum(ydif);
			Square spot = board.get(x).get(y);
			if(spot.containsPeice()) {
				return false;
			}
			
		}
		return true;
	}
	
}
