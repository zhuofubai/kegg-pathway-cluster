/**
 * @author zhuofu
 *
 */
 
public class ez_match {

	/**
	 * @param args
	 */
	public static double ez_score(vertex i,vertex j){
		double sim=0;
	
		 if (i.EC_num.length != 4 || j.EC_num.length != 4) { 
             sim = 0.0; 
         } else {
         	// Dummy variable to catch unknown EC elements
             Double zero = new Double(0);
             try {
                 // Compare EC numbers
                 if (Double.valueOf(i.EC_num[0]).compareTo(Double.valueOf(j.EC_num[0])) == 0 && (Double.valueOf(i.EC_num[0]).compareTo(zero) != 0 )&& (Double.valueOf(j.EC_num[0]).compareTo(zero) != 0 )) {
                     if (Double.valueOf(i.EC_num[1]).compareTo(Double.valueOf(j.EC_num[1])) == 0 && (Double.valueOf(i.EC_num[1]).compareTo(zero) != 0 )&& (Double.valueOf(j.EC_num[1]).compareTo(zero) != 0 )) {
                         if (Double.valueOf(i.EC_num[2]).compareTo(Double.valueOf(j.EC_num[2])) == 0 && (Double.valueOf(i.EC_num[2]).compareTo(zero) != 0 )&& (Double.valueOf(j.EC_num[2]).compareTo(zero) != 0 )) {
                             if (Double.valueOf(i.EC_num[3]).compareTo(Double.valueOf(j.EC_num[3])) == 0 && (Double.valueOf(i.EC_num[3]).compareTo(zero) != 0 )&& (Double.valueOf(j.EC_num[3]).compareTo(zero) != 0 )) {
                                 sim = 1.0;
                             } else {
                                 sim = 0.75;
                             }
                         } else {
                             sim = 0.5;
                         }
                     } else {
                         sim = 0.25;
                     }
                 } else {
                     sim = 0.0;
                 }
             } catch (NumberFormatException errnum) {
                 System.err.println("NumberFormat error: " + errnum.getMessage());  
             }
         }
		 return sim;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			vertex i= new vertex();
			vertex j = new vertex();
			String s1="2.3.2.1";
			String s2="2.3.1.2";
			i.EC_num= s1.split("\\.");
            j.EC_num = s2.split("\\.");
            System.out.println(ez_match.ez_score(i, j));
            
	}

}
