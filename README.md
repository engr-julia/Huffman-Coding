Huffman Compressor 📦
Overview
The Huffman Compressor is a Java-based application that implements Huffman coding, a lossless data compression algorithm. It enables users to:

Input text and compress it using Huffman coding.
Visualize the compression process through a graphical user interface (GUI) built with Java Swing.

The project is split into two main components:

Backend (HuffmanCoding.java): Handles the core compression logic.
Frontend (HuffmanCompressor.java): Provides the GUI and visualizations.


Features

Text CompressionEncode text using Huffman coding and decode it back to its original form.

Graphical InterfaceA user-friendly GUI featuring:  

A side panel for text input.  
Vertical tabs to display results.


Visualizations  

Frequency Table: Displays character frequencies and probabilities.  
Huffman Tree: Visualizes the tree with nodes labeled by characters and probabilities (shown in light blue circles).  
Code Alphabet: Lists Huffman codes for each character.  
Encoded/Decoded Strings: Shows the compressed binary string and the decompressed text with verification.  
Compression Metrics: Includes average length, Shannon entropy, efficiency, and compression ratio.


Input ValidationEnsures input contains only printable ASCII characters (32-126).

Clear FunctionalityReset the application state with a "Clear" button.



Project Structure

HuffmanCoding.java: Backend logic for Huffman coding (tree construction, encoding/decoding, and calculations).  
HuffmanCompressor.java: Frontend GUI implementation using Java Swing for displaying results and visualizations.  
README.md: This file, providing an overview and instructions.


Prerequisites

Java Development Kit (JDK): Version 8 or higher.  
Java Runtime Environment (JRE): Required to run the application.  
IDE (Optional): IntelliJ IDEA, Eclipse, or any Java IDE for easier compilation and running.


Setup Instructions

Clone or Download the RepositoryDownload the project files or clone the repository to your local machine.

Ensure Java is InstalledVerify Java is installed by running:  
java -version
javac -version


Compile the Java FilesOpen a terminal in the project directory and compile the files:  
javac HuffmanCoding.java HuffmanCompressor.java


Run the ApplicationExecute the main class:  
java HuffmanCompressor

The GUI window will open, ready for input.



Usage

Launch the ApplicationFollow the setup instructions to run the application.

Enter Text  

In the side panel, type your text into the input field (e.g., "HELLO").  
Input must contain only printable ASCII characters (spaces, letters, numbers, symbols).


Encode the Text  

Click the "Encode" button to process the text.  
The application will display:  
Frequency Table: Character frequencies and probabilities.  
Huffman Tree: A graphical tree with nodes showing combined characters and weights.  
Code Alphabet: Huffman codes for each character.  
Encoded String: The compressed binary string.  
Decoded String: The decompressed text with verification.  
Calculations: Metrics like average length, entropy, efficiency, and compression ratio.




Clear the InterfaceClick the "Clear" button to reset all fields and start over.



Example
Input: "HELLO"Output:  

Frequency Table:  char      quant     p
-----------------------------
L         2         0.400
H         1         0.200
E         1         0.200
O         1         0.200


Huffman Tree: Visual tree with nodes (e.g., root "HELLO", weight 1.0, child nodes).  
Code Alphabet:  char      code
---------------------
L         1
H         00
E         01
O         10


Encoded String: "00101110"  
Decoded String: "HELLO" with "Decoding successful!" message.  
Calculations: Compression ratio (e.g., 80.00%).


Limitations

Input is restricted to printable ASCII characters (32-126).  
No file I/O support; text must be entered manually in the GUI.  
The Huffman tree visualization may become cluttered for very long inputs.


Future Improvements

Add support for decoding from an encoded string input.  
Implement file I/O to load and save text.  
Enhance the tree visualization with edge labels ("0" and "1").  
Support a broader range of characters (e.g., Unicode).


License
This project is open-source and available under the MIT License. Feel free to use, modify, and distribute it as needed.

Contact
For questions, suggestions, or contributions, please reach out via the repository's issue tracker.
