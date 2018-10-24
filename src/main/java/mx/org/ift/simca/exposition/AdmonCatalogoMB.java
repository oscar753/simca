/**
 * 
 */
package mx.org.ift.simca.exposition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import mx.org.ift.simca.arq.core.model.Variable;

/**
 * @author cesar.agustin
 *
 */

@Controller
@Scope("view")
public class AdmonCatalogoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5326514639371473217L;
	

	private List<Variable> variableList = new ArrayList<Variable>();
	private String valorVariable;
	
	@PostConstruct
	public void init() {
		poblarVariables();
	}

	private void poblarVariables() {
		variableList.clear();			
		
		Variable varPoblacion = new Variable();
		varPoblacion.setIdentificador("1");
		varPoblacion.setDescripcion("Población");
		
		Variable estado = new Variable();
		estado.setIdentificador("2");
		estado.setDescripcion("Estado");
		
		variableList.add(varPoblacion);
	}

	public List<Variable> getVariableList() {
		return variableList;
	}	
	public void setVariableList(List<Variable> variableList) {
		this.variableList = variableList;
	}
	
	public String getValorVariable() {
		return valorVariable;
	}
	public void setValorVariable(String valorVariable) {
		this.valorVariable = valorVariable;
	}
	
	
}
