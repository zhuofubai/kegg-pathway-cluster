/**
 * @author zhuofu
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String xmlFileName = "/test5.xml";
		vertex[] test=GetNodes.GetNodesInfo(xmlFileName);
		System.out.println("check");
		for (int j = 0; j < test.length; j++) {
			System.out.println(test[j].edge_num);
		}
		String xmlFileName2="/test6.xml";
		vertex[] test2=GetNodes.GetNodesInfo(xmlFileName2);
		System.out.println("check");
		for (int j = 0; j < test2.length; j++) {
			System.out.println(test2[j].edge_num);
		}
		int[][] cost=costmatrix.costmatrix_cmp(test, test2);
		int s=test.length+test2.length;
		System.out.println("s is"+s);
		for(int n=0;n<s;n++){
			for(int m=0;m<s;m++){
				System.out.print(cost[n][m]+" ");
			}
		System.out.println(" ");
		}
		Munkre M = new Munkre();
		int[] Mr=M.Munkre(cost, s);
		System.out.println("Graph Edit Distance");
		for (int i = 0; i < Mr.length; i++)
			System.out.println(Mr[i] + " " + cost[i][Mr[i]]);
	}

}
