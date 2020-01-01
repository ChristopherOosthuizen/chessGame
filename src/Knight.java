import java.util.ArrayList;

public class Knight extends Piece{

	public Knight(int team) {
		super(team);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public boolean canMove(ArrayList<ArrayList<Square>> grid, Square one, Square two) {
		if(!two.containsPeice()||(two.containsPeice()&&two.getPeice().getTeam() != one.getPeice().getTeam())) {
		if(Math.abs(one.getXP()-two.getXP()) == 2&& Math.abs(one.getYP()-two.getYP()) ==1)
			return true;
		if(Math.abs(one.getYP()-two.getYP()) == 2&& Math.abs(one.getXP()-two.getXP()) ==1)
			return true;
		}
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "knight";
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 40;
	}

}
