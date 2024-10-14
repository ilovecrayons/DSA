public class App {
    public static void main(String[] args) throws Exception {
        NumCalc calc = new NumCalc("1 + 2 * 3 / 4 + 5 + 6");
        calc.evaluateExpression();
    }
}
