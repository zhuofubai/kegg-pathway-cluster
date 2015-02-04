/**
 * @author zhuofu
 * 
 */

public class Cost_cmp {

	/**
	 * @param args
	 */
	public static int Cost(vertex i, vertex j) {
		int cij = 0;
		if(i==null && j==null)
		{
			System.out.println("Warning null vertex");
			return 0;
		}else if (i==null || j == null)
			{cij = 1;}
		else if (i.type == j.type)
			{cij = Math.abs(i.edge_num - j.edge_num);}
		else if (i.type != j.type)
			{cij = Math.abs(i.edge_num - j.edge_num) + 50;}
		return cij;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
