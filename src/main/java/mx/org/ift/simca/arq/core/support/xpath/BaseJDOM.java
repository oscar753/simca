package mx.org.ift.simca.arq.core.support.xpath;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * La clase BaseJDOM.
 */
abstract class BaseJDOM {

    /**
     * Representa un objeto para el Log de mensajes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseJDOM.class);
    /**
     * La constante rootNode.
     */
    protected static final String ROOT_NODE = "/attList";
    /**
     * Representa el objeto de XPath
     */
    private XPath xPath = null;
    /**
     * Representa el documento que se va a leer
     */
    private Document doc = null;

    public BaseJDOM() {
        this("archivo-inexistente.xml");
    }

    /**
     * Crea una nueva instancia base jdom.
     *
     * @param xmlDocument el xml document
     */
    public BaseJDOM(String xmlDocument) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true); // never forget this!
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.doc = builder.parse(xmlDocument);
            XPathFactory xPathFactory = XPathFactory.newInstance();
            this.xPath = xPathFactory.newXPath();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public BaseJDOM(InputStream xmlDocument) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true); // never forget this!
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.doc = builder.parse(xmlDocument);
            XPathFactory xPathFactory = XPathFactory.newInstance();
            this.xPath = xPathFactory.newXPath();
        } catch (Exception ex) {
            LOGGER.error("Error al realizar la incialización del documento", ex);
        }
    }

    /**
     * Obtiene el listado de nodos contenidos en el archivo XML
     *
     * @param xpathExp Expressión a interpretar por parte de XPath
     * @return Lista de Nodos
     */
    private NodeList getNodeList(String xpathExp) {
        try {
            XPathExpression expr = this.xPath.compile(xpathExp);
            Object result = expr.evaluate(this.doc, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;
            return nodes;
        } catch (XPathExpressionException ex) {
            LOGGER.error("Error al obtener la lista de nodos", ex);
            return null;
        }
    }

    /**
     * Recupera el node value.
     *
     * @param xpathExp el xpath exp
     * @return El node value
     */
    protected String getNodeValue(String xpathExp) {
        return getNodeList(xpathExp).item(0).getNodeValue();
    }

    /**
     * Recupera el type.
     *
     * @param name el name
     * @return El type
     */
    protected String getType(String name) {
        StringBuilder stbd = new StringBuilder();
        stbd.append(ROOT_NODE).append("//attribute[@name='").append(name).append("']/child::*");
        Node node = getNodeList(stbd.toString()).item(0);

        if (node == null) {
            return null;
        } else {
            stbd = new StringBuilder();
            stbd.append("/").append(node.getNodeName());
            return stbd.toString();
        }
    }

    protected String getRoot(String name) {
        StringBuilder stbd = new StringBuilder();
        stbd.append(ROOT_NODE).append("//attribute[@name='").append(name).append("']/");
        return stbd.toString();
    }

    protected String[] getNodesValues(String xpathExp, String pointer) {
        return convertNodeValues(getNodeList(xpathExp), pointer);
    }

    protected String[] getNodesNames(String xpathExp) {
        return convertNodeNames(getNodeList(xpathExp));
    }

    /**
     * Convierte la lista de nodos especificada en un arreglo de cadenas de
     * texto
     *
     * @param list Lista de Nodos que se desea transformar
     * @return Arreglo de cadenas de texto
     */
    private String[] convertNodeNames(NodeList list) {
        int tam = list.getLength();
        String[] caja = new String[tam];
        for (int i = 0; i < tam; i++) {
            caja[i] = list.item(i).getNodeName();
        }
        return caja;
    }

    /**
     * Convierte los valores de la lista de nodos especificadas en un arreglo de
     * cadenas de texto
     *
     * @param list Lista de nodos que se desea convertir
     * @param valor Valor a transformar (clave o nombre del nodo)
     * @return Arreglo de cadenas de texto con las conversiones.
     */
    private String[] convertNodeValues(NodeList list, String valor) {
        int tam = list.getLength();
        String[] caja = new String[tam];
        for (int i = 0; i < tam; i++) {
            NamedNodeMap att = list.item(i).getAttributes();
            String val = att.getNamedItem(valor).getNodeValue();
            caja[i] = val;
        }
        return caja;
    }
}// fin de la clase BaseJDOM **************************************************
