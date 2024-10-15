
public class NumCalc {
    RawNode head;
    RawNode firstNode;

    public void print() {
        RawNode crt = firstNode;
        while (crt != null) {
            System.out.print(crt.getRawContent() + " ");
            crt = crt.getNext();

        }
        System.out.println();
    }

    public RawNode makeNode(String rawContent) throws RuntimeException {
        try {
            try {
                return OpNode.createNode(rawContent);
            } catch (RuntimeException e) {
                return NumNode.createNode(rawContent);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Unknown token: " + rawContent);
        }
    }

    public NumCalc(String expr) {
        head = null;
        RawNode lastNode = null;
        for (String rawContent : expr.split(" ")) {
            RawNode crtNode = makeNode(rawContent); // BREAKPOINT
            if (head == null) {
                head = crtNode;
                firstNode = head;
                lastNode = head;
            } else {
                lastNode = lastNode.addNext(crtNode);
            }

        }
    }

    public NumNode evaluateBinaryOperation(OpNode operatorNode) throws RuntimeException {
        NumNode prevNode = (NumNode) operatorNode.getPrev();
        NumNode nextNode = (NumNode) operatorNode.getNext();
        double prevValue = prevNode.getNumValue();
        double nextValue = nextNode.getNumValue();
        switch (operatorNode.getOpCode()) {
            case ADDITION:
                return NumNode.createNode(String.valueOf(prevValue + nextValue));
            case SUBTRACTION:
                return NumNode.createNode(String.valueOf(prevValue - nextValue));
            case MULTIPLICATION:
                return NumNode.createNode(String.valueOf(prevValue * nextValue));
            case DIVISION:
                try {
                    if (nextValue == 0) {
                        throw new RuntimeException("Cannot divide by zero");
                    }
                    return NumNode.createNode(String.valueOf(prevValue / nextValue));
                } catch (ArithmeticException e) {
                    throw new RuntimeException("Cannot divide by zero");
                }
            case MODULUS:
                try{
                    return NumNode.createNode(String.valueOf(prevValue % nextValue));
                } catch (ArithmeticException e) {
                    throw new RuntimeException("Cannot divide by zero");
                }
            case POWER:
                return NumNode.createNode(String.valueOf(Math.pow(prevValue, nextValue)));
            default:
                throw new RuntimeException("Unknown operator: " + operatorNode.getRawContent());
        }
    }

    public void evaluateExpression(OpNode.OpPrio opPrio) throws RuntimeException {

        RawNode crt = firstNode;

        while (crt != null) {
            if (crt instanceof OpNode) {
                OpNode operatorNode = null;
                NumNode prevNode;
                NumNode nextNode;
                try {
                operatorNode = (OpNode) crt;
                prevNode = (NumNode) operatorNode.getPrev();
                nextNode = (NumNode) operatorNode.getNext();
                } catch (Exception e) {
                    throw new RuntimeException("invalid operands for operator: " + operatorNode.getRawContent());
                }

                if (operatorNode.getOpPrio() == opPrio) {
                    // System.out.println("Operator recognized");
                    crt = evaluateBinaryOperation(operatorNode);
                    // System.out.println("crt value: " + crt.getRawContent());

                    if (nextNode.getNext() != null) {
                        // System.err.println("adding next node");
                        crt.addNext(nextNode.getNext());
                    }

                    if (prevNode.getPrev() != null) {
                        // System.err.println("adding prev node");
                        crt.addPrev(prevNode.getPrev());
                    } else {
                        firstNode = crt;
                    }
                    print();

                    crt = crt.getNext();
                } else {
                    // System.out.println("Operator not recognized");
                    crt = crt.getNext();
                }

            } else if (crt instanceof NumNode) {
                crt = crt.getNext();

            }
        }
    }

}
