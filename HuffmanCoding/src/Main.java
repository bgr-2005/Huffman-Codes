public class Main { 
    public static void main(String[] args){

    HuffmanTree huffmanTree = new HuffmanTree(1, "least"); 

    /*huffmanTree.add(15, "A"); 
    huffmanTree.add(7, "B"); 
    huffmanTree.add(6, "C"); 
    huffmanTree.add(6, "D"); 
    huffmanTree.add(5, "E"); */

    huffmanTree.add(120, "E");  //letters of the alphabet and what percent they are expected to show up * 10
    huffmanTree.add(91, "T"); 
    huffmanTree.add(80, "A"); 
    huffmanTree.add(75, "O"); 
    huffmanTree.add(70, "I"); 
    huffmanTree.add(67, "N"); 
    huffmanTree.add(57, "S"); 
    huffmanTree.add(61, "H"); 
    huffmanTree.add(60, "R"); 
    huffmanTree.add(40, "D"); 
    huffmanTree.add(40, "L"); 
    huffmanTree.add(30, "C"); 
    huffmanTree.add(30, "U"); 
    huffmanTree.add(30, "M"); 
    huffmanTree.add(20, "W"); 
    huffmanTree.add(20, "F");     
    huffmanTree.add(20, "G"); 
    huffmanTree.add(20, "Y"); 
    huffmanTree.add(20, "P"); 
    huffmanTree.add(15, "B"); 
    huffmanTree.add(10, "V"); 
    huffmanTree.add(8, "K"); 
    huffmanTree.add(2, "J"); 
    huffmanTree.add(2, "X"); 
    huffmanTree.add(1, "Q"); 
    huffmanTree.add(1, "Z"); 

    /*for(int i = 0; i < 1000; i++) {
        int timesValueOccurs = i; 
        huffmanTree.add(timesValueOccurs, "Symbol Occurs " + timesValueOccurs); 
    }*/ 

    huffmanTree.buildHuffmanTree();

    //huffmanTree.printBreadthFirst();              //This prints the symbols and their frequency row by row
    //huffmanTree.printHuffmanEncoding();           //This prints all the symbols huffman codes 
    huffmanTree.printCodesSorted();               //Prints the huffman codes in order 
    //huffmanTree.printVisualTree();                //Prints a visualization of the actual tree used to generate the codes 
    huffmanTree.printCompressionStats();          //Prints the compression statistics for the input 

    }
}