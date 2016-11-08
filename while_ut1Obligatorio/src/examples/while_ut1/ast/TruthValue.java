package examples.while_ut1.ast;

import java.util.*;

/** Representación de valores de verdad (cierto o falso).
*/
public class TruthValue extends BExp {
	public final Boolean value;

	public TruthValue(Boolean value) {
		this.value = value;
	}

	@Override public String unparse() {
		return value ? "true" : "false";
	}

	@Override public String toString() {
		return "TruthValue("+ value +")";
	}

	@Override public int hashCode() {
		int result = 1;
		result = result * 31 + (this.value == null ? 0 : this.value.hashCode());
		return result;
	}

	@Override public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		TruthValue other = (TruthValue)obj;
		return (this.value == null ? other.value == null : this.value.equals(other.value));
	}

	public static TruthValue generate(Random random, int min, int max) {
		Boolean value; 
		value = random.nextBoolean();
		return new TruthValue(value);
	}

	@Override
	public Object evaluate(State state) {
		return value;
	}
	
	@Override
	public String check(CheckState s){
		return "Boolean";
	}

	@Override
	public String checkLinter(CheckStateLinter s) {
		// TODO Auto-generated method stub
		return "Boolean";
	}

	@Override
	public Exp optimize() {
		return this;
	}

	@Override
	public int getLine() {
		return 0;
	}

	@Override
	public int getColumn() {
		return 0;
	}
}


