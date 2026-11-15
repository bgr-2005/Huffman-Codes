import java.util.ArrayDeque; 
import java.util.Queue;

public class BST implements BSTInt{ 
    Node _root; 

    public BST(int value, String symbol){ 
        _root = new Node(value, symbol); 
    }

    @Override 
    public void add(int value, String symbol){
        Node node = new Node(value, symbol); 
        if(_root == null){ 
            _root = node; 
        }
        else { 
            addRec(_root, node); 
        }
        
    }

    void addRec(Node parent, Node node) {
        if (node._value < parent._value) {
            if (parent._left == null) {
                parent._left = node;
                node._parent = parent;
            } else {
                addRec(parent._left, node);
            }
        } else { // Greater values and duplicates go right
            if (parent._right == null) {
                parent._right = node;
                node._parent = parent;
            } else {
                addRec(parent._right, node);
            }
        }
    }

    @Override 
    public void printInOrder(){
        System.out.println("\n Printing BST in order"); 
        printInOrderRec(_root); 
        System.out.println();
    }

    public void printInOrderRec(Node node){ 
        if(node == null) {
            return; 
        }

        if( node._left != null)
            printInOrderRec(node._left);
        System.out.print(" -> " + node._symbol+ ":" + node._value ); 
        if (node._right != null)
            printInOrderRec(node._right);
    }

    public boolean searchValue(int value) {
        Node current = _root;

        while (current != null) {
            if (value == current._value) {
                return true;
            } else if (value < current._value) {
                current = current._left;
            } else {
                current = current._right;
            }
        } 

        return false;
    }


/*public boolean searchSymbol(String symbol) {
    Node current = _root;

    while (current != null) {
        if (symbol == current._symbol) {
            return true;
        } else if (symbol < current._symbol) {
            current = current._left;
        } else {
            current = current._right;
        }
    } 

    return false;
}*/ 

    /*private static class CodeAtNode {
        Node node;
        String code;

        CodeAtNode(Node node, String code) {
            this.node = node;
            this.code = code;
        }
    }*/ 


    @Override  
    public void printBreadthFirst() {
        if (_root == null) {
            System.out.println("The tree is empty.");
            return;
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(_root);

        while (!queue.isEmpty()) {
            Node current = queue.remove();
            Node node = current;

            System.out.print(current._value + " -> "); 


            if (node._left != null) {
                queue.add(node._left);
            }
            if (node._right != null) {
                queue.add(node._right);
            }
        }
    }
}

    /*@Override 
    public void printBreadthFirst(){
        System.out.println("\n Printing BST Depth First"); 
        printBreadthFirstRec(_root); 
        System.out.println();
    }

    public void printBreadthFirstRec(Node node){ 
        if (_root == null)
            return;

        //return res;
        
    }*/ 
