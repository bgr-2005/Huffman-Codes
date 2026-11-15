public class Main { 
    public static void main(String[] args){

    HuffmanTree huffmanTree = new HuffmanTree(1, "least"); 

    /*huffmanTree.add(15, "A"); 
    huffmanTree.add(7, "B"); 
    huffmanTree.add(6, "C"); 
    huffmanTree.add(6, "D"); 
    huffmanTree.add(5, "E"); */ 

    for(int i = 10; i < 100; i++) {
        int timesValueOccurs = i*i*i; 
        huffmanTree.add(timesValueOccurs, "Symbol Occurs " + timesValueOccurs); 
    }

    huffmanTree.buildHuffmanTree();

    //huffmanTree.printBreadthFirst();              //This prints the symbols and their frequency row by row
    //huffmanTree.printHuffmanEncoding();           //This prints all the symbols huffman codes 
    huffmanTree.printCodesSorted();               //Prints the huffman codes in order 
    //huffmanTree.printVisualTree();                //Prints a visualization of the actual tree used to generate the codes 
    huffmanTree.printCompressionStats();            //Prints the compression statistics for the input 

    }
}