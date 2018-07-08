package ldd;

import java.util.ArrayList;

public class Extension {
	
	private ArrayList<ExpressionLogique> extentions = new ArrayList<>();

	public Extension() {}

	public Extension(ArrayList<ExpressionLogique> extentions) {
		this.extentions = extentions;
	}

	public ArrayList<ExpressionLogique> getExpress() {
		return extentions;
	}
	
	public void addAllSolutoin(ArrayList<ExpressionLogique> sols){
		extentions.addAll(sols);
	}

	@Override
	public String toString() {
		return " [" + extentions + "] \n";
	}

	@Override
	public boolean equals(Object obj) {
		Extension ext = (Extension) obj;
		for(ExpressionLogique exp:ext.getExpress())
			if(!extentions.contains(exp)) return false;
		
		return true;
	}
	
}
