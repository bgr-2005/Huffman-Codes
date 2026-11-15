public class Main { 
    public static void main(String[] args){
        /*BST bst = new BST(101, "a"); 
        for (int i = 0; i < 100; i++){
            bst.add((i), "a"); 
        }
        for (int i = 0; i < 100; i++){
            bst.add((-i), "a"); 
        }
        //bst.printInOrder();
        bst.printBreadthFirst();
    }*/ 

    HuffmanTree huffmanTree = new HuffmanTree(0, null); 

    huffmanTree.add(5, "a"); 
    huffmanTree.add(4, "b"); 
    huffmanTree.add(3, "c"); 
    huffmanTree.add(2, "d"); 
    huffmanTree.add(1, "e"); 

    huffmanTree.buildHuffmanTree();

    //huffmanTree.printBreadthFirst();
    huffmanTree.printHuffmanEncoding(); 


    }
}