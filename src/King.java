import java.util.ArrayList;

public class King extends Piece{

	public King(int team) {
		super(team);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove(ArrayList<ArrayList<Square>> grid, Square one, Square two) {
		if(!two.containsPeice()||(two.containsPeice()&&two.getPeice().getTeam() != one.getPeice().getTeam())) {
			if(Math.abs(one.getXP()-two.getXP()) ==1 && one.getYP()==two.getYP())
				return true;
			if(Math.abs(one.getYP()-two.getYP()) ==1 && one.getXP()==two.getXP())
				return true;
			if(Math.abs(one.getXP()-two.getXP()) ==1 && Math.abs(one.getYP()-two.getYP()) ==1)
				return true;
		}
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "King";
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 100;
	}

	

}
