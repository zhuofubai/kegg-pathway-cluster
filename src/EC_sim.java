/**
 * 
 */

/**
 * @author zhuofu
 *
 */
public class EC_sim {

	/**
	 * @param args
	 */

	/**
	 * @param args
	 */
	public static double SimScore(String[] i,String[] j){
		double sim=0;
	
		 if (i.length != 4 || j.length != 4) { 
             sim = 0.0; 
         } else {
         	// Dummy variable to catch unknown EC elements
             Double zero = new Double(0);
             try {
                 // Compare EC numbers
                 if (Double.valueOf(i[0]).compareTo(Double.valueOf(j[0])) == 0 && (Double.valueOf(i[0]).compareTo(zero) != 0 )&& (Double.valueOf(j[0]).compareTo(zero) != 0 )) {
                     if (Double.valueOf(i[1]).compareTo(Double.valueOf(j[1])) == 0 && (Double.valueOf(i[1]).compareTo(zero) != 0 )&& (Double.valueOf(j[1]).compareTo(zero) != 0 )) {
                         if (Double.valueOf(i[2]).compareTo(Double.valueOf(j[2])) == 0 && (Double.valueOf(i[2]).compareTo(zero) != 0 )&& (Double.valueOf(j[2]).compareTo(zero) != 0 )) {
                             if (Double.valueOf(i[3]).compareTo(Double.valueOf(j[3])) == 0 && (Double.valueOf(i[3]).compareTo(zero) != 0 )&& (Double.valueOf(j[3]).compareTo(zero) != 0 )) {
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

	}

}
