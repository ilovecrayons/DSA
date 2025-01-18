public class App {
    public static void main(String[] args) throws Exception {
        IntTree tree = new IntTree();
        tree.addValue(2);
        tree.addValue(1);
        tree.addValue(4);
        tree.addValue(3);
        tree.addValue(5);
        System.out.println(tree.preOrder(tree.overallRoot));
        System.out.println(tree.inOrder(tree.overallRoot));
        System.out.println(tree.postOrder(tree.overallRoot));
    }
}