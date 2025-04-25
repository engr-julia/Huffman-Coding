import java.util.*;

class HuffmanNode implements Comparable<HuffmanNode> {
    char data;
    int frequency;
    HuffmanNode left, right;

    public HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    @Override
    public int compareTo(HuffmanNode node) {
        return this.frequency - node.frequency;
    }
}

public class HuffmanCoding {
    private static final Map<Character, String> huffmanCodes = new HashMap<>();
    
    // Getter for huffmanCodes
    public static Map<Character, String> getHuffmanCodes() {
        return huffmanCodes;
    }
    
    // Clear huffmanCodes for a new input
    public static void clearHuffmanCodes() {
        huffmanCodes.clear();
    }
    
    // Build Huffman Tree
    public static HuffmanNode buildHuffmanTree(Map<Character, Integer> freqMap) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            priorityQueue.offer(new HuffmanNode(entry.getKey(), entry.getValue()));
        }
        
        if (priorityQueue.size() == 1) {
            HuffmanNode node = priorityQueue.poll();
            HuffmanNode parent = new HuffmanNode('\0', node.frequency);
            parent.left = node;
            return parent;
        }
        
        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            
            HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;
            
            priorityQueue.offer(parent);
        }
        
        return priorityQueue.poll();
    }
    
    // Generate Huffman Codes
    public static void generateCodes(HuffmanNode root, String code) {
        if (root == null) {
            return;
        }
        
        if (root.left == null && root.right == null && root.data != '\0') {
            huffmanCodes.put(root.data, code.length() > 0 ? code : "0");
            return;
        }
        
        generateCodes(root.left, code + "0");
        generateCodes(root.right, code + "1");
    }
    
    // Encode the input string
    public static String encode(String input) {
        StringBuilder encoded = new StringBuilder();
        for (char c : input.toCharArray()) {
            String code = huffmanCodes.get(c);
            if (code == null) {
                throw new IllegalArgumentException("Character " + c + " not found in Huffman codes");
            }
            encoded.append(code);
        }
        return encoded.toString();
    }
    
    // Decode the encoded string
    public static String decode(HuffmanNode root, String encoded) {
        StringBuilder decoded = new StringBuilder();
        HuffmanNode current = root;
        
        for (char bit : encoded.toCharArray()) {
            switch (bit) {
                case '0' -> current = current.left;
                case '1' -> current = current.right;
                default -> throw new IllegalArgumentException("Invalid bit in encoded string: " + bit);
            }
            
            if (current.left == null && current.right == null) {
                decoded.append(current.data);
                current = root;
            }
        }
        
        return decoded.toString();
    }
    
    // Validate input string
    public static boolean isValidInput(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        for (char c : input.toCharArray()) {
            if (c < 32 || c > 126) {
                return false;
            }
        }
        return true;
    }
    
    // Calculate compression ratio
    public static double calculateCompressionRatio(String input, String encoded) {
        int originalBits = input.length() * 8;
        int encodedBits = encoded.length();
        return (double) encodedBits / originalBits * 100;
    }
    
    // Display frequency table (sorted by probability in descending order)
    public static void displayFrequencyTable(Map<Character, Integer> freqMap, int totalChars) {
        System.out.println("\nFrequency Table:");
        System.out.printf("%-10s %-10s %-10s%n", "char", "quant", "p");
        System.out.println("-----------------------------");
        
        List<Map.Entry<Character, Integer>> entries = new ArrayList<>(freqMap.entrySet());
        entries.sort((e1, e2) -> {
            double p1 = (double) e1.getValue() / totalChars;
            double p2 = (double) e2.getValue() / totalChars;
            int compare = Double.compare(p2, p1);
            if (compare == 0) {
                return Character.compare(e1.getKey(), e2.getKey());
            }
            return compare;
        });
        
        for (Map.Entry<Character, Integer> entry : entries) {
            char c = entry.getKey();
            int quantity = entry.getValue();
            double probability = (double) quantity / totalChars;
            System.out.printf("%-10c %-10d %-10.3f%n", c, quantity, probability);
        }
    }
    
    // Display Huffman Tree
    public static void displayHuffmanTree(HuffmanNode root) {
        System.out.println("\nHuffman Tree Structure:");
        printHuffmanTree(root, 0);
    }
    
    // Helper method to print Huffman Tree with indentation
    private static void printHuffmanTree(HuffmanNode node, int level) {
        if (node == null) {
            return;
        }
        
        String indent = "  ".repeat(level);
        
        if (node.data == '\0') {
            System.out.println(indent + "[Internal] (freq: " + node.frequency + ")");
        } else {
            System.out.println(indent + "Char: " + node.data + " (freq: " + node.frequency + ")");
        }
        
        if (node.left != null) {
            System.out.println(indent + "  Left:");
            printHuffmanTree(node.left, level + 1);
        }
        if (node.right != null) {
            System.out.println(indent + "  Right:");
            printHuffmanTree(node.right, level + 1);
        }
    }
    
    // Display Code Alphabet table (sorted by code in ascending order)
    public static void displayCodeAlphabet() {
        System.out.println("\nCode Alphabet:");
        System.out.printf("%-10s %-10s%n", "char", "code");
        System.out.println("---------------------");
        
        List<Map.Entry<Character, String>> entries = new ArrayList<>(huffmanCodes.entrySet());
        entries.sort((e1, e2) -> {
            int compare = e1.getValue().compareTo(e2.getValue());
            if (compare == 0) {
                return Character.compare(e1.getKey(), e2.getKey());
            }
            return compare;
        });
        
        for (Map.Entry<Character, String> entry : entries) {
            System.out.printf("%-10c %-10s%n", entry.getKey(), entry.getValue());
        }
    }
    
    // Calculate Average Length (L)
    public static double calculateAverageLength(Map<Character, Integer> freqMap, int totalChars) {
        double averageLength = 0.0;
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            char c = entry.getKey();
            String code = entry.getValue();
            Integer frequency = freqMap.get(c);  // Handle null case
            if (frequency == null) {
                continue; // Skip characters not in the current frequency map
            }
            double probability = (double) frequency / totalChars;
            averageLength += probability * code.length();
        }
        return averageLength;
    }
    
    // Calculate Shannon Entropy (H)
    public static double calculateEntropy(Map<Character, Integer> freqMap, int totalChars) {
        double entropy = 0.0;
        for (int frequency : freqMap.values()) {
            double probability = (double) frequency / totalChars;
            if (probability > 0) {
                entropy -= probability * (Math.log(probability) / Math.log(2));
            }
        }
        return entropy;
    }
    
    // Display Calculations
    public static void displayCalculations(String input, String encoded, Map<Character, Integer> freqMap) {
        int totalSymbols = input.length();
        int uniqueSymbols = freqMap.size();
        
        double averageLength = calculateAverageLength(freqMap, totalSymbols);
        double entropy = calculateEntropy(freqMap, totalSymbols);
        double efficiency = averageLength != 0 ? entropy / averageLength : 0; // Prevent division by zero
        double residualEfficiency = entropy - averageLength;
        double redundancy = 1.0 - efficiency;
        
        int naiveBits = totalSymbols * 8;
        int huffmanBits = encoded.length();
        double compressionRatio = (double) huffmanBits / naiveBits * 100;
        
        System.out.println("\nCalculations:");
        System.out.printf("The input text has N=%d symbols of which %d are unique. After computing the probability of each and building%n", totalSymbols, uniqueSymbols);
        System.out.println("the tree, we can use it to fill the alphabet table with every encoded symbol. Then, we just have to swap each symbol");
        System.out.println("for its binary result, and we get our encoded string. With the obtained table we can compute the average length of");
        System.out.println("the code words,");
        System.out.printf("• Average Length, L = %.3f bits%n", averageLength);
        System.out.println("Which is very close to the minimum, defined by the Shannon Entropy,");
        System.out.printf("• Entropy, H = %.3f bits/symbol.%n", entropy);
        System.out.println("Other conclusions we can obtain are:");
        System.out.printf("• Code Efficiency: η = H/L = %.3f%n", efficiency);
        System.out.printf("• Residual Efficiency: τ = H - L = %.3f%n", residualEfficiency);
        System.out.printf("• Code Redundancy: r = 1 - η = %.3f%n", redundancy);
        System.out.printf("If we would have encoded the text naively, it would have weighted %d bits. On the other hand, with Huffman is %d bits.%n", naiveBits, huffmanBits);
        System.out.printf("Therefore, we can calculate its compression ratio:%n");
        System.out.printf("• Compression Ratio: %d/%d bits = %.3f%%%n", huffmanBits, naiveBits, compressionRatio);
        System.out.println("Note: Values are truncated to 3 decimals for visualization purposes.");
    }
}