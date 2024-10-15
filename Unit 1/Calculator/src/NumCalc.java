
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

    public double evaluateExpression(OpNode.OpPrio opPrio) {
        print();
        RawNode crt = firstNode;
        
        while(crt != null){
            if(crt instanceof OpNode){
                OpNode operatorNode = (OpNode) crt;
                NumNode prevNode = (NumNode) operatorNode.getPrev();
                NumNode nextNode = (NumNode) operatorNode.getNext();

                if(operatorNode.getOpPrio() == opPrio){
                    //System.out.println("Operator recognized");
                    crt = evaluateBinaryOperation(operatorNode);
                    //System.out.println("crt value: " + crt.getRawContent());

                    if (nextNode.getNext() != null){
                        //System.err.println("adding next node");
                        crt.addNext(nextNode.getNext());
                    } 
                    
                    if(prevNode.getPrev() != null){
                        //System.err.println("adding prev node");
                        crt.addPrev(prevNode.getPrev());
                    } else {
                        firstNode = crt;
                    }
                    print();
                    
                    crt = crt.getNext();
                } else {
                    //System.out.println("Operator not recognized");
                    crt = crt.getNext();
                }
                

            } else if(crt instanceof NumNode){
                crt = crt.getNext();
                
            }
        }
        return 0;
    }
    
}
