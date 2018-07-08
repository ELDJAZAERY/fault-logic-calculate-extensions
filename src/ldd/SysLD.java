package ldd;

import java.util.ArrayList;

public class SysLD {
	
	private ArrayList<ExpressionLogique> word = new ArrayList<>();
	private ArrayList<Defaut> defauts = new ArrayList<>();

	private ArrayList<Extension> extension = new ArrayList<>();

	public SysLD() {}
	public SysLD(ArrayList<ExpressionLogique> word, ArrayList<Defaut> defauts) {
		this.word = word;
		this.defauts = defauts;
		extension = new ArrayList<>();
	}
	
	public ArrayList<ExpressionLogique> getWord() {
		return word;
	}
	public void setWord(ArrayList<ExpressionLogique> word) {
		this.word = word;
		extension = new ArrayList<>();
	}
	public ArrayList<Defaut> getDefauts() {
		return defauts;
	}
	public void setDefauts(ArrayList<Defaut> defauts) {
		this.defauts = defauts;
		extension = new ArrayList<>();
	}
	
	public ArrayList<Extension> getExtension() {
		if(extension.size()==0) calculExtensions();
		return extension;
	}
	
	
	public void init(){
		word = new ArrayList<>();
		defauts = new ArrayList<>();
		extension = new ArrayList<>();
	}
	
	public void addExpToWord(ExpressionLogique exp){
		word.add(exp);
		extension = new ArrayList<>();
	}
	
	public void addDefToSys(Defaut def){
		defauts.add(def);
		extension = new ArrayList<>();
	}

	private ArrayList<ExpressionLogique> simplifier(ArrayList<ExpressionLogique> word){
		ArrayList<ExpressionLogique> wd = new ArrayList<>();
		for(ExpressionLogique exp:word)
			wd.add(elimineImp(exp));
		
		return wd;
	}
	
	private ExpressionLogique elimineImp(ExpressionLogique exp){
		boolean imp = false;
		for(Connecteur_Logique c:exp.getCnx())
			if(c.equals(Connecteur_Logique.IMP)) imp = true;
		if(!imp) return exp;
		
		ExpressionLogique expClone = new ExpressionLogique(exp.getVars(),exp.getCnx());
		expClone.getVars().remove(expClone.getVars().size()-1);
		expClone.getCnx().remove(expClone.getCnx().size()-1);
		expClone = expClone.nonExp();
		
		expClone.getCnx().add(Connecteur_Logique.OR);
		expClone.getVars().add(exp.getVars().get(exp.getVars().size()-1));
		
		return expClone;
	}

	
	@SuppressWarnings("unchecked")
	private void calculExtensions(){		
		word = simplifier(word);
		ArrayList<ExpressionLogique> E ;
		for(Defaut def:defauts){
			E = (ArrayList<ExpressionLogique>) word.clone();
			
			ArrayList<Extension> sols = new ArrayList<>();
			calcExtensionRacine(def,defauts,E,sols);
			for(Extension s:sols){
				if(!extension.contains(s)) extension.add(s);				
			}
		}
		
	}
		

	@SuppressWarnings("unchecked")
	private void calcExtensionRacine(Defaut racine,ArrayList<Defaut> defs,ArrayList<ExpressionLogique> E,ArrayList<Extension> sols){

		ArrayList<ExpressionLogique> EE = (ArrayList<ExpressionLogique>) E.clone();
		ArrayList<Defaut> Defautsclone = (ArrayList<Defaut>) defs.clone();
		Defautsclone.remove(racine);
		
		
		if(racine.getPrerequis()==null){
			EE.add(racine.getConsequent());
		}else 
		if(!EE.contains(racine.getConsequent()) && racine.getPrerequis().valide(EE) && !racine.getJustification().nonExp().valide(EE))
			EE.add(racine.getConsequent());
		
		if(Defautsclone.isEmpty()){
			sols.add(new Extension(EE));
		}
			
		for(Defaut def:Defautsclone)
			calcExtensionRacine(def,Defautsclone,EE,sols);

	}
	
	
	
	@Override
	public String toString() {
		return "Sys Logique Des Defauts [ \n Word = { " + word + " } ,\n Defauts = { " + defauts + " } ,\n Extension={" + extension + " }\n ]";
	}
	
	

}
