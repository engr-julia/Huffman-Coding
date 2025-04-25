import java.awt.*;
import java.awt.geom.QuadCurve2D;
import java.util.*;
import javax.swing.*;

public class HuffmanCompressor {
    private final JFrame frame;
    private JTextField inputField;
    private JTextArea freqTableArea;
    private HuffmanTreePanel huffmanTreePanel;
    private JTextArea codeAlphabetArea;
    private JTextArea calculationsArea;
    private JTextArea encodedArea;
    private JTextArea decodedArea;
    private HuffmanNode huffmanRoot;

    public HuffmanCompressor() {
        // Initialize the GUI
        frame = new JFrame("Huffman Compressor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout(10, 10));

        // Styling
        frame.getContentPane().setBackground(new Color(240, 240, 240));
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font buttonFont = new Font("Arial", Font.PLAIN, 12);

        // Side panel (left) for input and buttons
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBackground(new Color(200, 200, 200)); // Slightly darker gray
        sidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        sidePanel.setPreferredSize(new Dimension(200, 600));

        JLabel inputLabel = new JLabel("Enter text to encode:");
        inputLabel.setFont(labelFont);
        inputLabel.setForeground(new Color(0, 51, 102));
        inputLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputField = new JTextField(15);
        inputField.setBackground(Color.WHITE);
        inputField.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton encodeButton = new JButton("Encode");
        encodeButton.setFont(buttonFont);
        encodeButton.setBackground(new Color(144, 238, 144)); // Light green
        encodeButton.setForeground(Color.BLACK);
        encodeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton clearButton = new JButton("Clear");
        clearButton.setFont(buttonFont);
        clearButton.setBackground(new Color(144, 238, 144)); // Light green
        clearButton.setForeground(Color.BLACK);
        clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidePanel.add(Box.createVerticalStrut(20));
        sidePanel.add(inputLabel);
        sidePanel.add(Box.createVerticalStrut(10));
        sidePanel.add(inputField);
        sidePanel.add(Box.createVerticalStrut(10));
        sidePanel.add(encodeButton);
        sidePanel.add(Box.createVerticalStrut(10));
        sidePanel.add(clearButton);
        sidePanel.add(Box.createVerticalGlue());

        // Main panel (right) for tabs
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT); // Vertical tabs
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 12));
        tabbedPane.setBackground(new Color(144, 238, 144)); // Light green tabs

        // Frequency Table Tab
        freqTableArea = new JTextArea();
        freqTableArea.setEditable(false);
        freqTableArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane freqScrollPane = new JScrollPane(freqTableArea);
        tabbedPane.addTab("Frequency Table", freqScrollPane);

        // Huffman Tree Tab
        huffmanTreePanel = new HuffmanTreePanel();
        JScrollPane treeScrollPane = new JScrollPane(huffmanTreePanel);
        tabbedPane.addTab("Huffman Tree", treeScrollPane);

        // Code Alphabet Tab
        codeAlphabetArea = new JTextArea();
        codeAlphabetArea.setEditable(false);
        codeAlphabetArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane codeScrollPane = new JScrollPane(codeAlphabetArea);
        tabbedPane.addTab("Code Alphabet", codeScrollPane);

        // Encoded String Tab
        encodedArea = new JTextArea();
        encodedArea.setEditable(false);
        encodedArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane encodedScrollPane = new JScrollPane(encodedArea);
        tabbedPane.addTab("Encoded String", encodedScrollPane);

        // Decoded String Tab
        decodedArea = new JTextArea();
        decodedArea.setEditable(false);
        decodedArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane decodedScrollPane = new JScrollPane(decodedArea);
        tabbedPane.addTab("Decoded String", decodedScrollPane);

        // Calculations Tab
        calculationsArea = new JTextArea();
        calculationsArea.setEditable(false);
        calculationsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane calcScrollPane = new JScrollPane(calculationsArea);
        tabbedPane.addTab("Calculations", calcScrollPane);

        // Add components to frame
        frame.add(sidePanel, BorderLayout.WEST);
        frame.add(tabbedPane, BorderLayout.CENTER);

        // Action listener for the Encode button
        encodeButton.addActionListener(e -> processInput());

        // Action listener for the Clear button
        clearButton.addActionListener(e -> {
            HuffmanCoding.clearHuffmanCodes();
            inputField.setText("");
            freqTableArea.setText("");
            huffmanTreePanel.clear();
            codeAlphabetArea.setText("");
            encodedArea.setText("");
            decodedArea.setText("");
            calculationsArea.setText("");
            inputField.requestFocusInWindow();
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    // Process the input and display results
    private void processInput() {
        String input = inputField.getText().trim();
        freqTableArea.setText("");
        huffmanTreePanel.clear();
        codeAlphabetArea.setText("");
        encodedArea.setText("");
        decodedArea.setText("");
        calculationsArea.setText("");

        if (!HuffmanCoding.isValidInput(input)) {
            freqTableArea.setText("Error: Input must be non-empty and contain only printable ASCII characters (e.g., letters, numbers, symbols).\n");
            return;
        }

        try {
            // Step 1: Calculate frequency
            Map<Character, Integer> freqMap = new HashMap<>();
            for (char c : input.toCharArray()) {
                freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            }

            // Display frequency table
            displayFrequencyTable(freqMap, input.length());

            // Step 2: Build Huffman Tree
            huffmanRoot = HuffmanCoding.buildHuffmanTree(freqMap);
            huffmanTreePanel.setRoot(huffmanRoot, input.length());

            // Step 3: Generate Huffman Codes
            HuffmanCoding.clearHuffmanCodes();
            HuffmanCoding.generateCodes(huffmanRoot, "");

            // Step 4: Display Code Alphabet
            displayCodeAlphabet();

            // Step 5: Encode
            String encoded = HuffmanCoding.encode(input);
            encodedArea.append("Encoded string: " + encoded + "\n");

            // Step 6: Decode
            String decoded = HuffmanCoding.decode(huffmanRoot, encoded);
            decodedArea.append("Decoded string: " + decoded + "\n");

            // Step 7: Verify and show compression
            if (input.equals(decoded)) {
                decodedArea.append("Verification: Decoding successful!\n");
                double compressionRatio = HuffmanCoding.calculateCompressionRatio(input, encoded);
                decodedArea.append(String.format("Compression ratio: %.2f%% (encoded bits: %d, original bits: %d)%n",
                    compressionRatio, encoded.length(), input.length() * 8));

                // Step 8: Display Calculations
                displayCalculations(input, encoded, freqMap);
            } else {
                decodedArea.append("Verification: Decoding failed!\n");
            }
        } catch (Exception e) {
            freqTableArea.setText("Error: " + e.getMessage() + "\n");
        }
    }

    // Display frequency table (sorted by probability in descending order)
    private void displayFrequencyTable(Map<Character, Integer> freqMap, int totalChars) {
        freqTableArea.append("Frequency Table:\n");
        freqTableArea.append(String.format("%-10s %-10s %-10s%n", "char", "quant", "p"));
        freqTableArea.append("-----------------------------\n");

        java.util.List<Map.Entry<Character, Integer>> entries = new ArrayList<>(freqMap.entrySet());
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
            freqTableArea.append(String.format("%-10c %-10d %-10.3f%n", c, quantity, probability));
        }
    }

    // Display Code Alphabet table (sorted by code in ascending order)
    private void displayCodeAlphabet() {
        codeAlphabetArea.append("Code Alphabet:\n");
        codeAlphabetArea.append(String.format("%-10s %-10s%n", "char", "code"));
        codeAlphabetArea.append("---------------------\n");

        Map<Character, String> huffmanCodes = HuffmanCoding.getHuffmanCodes();
        java.util.List<Map.Entry<Character, String>> entries = new ArrayList<>(huffmanCodes.entrySet());
        entries.sort((e1, e2) -> {
            int compare = e1.getValue().compareTo(e2.getValue());
            if (compare == 0) {
                return Character.compare(e1.getKey(), e2.getKey());
            }
            return compare;
        });

        for (Map.Entry<Character, String> entry : entries) {
            codeAlphabetArea.append(String.format("%-10c %-10s%n", entry.getKey(), entry.getValue()));
        }
    }

    // Display Calculations
    private void displayCalculations(String input, String encoded, Map<Character, Integer> freqMap) {
        int totalSymbols = input.length();
        int uniqueSymbols = freqMap.size();

        double averageLength = HuffmanCoding.calculateAverageLength(freqMap, totalSymbols);
        double entropy = HuffmanCoding.calculateEntropy(freqMap, totalSymbols);
        double efficiency = entropy / averageLength;
        double residualEfficiency = entropy - averageLength;
        double redundancy = 1.0 - efficiency;

        int naiveBits = totalSymbols * 8;
        int huffmanBits = encoded.length();
        double compressionRatio = (double) huffmanBits / naiveBits * 100;

        calculationsArea.append("Calculations:\n");
        calculationsArea.append(String.format("The input text has N=%d symbols of which %d are unique. After computing the probability of each and building%n", totalSymbols, uniqueSymbols));
        calculationsArea.append("the tree, we can use it to fill the alphabet table with every encoded symbol. Then, we just have to swap each symbol\n");
        calculationsArea.append("for its binary result, and we get our encoded string. With the obtained table we can compute the average length of\n");
        calculationsArea.append("the code words,\n");
        calculationsArea.append(String.format("• Average Length, L = %.3f bits%n", averageLength));
        calculationsArea.append("Which is very close to the minimum, defined by the Shannon Entropy,\n");
        calculationsArea.append(String.format("• Entropy, H = %.3f bits/symbol.%n", entropy));
        calculationsArea.append("Other conclusions we can obtain are:\n");
        calculationsArea.append(String.format("• Code Efficiency: η = H/L = %.3f%n", efficiency));
        calculationsArea.append(String.format("• Residual Efficiency: τ = H - L = %.3f%n", residualEfficiency));
        calculationsArea.append(String.format("• Code Redundancy: r = 1 - η = %.3f%n", redundancy));
        calculationsArea.append(String.format("If we would have encoded the text naively, it would have weighted %d bits. On the other hand, with Huffman is %d bits.%n", naiveBits, huffmanBits));
        calculationsArea.append("Therefore, we can calculate its compression ratio:\n");
        calculationsArea.append(String.format("• Compression Ratio: %d/%d bits = %.3f%%%n", huffmanBits, naiveBits, compressionRatio));
        calculationsArea.append("Note: Values are truncated to 3 decimals for visualization purposes.\n");
    }

    // Custom panel to draw the Huffman Tree graphically
    private class HuffmanTreePanel extends JPanel {
        private HuffmanNode root;
        private int totalChars;
        private static final int NODE_DIAMETER = 80; // Increased to fit text inside
        private static final int VERTICAL_GAP = 100; // Increased for better spacing
        private static final int HORIZONTAL_GAP = 60;

        public HuffmanTreePanel() {
            this.root = null;
            this.totalChars = 0;
            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(800, 600));
        }

        public void setRoot(HuffmanNode root, int totalChars) {
            this.root = root;
            this.totalChars = totalChars;
            if (root != null) {
                int depth = calculateDepth(root);
                int width = calculateWidth(root);
                setPreferredSize(new Dimension(width * HORIZONTAL_GAP + 200, depth * VERTICAL_GAP + 100));
            }
            revalidate();
            repaint();
        }

        public void clear() {
            this.root = null;
            this.totalChars = 0;
            setPreferredSize(new Dimension(800, 600));
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (root == null) return;

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setFont(new Font("Arial", Font.PLAIN, 10)); // Smaller font to fit inside nodes

            int treeWidth = calculateWidth(root) * HORIZONTAL_GAP;
            int startX = (getWidth() - treeWidth) / 2 + NODE_DIAMETER / 2;
            drawTree(g2d, root, startX, 50, HORIZONTAL_GAP * (calculateWidth(root) / 2));
        }

        private void drawTree(Graphics2D g, HuffmanNode node, int x, int y, int xOffset) {
            if (node == null) return;

            // Calculate probability
            double probability = totalChars > 0 ? (double) node.frequency / totalChars : 0;
            String chars = getCombinedCharacters(node);
            String label1 = chars;
            String label2 = "weight: " + String.format("%.3f", probability);

            // Draw the node as a circle
            g.setColor(new Color(135, 206, 250)); // Light blue (sky blue)
            g.fillOval(x - NODE_DIAMETER / 2, y - NODE_DIAMETER / 2, NODE_DIAMETER, NODE_DIAMETER);
            g.setColor(Color.BLACK);
            g.drawOval(x - NODE_DIAMETER / 2, y - NODE_DIAMETER / 2, NODE_DIAMETER, NODE_DIAMETER);

            // Draw the label inside the circle
            g.setColor(Color.BLACK);
            FontMetrics fm = g.getFontMetrics();
            int textWidth1 = fm.stringWidth(label1);
            int textWidth2 = fm.stringWidth(label2);
            g.drawString(label1, x - textWidth1 / 2, y - 5);
            g.drawString(label2, x - textWidth2 / 2, y + 15);

            // Draw edges and children as curved lines
            int childY = y + VERTICAL_GAP;
            if (node.left != null) {
                int leftX = x - xOffset;
                g.setColor(Color.BLACK);
                QuadCurve2D curve = new QuadCurve2D.Float(
                    x, y + NODE_DIAMETER / 2,
                    x - xOffset / 2, y + VERTICAL_GAP / 2,
                    leftX, childY - NODE_DIAMETER / 2
                );
                g.draw(curve);
                drawTree(g, node.left, leftX, childY, xOffset / 2);
            }
            if (node.right != null) {
                int rightX = x + xOffset;
                g.setColor(Color.BLACK);
                QuadCurve2D curve = new QuadCurve2D.Float(
                    x, y + NODE_DIAMETER / 2,
                    x + xOffset / 2, y + VERTICAL_GAP / 2,
                    rightX, childY - NODE_DIAMETER / 2
                );
                g.draw(curve);
                drawTree(g, node.right, rightX, childY, xOffset / 2);
            }
        }

        // Get combined characters for a node (e.g., "LHOE" for the root)
        private String getCombinedCharacters(HuffmanNode node) {
            if (node == null) return "";
            if (node.left == null && node.right == null && node.data != '\0') {
                return String.valueOf(node.data);
            }
            String leftChars = getCombinedCharacters(node.left);
            String rightChars = getCombinedCharacters(node.right);
            return leftChars + rightChars;
        }

        // Calculate the depth of the tree for vertical spacing
        private int calculateDepth(HuffmanNode node) {
            if (node == null) return 0;
            return 1 + Math.max(calculateDepth(node.left), calculateDepth(node.right));
        }

        // Calculate the width of the tree (number of leaf nodes) for horizontal spacing
        private int calculateWidth(HuffmanNode node) {
            if (node == null) return 0;
            if (node.left == null && node.right == null) return 1;
            return calculateWidth(node.left) + calculateWidth(node.right);
        }
    }
}