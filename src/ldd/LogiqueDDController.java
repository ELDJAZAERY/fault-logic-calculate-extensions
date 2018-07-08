package ldd;

import java.util.ArrayList;

public class LogiqueDDController {
	
	static SysLD sys;
	
	public static void initSys(){
		sys.init();
	}

	public static void initSys(SysLD s){
		sys = s;
	}
	

	public static void initSys(ArrayList<ExpressionLogique> exp , ArrayList<Defaut> defs){
		sys = new SysLD(exp, defs);
	}

	public static void addExpToWord(ExpressionLogique exp){
		sys.addExpToWord(exp);
	}
	
	public static void addDefToSys(Defaut def){
		sys.addDefToSys(def);
	}
	
	
	public static ArrayList<Extension> getExtension(){
		sys.getExtension();
		System.out.println(sys);
		return sys.getExtension();
	}

	
}
