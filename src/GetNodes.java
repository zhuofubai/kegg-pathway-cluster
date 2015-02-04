import org.dom4j.Document;
import org.dom4j.Element;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * @author zhuofu
 *
 */
public class GetNodes {
	/**
	 * This method is used to get node information from a xml document and
	 * return it
	 * 
	 * @param xmlFileName
	 *            is the xml file name to be loaded
	 * @return vertices
	 */
	public static vertex[] GetNodesInfo(String xmlFileName) {
		Document document = Foo.getDocument(xmlFileName);
		/* INITIAL NODE LIST */
		List<Node> reactions = document
				.selectNodes("//rdf:RDF/bp:biochemicalReaction");
		List<Node> simpMols = document
				.selectNodes("//rdf:RDF/bp:smallMolecule");
		List<Node> proteins = document.selectNodes("//rdf:RDF/bp:protein");
		int prosize = proteins.size();
		int s = reactions.size() + simpMols.size();
		vertex[] vertices = new vertex[s];
		int i = 0;
		for (Node simpMol : simpMols) {
			vertices[i] = new vertex();
			vertices[i].type = 0;
			i++;
		}

		for (Node reaction : reactions) {
			vertices[i] = new vertex();
			vertices[i].type = 1;
			i++;
		}
		/* GET BEGIN NODE */
		Node begin_node = simpMols.get(0);
		String type1 = begin_node.valueOf("@rdf:ID");
		int p = type1.length();
		String begin_id = type1.substring(6, p);
		int begin = Integer.parseInt(begin_id);
		/* UPDATING NODE LIST */
		int offset1, offset2;
		for (Node process : reactions) {

			List<Node> leftnodes = process
					.selectNodes("bp:LEFT/bp:physicalEntityParticipant/bp:PHYSICAL-ENTITY");
			for (Node left : leftnodes) {

				String process_id = process.valueOf("@rdf:ID");
				//System.out.println(process_id);
				int m = process_id.length();
				String process_ps = process_id.substring(6, m);
				//System.out.println(process_ps);
				int process_num = Integer.parseInt(process_ps);
				offset2 = process_num - begin - prosize;
				vertices[offset2].edge_num++;

				String id = left.valueOf("@rdf:resource");
				//System.out.println(id);
				m = id.length();
				String offset_val = id.substring(7, m);
				//System.out.println(offset_val);
				int node_id = Integer.parseInt(offset_val);
				offset1 = node_id - begin;
				vertices[offset1].edge_num++;
			}
			List<Node> rightnodes = process
					.selectNodes("bp:RIGHT/bp:physicalEntityParticipant/bp:PHYSICAL-ENTITY");
			for (Node right : rightnodes) {
				String process_id = process.valueOf("@rdf:ID");
				//System.out.println(process_id);
				int m = process_id.length();
				String process_ps = process_id.substring(6, m);
				//System.out.println(process_ps);
				int process_num = Integer.parseInt(process_ps);
				offset2 = process_num - begin - prosize;
				vertices[offset2].edge_num++;

				String id = right.valueOf("@rdf:resource");
				//System.out.println(id);
				m = id.length();
				String offset_val = id.substring(7, m);
				//System.out.println(offset_val);
				int node_id = Integer.parseInt(offset_val);
				offset1 = node_id - begin;
				vertices[offset1].edge_num++;
			}
		}
		for (int j = 0; j < s; j++) {
			if(vertices[j].edge_num==0)
				vertices[j].type=3;
		}
		return vertices;
	}

	public static void main(String[] args) {
		String xmlFileName = "/test3.xml";
		Document document = Foo.getDocument(xmlFileName);
		// Element root = document.getRootElement();
		List<Node> reactions = document
				.selectNodes("//rdf:RDF/bp:biochemicalReaction");
		List<Node> simpMols = document
				.selectNodes("//rdf:RDF/bp:smallMolecule");
		List<Node> proteins = document.selectNodes("//rdf:RDF/bp:protein");
		int prosize = proteins.size();
		int s = reactions.size() + simpMols.size();
		System.out.println("s is" + s);
		vertex[] vertices = new vertex[s];
		int i = 0;
		for (Node simpMol : simpMols) {
			vertices[i] = new vertex();
			vertices[i].type = 0;
			i++;
		}
		/*
		 * for (Node protein : proteins) { vertices[i]=new vertex();
		 * vertices[i].type=0; i++; }
		 */
		for (Node reaction : reactions) {
			vertices[i] = new vertex();
			vertices[i].type = 1;
			i++;
		}
		System.out.println(i);
		Node begin_node = simpMols.get(0);
		String type1 = begin_node.valueOf("@rdf:ID");
		int p = type1.length();
		String begin_id = type1.substring(6, p);
		int begin = Integer.parseInt(begin_id);
		int offset1, offset2;
		for (Node process : reactions) {
			List<Node> leftnodes = process
					.selectNodes("bp:LEFT/bp:physicalEntityParticipant/bp:PHYSICAL-ENTITY");
			for (Node left : leftnodes) {

				String process_id = process.valueOf("@rdf:ID");
				// System.out.println(process_id);
				int m = process_id.length();
				String process_ps = process_id.substring(6, m);
				// System.out.println(process_ps);
				int process_num = Integer.parseInt(process_ps);
				offset2 = process_num - begin - prosize;
				vertices[offset2].edge_num++;

				String id = left.valueOf("@rdf:resource");
				// System.out.println(id);
				m = id.length();
				String offset_val = id.substring(7, m);
				// System.out.println(offset_val);
				int node_id = Integer.parseInt(offset_val);
				offset1 = node_id - begin;
				vertices[offset1].edge_num++;
			}
			List<Node> rightnodes = process
					.selectNodes("bp:RIGHT/bp:physicalEntityParticipant/bp:PHYSICAL-ENTITY");
			for (Node right : rightnodes) {
				String process_id = process.valueOf("@rdf:ID");
				// System.out.println(process_id);
				int m = process_id.length();
				String process_ps = process_id.substring(6, m);
				// System.out.println(process_ps);
				int process_num = Integer.parseInt(process_ps);
				offset2 = process_num - begin - prosize;
				vertices[offset2].edge_num++;

				String id = right.valueOf("@rdf:resource");
				// System.out.println(id);
				m = id.length();
				String offset_val = id.substring(7, m);
				// System.out.println(offset_val);
				int node_id = Integer.parseInt(offset_val);
				offset1 = node_id - begin;
				vertices[offset1].edge_num++;
			}
		}
		System.out.println("check");
		for (int j = 0; j < s; j++) {
			System.out.println(vertices[j].edge_num);
		}
	}

}
