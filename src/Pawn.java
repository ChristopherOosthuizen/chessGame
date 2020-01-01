import java.awt.Point;
import java.util.*;

public class Pawn extends Piece{
	
	public Pawn(int team) {
		super(team);
		
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
	public boolean canMove(ArrayList<ArrayList<Square>> grid,Square one ,Square two) {
		
		if(two.getYP() == one.getYP()&& (!two.containsPeice())) {
				
				if(getTurns() ==1 &&two.getXP()-one.getXP()== getTeam()*2&& this.pathClear(grid, one, two) ) {
					return true;
				}
				if(two.getXP()-one.getXP()== getTeam() )
					return true;
		}
		if(two.getXP()-one.getXP()== getTeam()&& (two.getYP()-1 == one.getYP() || two.getYP()+1 == one.getYP())&& two.containsPeice())
			return true;
		return false;
	}

}
