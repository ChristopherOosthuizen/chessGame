import java.awt.Point;
import java.util.*;

public class Pawn extends Piece{
	
	public Pawn(int team,Board board) {
		super(team,board);
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Pawn";
	}
	
	

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 100;
	}
	

	@Override
	public boolean canMove(Square one ,Square two) {
		
		if(two.getYP() == one.getYP()&& (!two.containsPeice())) {
				
				if(getTurns() ==1 &&two.getXP()-one.getXP()== getTeam()*2&& this.pathClear( one, two) ) {
					return true;
				}
				if(two.getXP()-one.getXP()== getTeam() )
					return true;
		}
		if(two.getXP()-one.getXP()== getTeam()&& (two.getYP()-1 == one.getYP() || two.getYP()+1 == one.getYP())&& one.isSameTeam(two) )
			return true;
		return false;
	}

}
