package test;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import examples.while_ut1.ast.CheckStateLinter;
//import examples.while_ut1.Parser;
import examples.while_ut1.ast.Stmt;
import junit.framework.TestCase;

/**
 * Regla n�mero 18 - No escribir nombres de variables o métodos iguales pero que se diferencien en solamente en mayúsculas y minúsculas Ej: ''numeric a=23; numeric A=23''
 */
public class Rule18 extends TestCase {

	Map<Integer, String> datosPruebas = new HashMap<Integer, String>();
	Logger logger = Logger.getAnonymousLogger();
	CheckStateLinter state;

	protected void setUp() throws Exception {
		state = new CheckStateLinter();
		loadData();
		super.setUp();
	}

	protected void loadData() {
		datosPruebas.put(1, "{if(10<=15)then{{int c=10;}} else{int z=11;}}");//ok
		datosPruebas.put(2, "{while(15<=10)do{{{ y=2;\n x=3; }}}}"); //ok
		datosPruebas.put(3, "{while(15<=10)do{ y=2;\n x=3; }}"); //ok 
		datosPruebas.put(4, "{int y=3;int x=32 ;int z= (15==10) ? 2 : 3;}"); //ok
		datosPruebas.put(5, "{{y=2;\n\ny=3;}}"); // ok
		datosPruebas.put(6, "{int y=3;int x=32 ;int z= (15<=10) ? 15<=10 : 15==10;}"); //ok
	}

	public void testData() {
		try {
			Integer numTest =6;   // Setear este valor
			
			Object obj = Parse.parse(datosPruebas.get(numTest));
			logger.log(Level.INFO, obj.toString());
			
			CheckStateLinter check = ((Stmt) obj).checkLinter(state); 
			//logger.log(Level.INFO, "Despues");
			
			String actual = check.toString();
			logger.log(Level.INFO, actual); //borrar
			String expected = "";
			
			if (numTest == 3 ||numTest==4 || numTest==6)
				expected = "";
			else{ 
				if(numTest ==1 || numTest ==2 || numTest ==5 )
					expected = "Offense detected - 17: Existen llaves superfluas";				
				
				}
			
			assertTrue("Se esperaba " + expected + "pero el resultado fue " + actual, actual.contains(expected));
			
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e.getCause());
		}
	}

}