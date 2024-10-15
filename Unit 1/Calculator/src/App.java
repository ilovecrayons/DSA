public class App {
    public static void main(String[] args) throws Exception {
        NumCalc calc = new NumCalc("1 * 2 ^ 3 + 4 / 2 + 1");
        //expected: 11
         calc.evaluateExpression(OpNode.OpPrio.HIGHEST);
         calc.evaluateExpression(OpNode.OpPrio.HIGHER);
         calc.evaluateExpression(OpNode.OpPrio.LOWER);
    }
}
