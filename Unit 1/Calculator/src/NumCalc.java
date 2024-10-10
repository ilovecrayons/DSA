public class NumCalc {
    RawNode head;
    RawNode firstNode;

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
        for (String rawContent : expr.split(" ")) {
            RawNode crtNode = NumNode.createNode(rawContent); // BREAKPOINT
            if (head == null) {
                head = crtNode;
                firstNode = head;
            } else {
                head.addNext(crtNode); 
            }
        }
    }
}
