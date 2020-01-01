
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
		
		return turn++;
		
	}
	public int run(int turn) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return (int) type.invoke(this, turn);
	}
	private int[][] generateValueTable(int o){
		int[][] result = new int[8][8];
		for(int i=0; i< 8; i++) {
			for(int ii=0; ii<8;ii++) {
				if(board.get(i).get(ii).containsPeice())
					result[i][ii] =board.get(i).get(ii).getPeice().getValue()*board.get(i).get(ii).getPeice().getTeam();
				else
					result[i][ii] =o;
			}
		}
		return result;
	}
	private int[][] preformLamda(int[][] table,IntUnaryOperator bi){
		for(int i=0; i< 8; i++) {
			table[i] = Arrays.stream(table[i]).map(bi).toArray();
		}
		return table;
	}

}
