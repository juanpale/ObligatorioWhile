package rules

import examples.while_ut1.Parser
import examples.while_ut1.ast.{CheckStateLinter, Stmt}
import org.specs2.mutable._
import rules.Errores._

import scala.collection.JavaConverters._

/**
  * Regla número 9
  * Chequear llamado a funciones que en el caso que se quiera asignar su retorno retornen algo y el tipo coincida.
  */
class rule9 extends Specification {

  sequential

  val case1 =
    """{
      | function int identidad(int a) return a;
      | int b = identidad(a);
      | bool c = identidad(a);
      | print(b);
      |}""".stripMargin

  s"The case1: '$case1' string" should {
    //    println("-----------------------"+ case2)

    s"contain messages:\n" in {

      CheckStateLinter.errores.clear()
      val newState = Parser.parse(case1).value.asInstanceOf[Stmt].checkLinter(new CheckStateLinter())
      CheckStateLinter.generateErrors(newState)

      atLeastOnce (CheckStateLinter.errores.asScala) ((_:String) must be startWith error08("a")) and
        atLeastOnce (CheckStateLinter.errores.asScala) ((_:String) must be startWith error9B) and
          atLeastOnce (CheckStateLinter.errores.asScala) ((_:String) must be startWith error10B("int", "num")) and
            atLeastOnce (CheckStateLinter.errores.asScala) ((_:String) must not startWith error15("b")) and
              atLeastOnce (CheckStateLinter.errores.asScala) ((_:String) must be startWith error15("c"))
    }
  }

}

