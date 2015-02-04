import java.util.Iterator;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.Element;

public class Foo {
	/**
	 * This method is used to load the xml file to a document and return it
	 * 
	 * @param xmlFileName
	 *            is the xml file name to be loaded
	 * @return Document
	 */
	public static Document getDocument(final String xmlFileName) {
		Document document = null;
		SAXReader reader = new SAXReader();
		try {
			document = reader.read(xmlFileName);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String xmlFileName = "/home/zhuofu/Desktop/test2.xml";
		// String xPath = "//Root/Address";
		Document document = getDocument(xmlFileName);
		Element root = document.getRootElement();
		System.out.println(root.getName());
		for (Iterator i = root.elements().iterator(); i.hasNext();) {
			Element elem = (Element) i.next();
			System.out.println(elem.attributeValue("ID"));
			List<Attribute> list = elem.attributes();
			for (Attribute attribute : list) {
				String name = attribute.getName();
				System.out.println("Name : " + name);
			}
		}
		// for(Iterator i = root.elements().iterator(); i.hasNext();)
		// {Element elem = (Element)i.next();
		// System.out.println(elem.getName());}

		List<Node> nodes = document
				.selectNodes("//rdf:RDF/bp:biochemicalReaction");
		List<Node> simpMol = document.selectNodes("//rdf:RDF/bp:smallMolecule");
		List<Node> protein = document.selectNodes("//rdf:RDF/bp:protein");
		int s = nodes.size() + simpMol.size() + protein.size();
		System.out.println("s is" + s);
		for (Node node : nodes) {
			String studentId = node.valueOf("@rdf:ID");
			System.out.println(" Id : " + studentId);
			String nodeleft = node
					.selectSingleNode(
							"//bp:biochemicalReaction/bp:LEFT/bp:physicalEntityParticipant/bp:PHYSICAL-ENTITY")
					.valueOf("@rdf:resource");
			System.out.println("Left:" + nodeleft);
			vertex v = new vertex();

		}

		// List<Node> nodes = document.selectNodes( xPath );
		// for (Node node : nodes)a
		// {
		// String studentId = node.valueOf( "@studentId" );
		// System.out.println( "Student Id : " + studentId );
		// }
	}
}
