
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.stream.Stream;


public class John {
	private final int PEICEIS=0;
	private final int SPACES =1;
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
		return ++turn;
	}
	public int player(int turn) {
		return turn;
	}
	public int decide(int turn) {
		ArrayList<Square> squares = peciesList();
		int min_value = Integer.MAX_VALUE;
		Square min_Square = squares.get(0);
		for(Square sqr:squares) {
			if(lowestMove(sqr) !=null&&lowestMove(sqr).getValue() < min_value) {
				
				min_value = lowestMove(sqr).getValue();
				min_Square = sqr;
			}
			
			
		}
		
		min_Square.movePeice(board, lowestMove(min_Square));
		return ++turn;
		
	}
	public int run(int turn) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return (int) type.invoke(this, turn);
	}
	
	private Square lowestMove(Square square) {
		ArrayList<Square> squares = square.getPeice().movableSpaces(board, square);
		if(squares.size() >0) {
		int min_value = Integer.MAX_VALUE;
		Square min_Square = squares.get(0);
		for(Square sqr:squares) {
			if(sqr.getValue() < min_value) {
				min_value = sqr.getValue();
				min_Square = sqr;
			}
			
		}
		return min_Square;
		}
		return null;
	}
	

}
