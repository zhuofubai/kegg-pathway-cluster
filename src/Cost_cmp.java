/**
 * @author zhuofu
 * 
 */

public class Cost_cmp {

	/**
	 * @param args
	 */
	public static double Cost(vertex i, vertex j) {
		double cij = 0;
		if(i==null && j==null)
		{
			System.out.println("Warning null vertex");
			return 0;
		}else if (i==null || j == null)
			{cij = 1;}
		else if(i.type==j.type){ 
		   if(i.type == 0)
			{cij = Math.abs(i.edge_num - j.edge_num);}
		   else if (i.type ==3 && j.type==3)
		    {cij = Math.abs(i.edge_num - j.edge_num);}
		   else if (i.type == 1 && j.type==1)
		    {
			  double s=ez_match.ez_score(i, j);
			  if (s==1.0) cij=0;
			  else if(s==0.75) cij=1;
			  else if(s==0.5)  cij=2;
			  else if(s==0)    cij=4;
		    }
		  }
		else if (i.type != j.type)
			{cij = Math.abs(i.edge_num - j.edge_num) + 50;}
		return cij;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
