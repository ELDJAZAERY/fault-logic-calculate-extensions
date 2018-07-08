package ldd;

import java.util.ArrayList;

public class ExpressionLogique {
	
	private ArrayList<Symbole> vars = new ArrayList<>();
	private ArrayList<Connecteur_Logique> cnx = new ArrayList<>();
	
	
	@SuppressWarnings("unchecked")
	public ExpressionLogique(ArrayList<Symbole> vars, ArrayList<Connecteur_Logique> cnx) {
		this.vars = (ArrayList<Symbole>) vars.clone();
		this.cnx = (ArrayList<Connecteur_Logique>) cnx.clone();
	}


	public ArrayList<Symbole> getVars() {
		return vars;
	}


	public void setVars(ArrayList<Symbole> vars) {
		this.vars = vars;
	}


	public ArrayList<Connecteur_Logique> getCnx() {
		return cnx;
	}


	public void setCnx(ArrayList<Connecteur_Logique> cnx) {
		this.cnx = cnx;
	}
	
		
	public ArrayList<ExpressionLogique> DNF(){
		ArrayList<ExpressionLogique> dnf = new ArrayList<>();
		
		if(cnx.size()==0){
			dnf.add(new ExpressionLogique(vars, cnx));
			return dnf;
		}
		
		ArrayList<Symbole> v = new ArrayList<>();
		ArrayList<Connecteur_Logique> c = new ArrayList<>();
		
		ExpressionLogique exp = new ExpressionLogique(vars, cnx);
		
		if(exp.cnx.get(cnx.size()-1).equals(Connecteur_Logique.IMP)){
			exp.vars.remove(vars.size()-1); exp.cnx.remove(cnx.size()-1);
		}
		
		while(exp.cnx.size()!=0 && exp.vars.size()!=0){
			while(exp.cnx.size()!=0 && !exp.cnx.get(0).equals(Connecteur_Logique.OR)){
				c.add(exp.cnx.remove(0));
				v.add(exp.vars.remove(0));
			}
			v.add(exp.vars.remove(0));
			dnf.add(new ExpressionLogique(v, c));
			v = new ArrayList<>(); c = new ArrayList<>();
		}
		
		
		return dnf;
	}

	
	public boolean valide(ArrayList<ExpressionLogique> E){
		
		ArrayList<ExpressionLogique> dnf = this.DNF();
		ArrayList<Symbole> sE = new ArrayList<>();
		
		for(ExpressionLogique exp:E)
			sE.addAll(exp.getVars());
		
		for(ExpressionLogique cnf:dnf){
			if(cnfAppartien(cnf,sE)) return true;
		}
		
		return false;
	}
	
	
	private boolean cnfAppartien(ExpressionLogique cnf,ArrayList<Symbole> sE){
		
		for(Symbole s:cnf.vars){
			if(!sE.contains(s)) return false;
		}
		
		return true;
	}
	
	
	public ExpressionLogique nonExp(){
		ArrayList<Symbole> v = new ArrayList<>();
		ArrayList<Connecteur_Logique> c = new ArrayList<>();
		
		for(Symbole s:vars)
			v.add(s.nonVar());
	
		for(Connecteur_Logique cx:cnx)
			if(cx.equals(Connecteur_Logique.AND))
				c.add(Connecteur_Logique.OR);
			else if(cx.equals(Connecteur_Logique.OR))
				c.add(Connecteur_Logique.AND);
			else c.add(cx);
		
		return new ExpressionLogique(v, c);
		
	}
	
		
	@Override
	public String toString() {
		String s=" ";
		for(int i=0;i<vars.size();i++){
			s+=vars.get(i)+" ";
			  if(i<cnx.size()) s+=cnx.get(i).name()+" ";
		}		
		return s+" ";
	}
	
	@Override
	public boolean equals(Object obj) {
		ExpressionLogique exp = (ExpressionLogique) obj;
		
		if(exp.vars.size()!=vars.size() || exp.cnx.size()!=cnx.size()) return false;
		
		for(int i=0;i<vars.size();i++)
			if(vars.get(i).getVar() != exp.getVars().get(i).getVar() || ( exp.vars.get(i).isVal() != vars.get(i).isVal() ) )
				return false;
		
		for(int i=0;i<cnx.size();i++)
			if(!cnx.get(i).equals(exp.cnx.get(i))) return false;
		
		return true;
	}
	
	
}
