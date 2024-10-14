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

    public enum OpPrio {
        HIGHER,
        LOWER
    }

    OpPrio opPrio;
    OpCode opCode;

    public OpPrio getOpPrio() { return opPrio; }
    public OpCode getOpCode() { return opCode; }

    public OpNode(String rawContent) throws RuntimeException {
        super(rawContent);
        switch (rawContent)  {
            case "+":
                opCode = OpCode.ADDITION;
                opPrio = OpPrio.LOWER;
                break;
            case "-":
                opCode = OpCode.SUBTRACTION;
                opPrio = OpPrio.LOWER;
                break;
            case "*":
                opCode = OpCode.MULTIPLICATION;
                opPrio = OpPrio.HIGHER;
                break;
            case "/":
                opCode = OpCode.DIVISION;
                opPrio = OpPrio.HIGHER;
                break;
            case "%":
                opCode = OpCode.MODULUS;
                opPrio = OpPrio.HIGHER;
                break;
            case "^":
                opCode = OpCode.POWER;
                opPrio = OpPrio.HIGHER;
                break;
            default:
                throw new RuntimeException("Unknown operator: " + rawContent);
        }
    }

    static OpNode createNode(String rawContent) {
        return new OpNode(rawContent);
    }
    
}
