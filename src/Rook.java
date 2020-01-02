import java.util.ArrayList;

public class Rook extends Piece{

	public Rook(int team,Board board) {
		super(team,board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove( Square one, Square two) {
		if(!two.containsPeice()||one.isSameTeam(two)) {
		if(((one.getXP() == two.getXP() && one.getYP() != two.getYP())||(one.getYP() == two.getYP() && one.getXP() != two.getXP())) && this.pathClear( one, two) )
			return true;
		}
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "rook";
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 30;
	}

}
