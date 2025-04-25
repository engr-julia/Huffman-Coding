# Huffman Compressor 📦

## Overview
The **Huffman Compressor** is a Java-based application that implements Huffman coding, a **lossless data compression algorithm**. It enables users to:

- Input text and compress it using Huffman coding.
- Visualize the compression process through a **graphical user interface (GUI)** built with Java Swing.

The project is split into two main components:
- **Backend (`HuffmanCoding.java`)**: Handles the core compression logic.
- **Frontend (`HuffmanCompressor.java`)**: Provides the GUI and visualizations.

---

## Features

### ✅ Text Compression
- Encode text using Huffman coding.
- Decode it back to its original form.

### 🖥️ Graphical Interface
A user-friendly GUI featuring:
- A side panel for text input.  
- Vertical tabs to display results.

### 📊 Visualizations
- **Frequency Table**: Displays character frequencies and probabilities.  
- **Huffman Tree**: Visualizes the tree with nodes labeled by characters and probabilities (shown in light blue circles).  
- **Code Alphabet**: Lists Huffman codes for each character.  
- **Encoded/Decoded Strings**: Shows the compressed binary string and the decompressed text with verification.  
- **Compression Metrics**: Includes average length, Shannon entropy, efficiency, and compression ratio.

### 🔍 Input Validation
- Ensures input contains only printable ASCII characters (32–126).

### 🔄 Clear Functionality
- Reset the application state with a **Clear** button.

---

## Project Structure

```plaintext
HuffmanCoding.java      // Backend logic for Huffman coding (tree, encoding/decoding, calculations)
HuffmanCompressor.java  // Frontend GUI using Java Swing for results & visualizations
README.md               // Project overview and instructions
