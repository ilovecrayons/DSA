import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // get input expression
        Scanner scanner = new Scanner(System.in);
        String expr = "";
        while (expr != "exit" || expr != "quit") {
            System.out.print("Enter expression: ");
            expr = scanner.nextLine();
            try {
                NumCalc calc = new NumCalc(expr);
                calc.evaluateExpression(OpNode.OpPrio.HIGHEST);
                calc.evaluateExpression(OpNode.OpPrio.HIGHER);
                calc.evaluateExpression(OpNode.OpPrio.LOWER);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        scanner.close();
    }
}
