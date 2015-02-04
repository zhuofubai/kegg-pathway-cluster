import java.util.List;

import org.dom4j.Node;

/**
 * 
 */

/**
 * @author zhuofu
 * 
 */
public class Rnsim {

	/**
	 * @param args
	 */
	double Rnsimi(Node i, Node j) {
		List<Node> ileft = i
				.selectNodes("bp:LEFT/bp:physicalEntityParticipant/bp:PHYSICAL-ENTITY");
		List<Node> iright = i
				.selectNodes("bp:RIGHT/bp:physicalEntityParticipant/bp:PHYSICAL-ENTITY");

		List<Node> jleft = j
				.selectNodes("bp:LEFT/bp:physicalEntityParticipant/bp:PHYSICAL-ENTITY");
		List<Node> jright = j
				.selectNodes("bp:RIGHT/bp:physicalEntityParticipant/bp:PHYSICAL-ENTITY");

		double sim = 0;
		double min = 2;

		if (i.selectSingleNode("bp:EC-NUMBER") != null
				&& j.selectSingleNode("bp:EC-NUMBER") != null) {
			List<Node> EC1_nums = i.selectNodes("bp:EC-NUMBER");
			List<Node> EC2_nums = j.selectNodes("bp:EC-NUMBER");
			String[] EC1 = new String[EC1_nums.size()];
			String[] EC2 = new String[EC1_nums.size()];
			int m = 0, n = 0;
			for (Node EC1_num : EC1_nums) {
				String ec1 = EC1_num.getText();
				EC1[m] = ec1.replaceAll("-", "0");
				m++;
			}

			for (Node EC2_num : EC2_nums) {
				String ec2 = EC2_num.getText();
				EC2[n] = ec2.replaceAll("-", "0");
				n++;
			}

			String[] EC1_hie, EC2_hie;
			for (m = 0; m < EC1.length; m++)
				for (n = 0; n < EC2.length; n++) {
					EC1_hie = EC1[m].split("\\.");
					EC2_hie = EC2[n].split("\\.");
					sim = EC_sim.SimScore(EC1_hie, EC1_hie);
					if (sim < min) {
						min = sim;
					}
				}
		}
		if (min >= 0.5) {
			sim = min;
		} else {

			int size_dif = Math.abs(ileft.size() - iright.size());
		}
		return sim;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
