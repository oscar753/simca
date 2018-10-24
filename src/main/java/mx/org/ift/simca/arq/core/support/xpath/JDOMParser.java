package mx.org.ift.simca.arq.core.support.xpath;

import java.io.InputStream;
import java.io.Serializable;

/**
 * <p>Title: JDOMParser.java</p>
 *
 * <p>Description: Provee mÃ©todos para obtener los elementos y valores
 * solicitados del archivo de validaciÃ³n XSD.
 *
*/
class JDOMParser extends BaseJDOM implements Serializable {

    /**
     * Representa el valor inicial de la version del serial
     */
    private static final long serialVersionUID = 1L;

    /**
     * Crea una nueva instancia jDOM parser.
     *
     * @param xmlDocument el xml document
     */
    public JDOMParser(String xmlDocument) {
        super(xmlDocument);
    }

    public JDOMParser(InputStream xmlDocument) {
        super(xmlDocument);
    }

    /**
     * Recupera el column attribute.
     *
     * @param tableName el table name
     * @param att el att
     * @return El column attribute
     */
    public String getColumnAttribute(String tableName, String att) {
        return this.getNodeValue(ROOT_NODE + "/table[@name='" + tableName + "']/@" + att);
    }

    /**
     * Recupera el widget name.
     *
     * @param attName el att name
     * @return El widget name
     */
    public String getWidgetName(String attName) {
        return this.getNodeValue(ROOT_NODE + "//attribute[@name='" + attName + "']/@widgetName");
    }

    public String[] getAttributeNamesForValidation() {
        return this.getNodesValues(ROOT_NODE + "//attribute[@validate='true']", "name");
    }

    public String[] getWidgetNamesForValidation() {
        return this.getNodesValues(ROOT_NODE + "//attribute[@validate='true']", "widgetName");
    }

    public String[] getAttributeNamesForValidation2() {
        return this.getNodesValues(ROOT_NODE + "//allowed-access/rol", "id");
    }

    public String[] getKeys() {
        return this.getNodesValues(ROOT_NODE + "/table/column[@isKey='true']", "attname");
    }

    public String[] getColumnWD() {
        return this.getNodesValues(ROOT_NODE + "/table/column[@visible='true']", "width");
    }

    public String[] getAllColumnWD() {
        return this.getNodesValues(ROOT_NODE + "/table/column", "width");
    }

    public String[] getColumnNames() {
        return this.getNodesValues(ROOT_NODE + "/table/column[@visible='true']", "attname");
    }

    public String[] getAllColumnNames() {
        return this.getNodesValues(ROOT_NODE + "/table/column", "attname");
    }

    public int getColumnNum() {
        return this.getNodesValues(ROOT_NODE + "/table/column", "attname").length;
    }

    public String[] getColumnCaptions() {
        return this.getNodesValues(ROOT_NODE + "/table/column[@visible='true']", "caption");
    }

    public String[] getAllColumnCaptions() {
        return this.getNodesValues(ROOT_NODE + "/table/column", "caption");
    }

    /**
     * Recupera el column attribute data.
     *
     * @param attribute el attribute
     * @return El column attribute data
     */
    public String getColumnAttributeData(String attribute) {
        return this.getNodeValue(ROOT_NODE + "/table/@" + attribute);
    }

    /**
     * Recupera el column attribute data2.
     *
     * @param attribute el attribute
     * @return El column attribute data2
     */
    public String getColumnAttributeData2(String attribute) {
        return this.getNodeValue(ROOT_NODE + "/table/column/@" + attribute);
    }

    public String[] getVisibleQuotedColumns() {
        return this.getNodesValues(ROOT_NODE + "/table/column[@quote='true' and @visible='true']", "attname");
    }

    public String[] getAllLabelNames() {
        return this.getNodesValues(ROOT_NODE + "/labels/label", "name");
    }

    public String[] getAllLabelValues() {
        return this.getNodesValues(ROOT_NODE + "/labels/label", "text");
    }

    public String getFormName() {
        return this.getNodeValue(ROOT_NODE + "/formDetail/@name");
    }

    public String getFormInfo() {
        return this.getNodeValue(ROOT_NODE + "/formDetail/@info");
    }

    /**
     * Recupera el equals.
     *
     * @param source el source
     * @return El equals
     */
    public String[] getEquals(String source) {
        return this.getNodesValues(ROOT_NODE + "//check-equals[@source='" + source + "']", "target");
    }

    /**
     * Recupera el message equals.
     *
     * @param source el source
     * @param target el target
     * @return El message equals
     */
    public String getMessageEquals(String source, String target) {
        return this.getNodeValue(ROOT_NODE + "/check-equals[@source='" + source + "' and @target='" + target + "']/@message");
    }
}// fin de la clase JDOMParser ************************************************
