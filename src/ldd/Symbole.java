package ldd;

public class Symbole {
	
	private char var ;
	private boolean val ;
	
	public Symbole() {}
	public Symbole(char var , boolean val) {
		this.var = var;
		this.val = val;
	}

	public char getVar() {
		return var;
	}

	public void setVar(char var) {
		this.var = var;
	}

	public boolean isVal() {
		return val;
	}

	public void setVal(boolean val) {
		this.val = val;
	}

	public Symbole nonVar() {
		return new Symbole(var, !val);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj==null || !( obj instanceof Symbole )  )  return false;
		Symbole s = (Symbole) obj;
		
		return s.val == val && s.var ==var;
	}
	
	@Override
	public String toString() {
		return val ? ""+var : "!"+var ;
	}
	
}
