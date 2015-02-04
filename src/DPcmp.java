import java.util.List;
import org.dom4j.Node;


/**
 * @author zhuofu
 *
 */
public class DPcmp {

	/**
	 * @param args
	 */
	double Pathway_align(List<Node> Rn1,List<Node> Rn2){
	double d=0,	S=0;
	int	m=Rn1.size();
	int n=Rn2.size(); 
	int imax=0,jmax=0;
	double max=0;
	double [][]M= new double [m][n];
	for (int i=0; i<m; i++)
		for(int j=0;j<n;j++){
			M[i][j]=Math.max(Math.max(0, M[i-1][j]+d),Math.max(M[i][j-1]+d, M[i-1][j-1])+S);
			if(max<M[i][j]){
				max=M[i][j];
				imax=i;
				jmax=j;
			}
		}
	return max;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
