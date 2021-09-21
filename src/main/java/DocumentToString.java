import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;

public class DocumentToString {

  static String xmlStringInput =
    "<person Id='1'>\n" +
    "  <name> John </name>\n" +
    "  <age>  20   </age>\n" +
    "</person>";

  //================================================================================
  // MAIN
  //================================================================================
  public static void main(String[] args) throws Exception {

    //GET DOCUMENT FROM STRING
    Document document       =  stringToDocument(xmlStringInput);

    //CONVERT DOCUMENT TO STRING
    String  xmlStringoUTput = documentToString(document);

    //DISPLAY STRING
    System.out.println(xmlStringoUTput);

  }

  //=======================================================================================
  // DOCUMENT TO STRING
  //=======================================================================================
  public static String documentToString(Document document) throws Exception {

    DOMSource          domSource          = new DOMSource(document);

    StringWriter       stringWriter       = new StringWriter();
    StreamResult       streamResult       = new StreamResult(stringWriter);

    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer        transformer;
                       transformer        = transformerFactory.newTransformer();
                       transformer.transform(domSource, streamResult);

    //RETURN STRING
    return stringWriter.toString();

  }

  //================================================================================
  // STRING TO DOCUMENT
  //================================================================================
  public static Document stringToDocument(String xmlString) throws Exception {

    //READ XML STRING
    InputSource            inputSource            = new InputSource();
                           inputSource.setCharacterStream(new StringReader(xmlString));

    //CREATE DOCUMENT
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                           documentBuilderFactory.setNamespaceAware(true);    //IMPORTANT
    DocumentBuilder        documentBuilder        = documentBuilderFactory.newDocumentBuilder();
    Document               document               = documentBuilder.parse(inputSource);

    //RETURN DOCUMENT
    return document;

  }

}



