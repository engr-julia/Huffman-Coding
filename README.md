Huffman Compressor
Overview
The Huffman Compressor is a Java-based application that implements Huffman coding, a lossless data compression algorithm. It allows users to input text, compress it using Huffman coding, and visualize the compression process through a graphical user interface (GUI) built with Java Swing. The project consists of two main components: the backend (2BSCS2_RODRIGO_hc.java) for core compression logic and the frontend (HuffmanCompressor.java) for the GUI and visualization.
Features

Text Compression: Encode text using Huffman coding and decode it back to the original form.
Graphical Interface: A user-friendly GUI with a side panel for input and vertical tabs to display results.
Visualizations:
Frequency table showing character frequencies and probabilities.
Huffman tree visualization with nodes labeled by characters and probabilities (displayed in light blue circles).
Code alphabet table showing the Huffman codes for each character.
Encoded and decoded strings with verification.
Compression metrics including average length, Shannon entropy, efficiency, and compression ratio.


Input Validation: Ensures input contains only printable ASCII characters (32-126).
Clear Functionality: Reset the application state with a "Clear" button.

Project Structure

HuffmanCoding.java: Backend logic for Huffman coding, including tree construction, encoding/decoding, and compression calculations.
HuffmanCompressor.java: Frontend GUI implementation using Java Swing, displaying the compression results and visualizations.
README.md: This file, providing an overview and instructions for the project.

Prerequisites

Java Development Kit (JDK): Version 8 or higher.
Java Runtime Environment (JRE): To run the application.
IDE (Optional): IntelliJ IDEA, Eclipse, or any Java IDE for easier compilation and running.

Setup Instructions

Clone or Download the Repository:
Download the project files or clone the repository to your local machine.


Ensure Java is Installed:
Verify Java is installed by running java -version and javac -version in your terminal/command prompt.


Compile the Java Files:
Open a terminal in the project directory.
Compile the files using:javac HuffmanCoding.java HuffmanCompressor.java




Run the Application:
Execute the main class:java HuffmanCompressor


The GUI window should open, ready for input.



Usage

Launch the Application:
Run the application as described in the setup instructions.


Enter Text:
In the side panel, type your text into the input field (e.g., "HELLO").
The input must contain only printable ASCII characters (spaces, letters, numbers, symbols).


Encode the Text:
Click the "Encode" button to process the text.
The application will display:
Frequency Table: Character frequencies and probabilities.
Huffman Tree: A graphical tree with nodes showing combined characters and weights.
Code Alphabet: Huffman codes for each character.
Encoded String: The compressed binary string.
Decoded String: The decompressed text with verification.
Calculations: Compression metrics like average length, entropy, efficiency, and compression ratio.




Clear the Interface:
Click the "Clear" button to reset all fields and start over.



Example

Input: "HELLO"
Output:
Frequency Table: Shows frequencies (e.g., H:1, E:1, L:2, O:1) and probabilities.
Huffman Tree: Visual tree with nodes (e.g., root "HELLO", weight 1.0, children nodes).
Code Alphabet: Huffman codes (e.g., H:00, E:01, L:1, O:10).
Encoded String: A binary string (e.g., "00101110").
Decoded String: "HELLO" with a "Decoding successful!" message.
Calculations: Metrics like compression ratio (e.g., 80.00%).



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
