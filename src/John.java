import java.awt.Point;
import java.util.*;
public class John {
	private ArrayList<ArrayList<Square>> grid;
	public John(ArrayList<ArrayList<Square>> grid) {
		this.grid = grid;
	}
	public ArrayList<Square> peciesList(){
		ArrayList<Square> list = new ArrayList();
		grid.stream().forEach(s-> s.stream().filter(r-> r.containsPeice() && r.getPeice().getTeam() ==1).forEach(n-> list.add(n)));
		return list;
	}
	public void strat() {
		
	}
	public void random() {
		ArrayList<Square> list = peciesList();
		int pec = (int)(Math.random() *list.size());
		
		Square spot = list.get(pec);
		ArrayList<Square> dsts = spot.getPeice().movableSpaces(grid, spot);
		while(dsts.size() ==0) {
			pec = (int)(Math.random() *list.size());
			
			spot = list.get(pec);
			dsts = spot.getPeice().movableSpaces(grid, spot);
		}
		Square dst = dsts.get((int)(Math.random()*dsts.size()));
		spot.movePeice(grid,dst);
		
		
	}

}
