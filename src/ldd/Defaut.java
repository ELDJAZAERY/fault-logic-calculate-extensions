package ldd;

public class Defaut {
	
	private ExpressionLogique prerequis;
	private ExpressionLogique justification;
	private ExpressionLogique consequent;
	
	
	public Defaut(ExpressionLogique prerequis, ExpressionLogique justification, ExpressionLogique consequent) {
		super();
		this.prerequis = prerequis;
		this.justification = justification;
		this.consequent = consequent;
	}


	public ExpressionLogique getPrerequis() {
		if(prerequis.getVars().size()==0) return null;
		return prerequis;
	}


	public void setPrerequis(ExpressionLogique prerequis) {
		this.prerequis = prerequis;
	}


	public ExpressionLogique getJustification() {
		return justification;
	}


	public void setJustification(ExpressionLogique justification) {
		this.justification = justification;
	}


	public ExpressionLogique getConsequent() {
		return consequent;
	}


	public void setConsequent(ExpressionLogique consequent) {
		this.consequent = consequent;
	}


	@Override
	public String toString() {
		return "" + prerequis + ":" + justification + "/" + consequent
				+ "";
	}
	
	@Override
	public boolean equals(Object obj) {
		Defaut d = (Defaut) obj;
		return toString().equals(d.toString());
	}

}
