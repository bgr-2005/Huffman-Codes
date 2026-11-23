# Huffman-Codes

link to presentation slides: https://docs.google.com/presentation/d/1wQ374kG4ybfPRrwzd3dVcxwV_QwMYuGZcyiete_gD28/edit?usp=sharing 

Huffman coding is a lossless data compression algorithm. It aims to minimize the number of bits used to represent each symbol by assigning common symbols to shorter bit encodings. Huffman coding is optimal among methods of encoding symbols separately, but not among all compression methods. 

Huffman Coding is used as one step within a larger compression algorithm. Used in PNG, JPEG, and GZIP. 

Basic steps for how it works: 

1. Create an Array of Nodes 
We are going to create a binary tree, where each node contains a frequency and symbol. Start with an unordered array of nodes, shown in step ‘a’. 

2. Recursively pair the two least frequent nodes 
Create a parent node for the two nodes which occur least often. The parent’s frequency is now the sum of its children’s frequencies. 

3. Assign codes to paths 
Each symbol’s Huffman code is the path to it in the tree. Left is 0, right is 1. 

These are the main functionalities: 

huffmanTree.printBreadthFirst();              //This prints the symbols and their frequency row by row
huffmanTree.printHuffmanEncoding();           //This prints all the symbols huffman codes 
huffmanTree.printCodesSorted();               //Prints the huffman codes in order 
huffmanTree.printVisualTree();                //Prints a visualization of the actual tree used to generate the codes 
huffmanTree.printCompressionStats();          //Prints the compression statistics for the input 


Huffman Coding has varying effeciency depending on the input, and gets more efficient the more a single symbol is overrepresented. For how often each letter of the alphabet occurs in regular text, it is expected to have ~15.5% compression effeciency. 