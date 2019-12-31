import java.awt.Point;
import java.util.*;
public abstract class Piece {
	private int team;
	private int turns;
	public Piece(int team) {
		this.team =team;
		
	}
	
	public abstract boolean canMove(ArrayList<ArrayList<Square>> grid,Square one,Square two);
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
	public ArrayList<Square> movableSpaces(ArrayList<ArrayList<Square>> grid,Square sqr){
		ArrayList<Square> list = new ArrayList();
		for(int i=0; i<8;i++) {
			for(int ii=0; ii<8;ii++) {
				if(canMove(grid,sqr, grid.get(i).get(ii))) {
					list.add(grid.get(i).get(ii));
				}
			}
		}
		return list;
	}
	public boolean pathClear(ArrayList<ArrayList<Square>> grid,Square one,Square two) {
		int xdif =two.getXP()-one.getXP();
		int ydif = two.getYP()-one.getYP();
		int x = one.getXP();
		int y =one.getYP();
		for(int i=0; i< Math.max(Math.abs(ydif),Math.abs(xdif))-1;i++) {
			x +=Integer.signum(xdif);
			y+=Integer.signum(ydif);
			Square spot = grid.get(x).get(y);
			if(spot.containsPeice()) {
				return false;
			}
			
		}
		return true;
	}
	
}
