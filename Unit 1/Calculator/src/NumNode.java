public class NumNode extends RawNode {
    double numValue;

    public NumNode(String rawContent) {
        super(rawContent);
        numValue = Double.parseDouble(rawContent); // BREAKPOINT
    }

    

    public static NumNode createNode(String rawContent) {
        return new NumNode(rawContent);
    }

    public double getNumValue() {
        return numValue;
    }

}
