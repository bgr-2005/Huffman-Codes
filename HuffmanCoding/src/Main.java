public class Main { 
    public static void main(String[] args){

    HuffmanTree huffmanTree = new HuffmanTree(1, "least"); 

    huffmanTree.add(15, "A"); 
    huffmanTree.add(7, "B"); 
    huffmanTree.add(6, "C"); 
    huffmanTree.add(6, "D"); 
    huffmanTree.add(5, "E"); 

    /*for(int i = 10; i < 100; i++) {
        huffmanTree.add(i, "Probability "+i); 
    }*/

    huffmanTree.buildHuffmanTree();

    //huffmanTree.printBreadthFirst();
    //huffmanTree.printHuffmanEncoding(); 
    huffmanTree.printCodesSorted();
    //huffmanTree.printVisualTree();

    }
}