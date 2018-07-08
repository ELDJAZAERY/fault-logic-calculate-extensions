package ldd;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<ExpressionLogique> exp = new ArrayList<>();
		ArrayList<ExpressionLogique> word = new ArrayList<>();
		ArrayList<Defaut> def = new ArrayList<>();
		ArrayList<Symbole> symb = new ArrayList<>();
		ArrayList<Connecteur_Logique> cnx = new ArrayList<>();
		
		
		symb.add(new Symbole('C',true));
		symb.add(new Symbole('D', true));
		cnx.add(Connecteur_Logique.IMP);
		word.add(new ExpressionLogique(symb, cnx));
		
		symb = new ArrayList<>();
		cnx = new ArrayList<>();


		symb.add(new Symbole('A',true));
		symb.add(new Symbole('B', true));
		symb.add(new Symbole('E', true));
		cnx.add(Connecteur_Logique.AND);
		cnx.add(Connecteur_Logique.IMP);		
		word.add(new ExpressionLogique(symb, cnx));
		
		symb = new ArrayList<>();
		cnx = new ArrayList<>();


		symb.add(new Symbole('E',true));
		symb.add(new Symbole('D', true));
		cnx.add(Connecteur_Logique.OR);		
		word.add(new ExpressionLogique(symb, cnx));
		
		symb = new ArrayList<>();
		cnx = new ArrayList<>();
		

		symb.add(new Symbole('D',true));
		symb.add(new Symbole('F', true));		
		cnx.add(Connecteur_Logique.IMP);		
		word.add(new ExpressionLogique(symb, cnx));
		
		symb = new ArrayList<>();
		cnx = new ArrayList<>();
		
		
		// Defaut 1
		exp = new ArrayList<>();
		
		symb.add(new Symbole('E',true));
		symb.add(new Symbole('F', true));
		cnx.add(Connecteur_Logique.OR);
		
		exp.add(new ExpressionLogique(symb, cnx));
		
		symb = new ArrayList<>();
		cnx = new ArrayList<>();

		symb.add(new Symbole('A',true));
		symb.add(new Symbole('F', true));
		cnx.add(Connecteur_Logique.AND);
		
		exp.add(new ExpressionLogique(symb, cnx));
		
		def.add(new Defaut(exp.get(0),exp.get(1),exp.get(1)));
		
		// Defaut 2
		exp = new ArrayList<>();
		symb = new ArrayList<>();
		cnx = new ArrayList<>();
		
		symb.add(new Symbole('A',true));		
		exp.add(new ExpressionLogique(symb, cnx));
		
		symb = new ArrayList<>();
		cnx = new ArrayList<>();

		symb.add(new Symbole('B',true));		
		exp.add(new ExpressionLogique(symb, cnx));
	
		def.add(new Defaut(exp.get(0),exp.get(1),exp.get(1)));

		// Defaut 3
		exp = new ArrayList<>();
		symb = new ArrayList<>();
		cnx = new ArrayList<>();
		
		symb.add(new Symbole('A',true));		
		symb.add(new Symbole('E',true));		
		cnx.add(Connecteur_Logique.AND);		
		exp.add(new ExpressionLogique(symb, cnx));
		
		symb = new ArrayList<>();
		cnx = new ArrayList<>();

		symb.add(new Symbole('C',true));		
		exp.add(new ExpressionLogique(symb, cnx));
	
		def.add(new Defaut(exp.get(0),exp.get(1),exp.get(1)));

		// Defaut 4
		exp = new ArrayList<>();
		symb = new ArrayList<>();
		cnx = new ArrayList<>();
		
		exp.add(new ExpressionLogique(symb, cnx));
		
		symb = new ArrayList<>();
		cnx = new ArrayList<>();

		symb.add(new Symbole('E',false));		
		exp.add(new ExpressionLogique(symb, cnx));
	
		def.add(new Defaut(exp.get(0),exp.get(1),exp.get(1)));
		
		
		LogiqueDDController.initSys(word, def);
		LogiqueDDController.getExtension();
		
	}
	
	

}
