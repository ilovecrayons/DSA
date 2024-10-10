public class OpNode extends RawNode{
    public enum OpCode {
        UNKNOWN,
        ADDITION,
        SUBTRACTION,
        MULTIPLICATION,
        DIVISION,
        MODULUS,
        POWER
    }

    OpCode opCode;

    public OpNode(String rawContent) throws RuntimeException {
        super(rawContent);
        switch (rawContent)  {
            case "+":
                opCode = OpCode.ADDITION;
                break;
            case "-":
                opCode = OpCode.SUBTRACTION;
                break;
            case "*":
                opCode = OpCode.MULTIPLICATION;
                break;
            case "/":
                opCode = OpCode.DIVISION;
                break;
            case "%":
                opCode = OpCode.MODULUS;
                break;
            case "^":
                opCode = OpCode.POWER;
                break;
            default:
                throw new RuntimeException("Unknown operator: " + rawContent);
        }
    }

    static OpNode createNode(String rawContent) {
        return new OpNode(rawContent);
    }
    
}
