
/**
 * @author zhuofu
 * 
 */
public class costmatrix {
	public static double [][] costmatrix_cmp(vertex[] m, vertex[] n) {
		int length = m.length + n.length;
		if (m.length == 0 || n.length == 0) {
			System.out.println("empty vertex");
			return null;
		}
		double [][] cost_mat = new double[length][length];
		for (int i = 0; i < n.length; i++) {
			for (int j = 0; j < m.length; j++) {			
				cost_mat[i][j] = Cost_cmp.Cost(n[i], m[j]);
			}
		}
		for (int i = 0; i < n.length; i++) {
			for (int j = m.length; j < length; j++) {
				if(i==j-m.length)
				{cost_mat[i][j] = Cost_cmp.Cost(n[i], null);}
				else {cost_mat[i][j]=100;}
			}
		}
		for (int i = n.length; i < length; i++) {
			for (int j = 0; j < m.length; j++) {
				if(j==i-n.length)
				{cost_mat[i][j] = Cost_cmp.Cost(null, m[j]);}
				else {cost_mat[i][j]=100;}
			}
		}
		for (int i = n.length; i < length; i++) {
			for (int j = m.length; j < length; j++) {
				cost_mat[i][j] = 0;
			}
		}
		return cost_mat;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
