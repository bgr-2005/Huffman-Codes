import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class HuffmanTree implements BSTInt{
    

    Node _root; 
    ArrayList<Node> listOfUnassignedNodes = new ArrayList<Node>();

 
    public HuffmanTree(int value, String symbol){ 
         
    }

    @Override 
    public void add(int value, String symbol){
        Node node = new Node(value, symbol); 
        node._right = null; 
        node._left = null; 
        node._parent = null; 

        listOfUnassignedNodes.add(node); 
    }

    public void buildHuffmanTree() { 
        while (listOfUnassignedNodes.size() > 1) { 
            // Find the indices of the two smallest nodes
            int min1Index = 0;
            int min2Index = 1;
            
            // Ensure min1Index points to the smaller of the first two
            if (listOfUnassignedNodes.get(min2Index)._value < listOfUnassignedNodes.get(min1Index)._value) {
                int temp = min1Index;
                min1Index = min2Index;
                min2Index = temp;
            }

            // Loop through the rest to find smaller ones if they exist
            for (int i = 2; i < listOfUnassignedNodes.size(); i++) {
                int val = listOfUnassignedNodes.get(i)._value;
                if (val < listOfUnassignedNodes.get(min1Index)._value) {
                    min2Index = min1Index;
                    min1Index = i;
                } else if (val < listOfUnassignedNodes.get(min2Index)._value) {
                    min2Index = i;
                }
            }

            Node node1 = listOfUnassignedNodes.get(min1Index);
            Node node2 = listOfUnassignedNodes.get(min2Index);

            // Create the parent node
            Node twoLCParent = new Node(node1._value + node2._value, "NoSymbol");
            twoLCParent._parent = null; 
            twoLCParent._left = node1; 
            twoLCParent._right = node2; 
            node1._parent = twoLCParent;
            node2._parent = twoLCParent;

            // Remove the two children safely (remove the higher index first to avoid shifting bugs)
            if (min1Index > min2Index) {
                listOfUnassignedNodes.remove(min1Index);
                listOfUnassignedNodes.remove(min2Index);
            } else {
                listOfUnassignedNodes.remove(min2Index);
                listOfUnassignedNodes.remove(min1Index);
            }

            // Add the new combined parent back to the unassigned list!
            listOfUnassignedNodes.add(twoLCParent);
        }

        // The last remaining node in the list is our root!
        if (!listOfUnassignedNodes.isEmpty()) {
            _root = listOfUnassignedNodes.get(0);
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

            System.out.print(current._symbol + ":" + current._value + " -> "); 


            if (node._left != null) {
                queue.add(node._left);
            }
            if (node._right != null) {
                queue.add(node._right);
            }
        }
    }

    public void printHuffmanEncoding() { 
        if (_root == null) {
            System.out.println("The tree is empty.");
            return;
        }
        System.out.println("--- Huffman Encodings ---");
        generateCodesRecursive(_root, "");
    }

    // Recursive helper method to traverse and build codes
    private void generateCodesRecursive(Node node, String currentCode) {
        if (node == null) {
            return;
        }

    // If it's a leaf node, it represents a character symbol
        if (node._left == null && node._right == null) {
            System.out.println(node._symbol + " : " + currentCode);
            return;
        }

        // Traverse left (add '0') and right (add '1')
        generateCodesRecursive(node._left, currentCode + "0");
        generateCodesRecursive(node._right, currentCode + "1");
    }
}




