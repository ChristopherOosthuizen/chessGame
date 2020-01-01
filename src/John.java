
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


public class John {
	private Board board;
	private Method type;
	public John(Board board,String type) throws NoSuchMethodException, SecurityException {
		this.board = board;
		this.type = John.class.getMethod(type, int.class);
	}
	public ArrayList<Square> peciesList(){
		ArrayList<Square> list = new ArrayList<Square>();
		board.stream().forEach(s-> s.stream().filter(r-> r.containsPeice() && r.getPeice().getTeam() ==Piece.BLACK_TEAM).forEach(n-> list.add(n)));
		return list;
	}
	
	public int random(int turn) {
		ArrayList<Square> list = peciesList();
		int pec = (int)(Math.random() *list.size());
		
		Square spot = list.get(pec);
		ArrayList<Square> dsts = spot.getPeice().movableSpaces(board, spot);
		while(dsts.size() ==0) {
			pec = (int)(Math.random() *list.size());
			
			spot = list.get(pec);
			dsts = spot.getPeice().movableSpaces(board, spot);
		}
		Square dst = dsts.get((int)(Math.random()*dsts.size()));
		spot.movePeice(board,dst);
		return turn++;
	}
	public int player(int turn) {
		return turn;
	}
	public int run(int turn) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return (int) type.invoke(this, turn);
	}

}
