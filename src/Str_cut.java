import java.util.List;
import org.dom4j.Document;
import org.dom4j.Node;

/**
 * @author zhuofu
 *
 */
 
public class Str_cut {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s="1.33.2";
		String []p=s.split("\\.");
		System.out.println(p.length);
		System.out.println(p[0]);
		System.out.println(p[1]);
		System.out.println(p[2]);
		
		String xmlFileName = "/home/zhuofu/Desktop/test4.xml";
		Document document = Foo.getDocument(xmlFileName);
		// Element root = document.getRootElement();
		List<Node> reactions = document
				.selectNodes("//rdf:RDF/bp:biochemicalReaction");
		List<Node> enzymes =document
		.selectNodes("//rdf:RDF/bp:biochemicalReaction/bp:EC-NUMBER");
		List<Node> simpMols = document
				.selectNodes("//rdf:RDF/bp:smallMolecule");
		List<Node> proteins = document.selectNodes("//rdf:RDF/bp:protein");
		
		for(Node reaction:reactions){
			if(reaction.selectSingleNode("bp:EC-NUMBER")!=null)
		     System.out.println(reaction.selectSingleNode("bp:EC-NUMBER").getText());
		}
		}

}
