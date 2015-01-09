package domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "pattern")
@XmlType(propOrder = {"name", "context", "problem", "solution", "diagram", "consequences", "purposeString", "scopeString"})
public class Pattern implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String context;
	private String problem;
	private String solution;
	private String diagram;
	private String consequences;
	private String purposeString;
	private String scopeString;
	private Category purpose;
	private Category scope;
	
	public Pattern() {
		
	}
	
	public Pattern(String n,Purpose p,Scope s) {
		name = n;
		purpose = p;
		scope = s;
	}
	
	public Pattern(String n,String cxt,String p,String s,String d,String con,Category pu,Category sc) {
		name = n;
		context = cxt;
		problem = p;
		solution = s;
		diagram = d;
		consequences = con;
		purpose = pu;
		purposeString = pu.getName();
		scope = sc;
		scopeString = sc.getName();
	}
	
	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getDiagram() {
		return diagram;
	}

	public void setDiagram(String diagram) {
		this.diagram = diagram;
	}

	public String getConsequences() {
		return consequences;
	}

	public void setConsequences(String consequences) {
		this.consequences = consequences;
	}

	public void setPurpose(Category p) {
		purpose = p;
		purposeString = p.getName();
	}
	
	@XmlTransient
	public Category getPurpose() {
		return purpose;
	}
	
	public void setScope(Category s) {
		scope = s;
		scopeString = s.getName();
	}
	
	@XmlTransient
	public Category getScope() {
		return scope;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	@XmlElement(name = "scope")
	public String getScopeString(){
		return scopeString;
	}
	@XmlElement(name = "purpose")
	public String getPurposeString(){
		return purposeString;
	}
}
