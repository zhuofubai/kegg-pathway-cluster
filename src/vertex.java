/**
 * @author zhuofu
 *
 */
 
public class vertex {
	int edge_num;
	int type;
	String[] EC_num;

	public static void main(String[] args) {
		int i = 100;
		vertex[] vertices = new vertex[i];
		vertices[1] = new vertex();
		vertices[1].edge_num = 1;
		System.out.println(vertices[1].edge_num);
	}
}