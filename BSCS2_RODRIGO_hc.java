import java.util.*;

public class BSCS2_RODRIGO_hc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Huffman Compressor");
        System.out.println("Choose an option:");
        System.out.println("1. Run backend in console (display tables and calculations)");
        System.out.println("2. Launch GUI");
        System.out.print("Enter your choice (1 or 2): ");

        int choice;
        try {
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Exiting...");
            scanner.close();
            return;
        }

        if (choice == 1) {
            // Run backend in console
            System.out.print("Enter text to compress (printable ASCII characters only): ");
            String input = scanner.nextLine().trim();

            if (!HuffmanCoding.isValidInput(input)) {
                System.out.println("Error: Input must be non-empty and contain only printable ASCII characters (e.g., letters, numbers, symbols).");
                scanner.close();
                return;
            }

            try {
                // Step 1: Calculate frequency
                Map<Character, Integer> freqMap = new HashMap<>();
                for (char c : input.toCharArray()) {
                    freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
                }

                // Step 2: Display frequency table
                HuffmanCoding.displayFrequencyTable(freqMap, input.length());

                // Step 3: Build Huffman Tree
                HuffmanNode root = HuffmanCoding.buildHuffmanTree(freqMap);

                // Step 4: Display Huffman Tree
                HuffmanCoding.displayHuffmanTree(root);

                // Step 5: Generate Huffman Codes
                HuffmanCoding.clearHuffmanCodes();
                HuffmanCoding.generateCodes(root, "");

                // Step 6: Display Code Alphabet
                HuffmanCoding.displayCodeAlphabet();

                // Step 7: Encode
                String encoded = HuffmanCoding.encode(input);
                System.out.println("\nEncoded string: " + encoded);

                // Step 8: Decode
                String decoded = HuffmanCoding.decode(root, encoded);
                System.out.println("Decoded string: " + decoded);

                // Step 9: Verify and show compression
                if (input.equals(decoded)) {
                    System.out.println("Verification: Decoding successful!");
                    double compressionRatio = HuffmanCoding.calculateCompressionRatio(input, encoded);
                    System.out.printf("Compression ratio: %.2f%% (encoded bits: %d, original bits: %d)%n",
                        compressionRatio, encoded.length(), input.length() * 8);

                    // Step 10: Display Calculations
                    HuffmanCoding.displayCalculations(input, encoded, freqMap);
                } else {
                    System.out.println("Verification: Decoding failed!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else if (choice == 2) {
            // Launch GUI
            javax.swing.SwingUtilities.invokeLater(() -> new HuffmanCompressor());
        } else {
            System.out.println("Invalid choice. Exiting...");
        }

        scanner.close();
    }
}