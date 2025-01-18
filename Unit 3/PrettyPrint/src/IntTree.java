public class IntTree {
    public IntTreeNode overallRoot;

    public IntTree() {
        overallRoot = null;
    }


    public void addValue(int data){
        if(overallRoot == null){
            overallRoot = new IntTreeNode(data);
        }
        else{
            IntTreeNode currentNode = overallRoot;
            while(currentNode != null){
                if(data < currentNode.data){
                    if(currentNode.left == null){
                        currentNode.left = new IntTreeNode(data);
                        break;
                    }
                    else{
                        currentNode = currentNode.left;
                    }
                } else {
                    if(currentNode.right == null){
                        currentNode.right = new IntTreeNode(data);
                        break;
                    } else {
                        currentNode = currentNode.right;
                    }
                }
            }
        }
    }

    public String preOrder(IntTreeNode root){
        IntTreeNode currentNode = root;
        String ans = "";
        if(currentNode != null){
            ans += "[" + currentNode.data + "]";
            ans += "(" + preOrder(currentNode.left) + ")";
            ans += "(" + preOrder(currentNode.right) + ")";
        }
        return ans;
    }

    public String inOrder(IntTreeNode root){
        IntTreeNode currentNode = root;
        String ans = "";
        if(currentNode != null){
            ans += "(" + inOrder(currentNode.left) + ")";
            ans += "[" + currentNode.data + "]";
            ans += "(" + inOrder(currentNode.right) + ")";
        }
        return ans;
    }

    public String postOrder(IntTreeNode root){
        IntTreeNode currentNode = root;
        String ans = "";
        if(currentNode != null){
            ans += "(" + postOrder(currentNode.left) + ")";
            ans += "(" + postOrder(currentNode.right) + ")";
            ans += "[" + currentNode.data + "]";
        }
        return ans;
    }

}
