package examples.while_ut1.ast;

import java.util.*;

/** Representación de las asignaciones de valores a variables.
 */
public class FunctionDeclaration extends Stmt {
	public final String id;
	public final String type;
	public final LinkedHashMap<String, String> parameters;
	public final Stmt body;

	//hay que clonar el estado para guardar las variables locales de la funic�n

	public FunctionDeclaration(String id, String type,
			LinkedHashMap<String, String> parameters,Stmt body,
			int line, int column) {
		this.id = id;
		this.type = type;
		this.parameters=parameters;
		this.body=body;
		this.line = line;
		this.column = column;
	}


	@Override public String toString() {
		String devolver="function "+type+" "+id+" (";
		List <String>keys=(List) parameters.values();
		int i=0;
		for (String parameter:keys){
			devolver+=parameter+" "+parameters.get(parameter);
			i++;
			if (i!=keys.size()){
				devolver+=",";
			}
		}
		devolver+=");";
		return devolver;
	}

	@Override public int hashCode() {
		return (Integer) null;
	}

	@Override public boolean equals(Object obj) {
		return (Boolean) null;
	}

	public static FunctionDeclaration generate(Random random, int min, int max) {
		return null;
	}

	@Override
	public State evaluate(State state) {
		return null;

	}

	@Override
	public CheckState check(CheckState s) {
		return null;
	}


	@Override
	public String unparse() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public CheckStateLinter checkLinter(CheckStateLinter s) {
		ObjectState objState = new ObjectState(this.type, false, 1, this);
		s.mapa.put(this.id, objState);
		return s;
	}


}