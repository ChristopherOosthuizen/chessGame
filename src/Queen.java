import java.util.ArrayList;

public class Queen extends Piece{

	public Queen(int team) {
		super(team);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove(ArrayList<ArrayList<Square>> grid, Square one, Square two) {
		if(!two.containsPeice()||(two.containsPeice()&&two.getPeice().getTeam() != one.getPeice().getTeam())) {
			
		if(Math.abs(one.getXP() - two.getXP()) == Math.abs(two.getYP()-one.getYP())&&this.pathClear(grid, one, two)){
			return true;
		}
		if(((one.getXP() == two.getXP() && one.getYP() != two.getYP())||(one.getYP() == two.getYP() && one.getXP() != two.getXP())) && this.pathClear(grid, one, two) )
			return true;
		}
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "queen";
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 20;
	}

}
