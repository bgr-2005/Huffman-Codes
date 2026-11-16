import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Collections;
import java.util.Comparator;

public class HuffmanTree implements BSTInt{
    Node _root; 
    ArrayList<Node> listOfUnassignedNodes = new ArrayList<Node>();

 
    public HuffmanTree(int value, String symbol){ 
        _root = new Node(value, symbol); 
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

        System.out.print("\n");
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
        System.out.println("\n");

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





    // Helper class to store a symbol and its corresponding binary code
    class HuffmanCodeEntry {
        String symbol;
        String code;
        int frequency;

        // Constructor with frequency (used for stats)
        public HuffmanCodeEntry(String symbol, String code, int frequency) {
            this.symbol = symbol;
            this.code = code;
            this.frequency = frequency;
        }

        // Constructor without frequency (if you just want symbol and code)
        public HuffmanCodeEntry(String symbol, String code) {
            this.symbol = symbol;
            this.code = code;
            this.frequency = 0;
        }
    }

    public void printCompressionStats() {
        if (_root == null) {
            System.out.println("The tree is empty.");
            return;
        }

        ArrayList<HuffmanCodeEntry> entries = new ArrayList<>();
        collectStatsRecursive(_root, "", entries);

        int totalFrequency = 0;
        int weightedCodeLengthSum = 0;
        int unweightedCodeLengthSum = 0;
        int numberOfSymbols = entries.size();

        for (HuffmanCodeEntry entry : entries) {
            totalFrequency += entry.frequency;
            weightedCodeLengthSum += (entry.frequency * entry.code.length());
            unweightedCodeLengthSum += entry.code.length();
        }

        // 1. Huffman Averages
        double weightedAvgBits = (totalFrequency == 0) ? 0.0 : (double) weightedCodeLengthSum / totalFrequency;
        double unweightedAvgBits = (numberOfSymbols == 0) ? 0.0 : (double) unweightedCodeLengthSum / numberOfSymbols;

        // 2. Uncompressed Fixed-Length Bits per Symbol: ceil(log2(N))
        int fixedBitsPerSymbol = (numberOfSymbols <= 1) ? 1 : (int) Math.ceil(Math.log(numberOfSymbols) / Math.log(2));

        System.out.println("--- Huffman Compression Statistics ---");
        System.out.println("Total Unique Symbols (N): " + numberOfSymbols);
        System.out.println("Uncompressed Fixed-Length Bits/Symbol: " + fixedBitsPerSymbol + " bits");
        System.out.println("Weighted Huffman Avg Bits/Symbol: " + String.format("%.2f", weightedAvgBits) + " bits");
        System.out.println("Unweighted Code Length Avg: " + String.format("%.2f", unweightedAvgBits) + " bits" );
        
        if (fixedBitsPerSymbol > 0) {
            double compressionSavings = ((fixedBitsPerSymbol - weightedAvgBits) / fixedBitsPerSymbol) * 100;
            System.out.println("Estimated Space Savings: " + String.format("%.1f", compressionSavings) + "%\n" );
        }

    }

    // Recursive helper to gather frequency and code length for each leaf
    private void collectStatsRecursive(Node node, String currentCode, ArrayList<HuffmanCodeEntry> entries) {
        if (node == null) {
            return;
        }

        if (node._left == null && node._right == null) {
            entries.add(new HuffmanCodeEntry(node._symbol, currentCode, node._value));
            return;
        }

        collectStatsRecursive(node._left, currentCode + "0", entries);
        collectStatsRecursive(node._right, currentCode + "1", entries);
    }



    // Public entry point to print codes sorted by length (shortest to longest)
    public void printCodesSorted() {
        if (_root == null) {
            System.out.println("The tree is empty.");
            return;
        }

        ArrayList<HuffmanCodeEntry> entries = new ArrayList<>();
        collectCodesRecursive(_root, "", entries);

        // Sort entries by code length (shortest/most common first)
        // If lengths are equal, you can fall back to alphabetical order or frequency
        Collections.sort(entries, new Comparator<HuffmanCodeEntry>() {
            @Override
            public int compare(HuffmanCodeEntry e1, HuffmanCodeEntry e2) {
                return Integer.compare(e1.code.length(), e2.code.length());
            }
        });

        System.out.println("--- Huffman Encodings (Shortest to Longest) ---");
        for (HuffmanCodeEntry entry : entries) {
            System.out.println(entry.symbol + " : " + entry.code + " (length: " + entry.code.length() + ")");
        }
        System.out.print("\n"); 
    }

    // Recursive helper to gather all leaf nodes and their codes into a list
    private void collectCodesRecursive(Node node, String currentCode, ArrayList<HuffmanCodeEntry> entries) {
        if (node == null) {
            return;
        }

        // If it's a leaf node, add it to our list with its symbol, code, and frequency value
        if (node._left == null && node._right == null) {
            entries.add(new HuffmanCodeEntry(node._symbol, currentCode, node._value));
            return;
        }

        collectCodesRecursive(node._left, currentCode + "0", entries);
        collectCodesRecursive(node._right, currentCode + "1", entries);
    }


        // Public entry point to print the tree visually in the console
    public void printVisualTree() {
        if (_root == null) {
            System.out.println("The tree is empty.");
            return;
        }
        System.out.println("--- Huffman Tree Structure ---");
        printTreeRecursive("", _root, false);
    }

    // Recursive helper to build the ASCII branches
    private void printTreeRecursive(String prefix, Node node, boolean isLeft) {
        if (node != null) {
            System.out.print(prefix);
            System.out.print(isLeft ? "├── " : "└── ");
            
            // If it's a leaf node, show its symbol and value. Otherwise, show the combined frequency sum.
            if (node._left == null && node._right == null) {
                System.out.println("'" + node._symbol + "' (" + node._value + ")");
            } else {
                System.out.println("[Sum: " + node._value + "]");
            }

            // Recurse down to right and left children
            printTreeRecursive(prefix + (isLeft ? "│   " : "    "), node._right, true);
            printTreeRecursive(prefix + (isLeft ? "│   " : "    "), node._left, false);
        }
    }


}




