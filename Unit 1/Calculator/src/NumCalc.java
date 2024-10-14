
public class NumCalc {
    RawNode head;
    RawNode firstNode;

    public void print() {
        RawNode crt = firstNode;
        while(crt != null) {
            System.out.print(crt.getRawContent() + " ");
            crt = crt.getNext();
            
        }
        System.out.println();
    }

    public RawNode makeNode(String rawContent) throws RuntimeException {
        try{
            try {
                return OpNode.createNode(rawContent);
            } catch (RuntimeException e) {
                return NumNode.createNode(rawContent);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Unknown input: " + rawContent);
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
                // print()
;            }

        }
    }

    public NumNode evaluateBinaryOperation(OpNode operatorNode){
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
                return NumNode.createNode(String.valueOf(prevValue / nextValue));
            case MODULUS:
                return NumNode.createNode(String.valueOf(prevValue % nextValue));
            case POWER:
                return NumNode.createNode(String.valueOf(Math.pow(prevValue, nextValue)));
            default:
                throw new RuntimeException("Unknown operator: " + operatorNode.getRawContent());
        }
    }

    public double evaluateExpression(){
        print();
        RawNode crt = firstNode;
        while(crt != null){
            //operator precedence
            if(crt instanceof OpNode){
                //System.out.println("Operator: " + crt.getRawContent());
                OpNode operatorNode = (OpNode) crt;
                NumNode prevNode = (NumNode) operatorNode.getPrev();
                NumNode nextNode = (NumNode) operatorNode.getNext();
                if(operatorNode.getOpPrio() == OpNode.OpPrio.LOWER){
                    crt = operatorNode.getNext();
                    continue;
                }

                if(operatorNode.getOpPrio() == OpNode.OpPrio.HIGHER){
                    crt = evaluateBinaryOperation(operatorNode);
                    crt.addNext(nextNode.getNext());
                    crt.addPrev(prevNode.getPrev());
                    crt = crt.getNext();
                }
                print();

            } else if(crt instanceof NumNode){
                crt = crt.getNext();
                
            }
        }
        return 0;
    }
    
}
