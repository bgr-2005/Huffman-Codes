public class Main { 
    public static void main(String[] args){

    HuffmanTree huffmanTree = new HuffmanTree(1, "least"); 

    /*huffmanTree.add(5, "a"); 
    huffmanTree.add(4, "b"); 
    huffmanTree.add(3, "c"); 
    huffmanTree.add(2, "d"); 
    huffmanTree.add(1, "e"); */ 

    for(int i = 10; i < 100; i++) {
        huffmanTree.add(i, "Probability "+i); 
    }

    huffmanTree.buildHuffmanTree();

    //huffmanTree.printBreadthFirst();
    //huffmanTree.printHuffmanEncoding(); 
    huffmanTree.printCodesSorted();
    //huffmanTree.printVisualTree();

    }
}