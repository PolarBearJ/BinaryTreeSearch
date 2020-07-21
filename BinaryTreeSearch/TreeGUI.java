import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.*;
import java.io.*;
import javax.swing.border.EmptyBorder;
import java.util.*;

public class TreeGUI extends JFrame  implements KeyListener, ActionListener{


    private JTextField optionTextF, valueTextF;
    private String option, optionV;

    private JPanel contentPane;
    public BinaryTree tree;
    public DrawTree drawer;

    // custom DrawTree
    private DrawTree treeCanvas;
    private JPanel menuCanvas;
    private JTextArea menuText;

    public static final int CANVAS_WIDTH = 500;
    public static final int CANVAS_HEIGHT = 500;
    /**
     * Create the frame.
     */
    Timer timer;

    public TreeGUI() {

        // create the tree and JPanel 
        tree = new BinaryTree();
        treeCanvas = new DrawTree(tree);
        treeCanvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        treeCanvas.setBackground(Color.green);

        // create the JPanel for menu
        menuCanvas = new JPanel();
        menuCanvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        menuCanvas.setLayout(new GridLayout(2,1));


        // menu text
        menuText = new JTextArea(8,5);
        menuText.setLineWrap(true);
        menuText.setEditable(false);
        menuText.setWrapStyleWord(true);
        menuText.setFont(new Font("Courier New", Font.BOLD, 13));
        menuText.setBackground(Color.red);

        // create the input text field                   
        optionTextF = new JTextField(1);
        optionTextF.setFont(new Font("Courier New", Font.BOLD, 18));
        optionTextF.setBackground(Color.green);

        optionTextF.addActionListener(this);
        optionTextF.requestFocus();

        valueTextF = new JTextField(1);
        valueTextF.setFont(new Font("Courier New", Font.BOLD, 18));
        valueTextF.addActionListener(this);

        menuCanvas.add(menuText);
        menuCanvas.add(optionTextF);

        Container cp = getContentPane();

        cp.setLayout(new GridLayout(1,2));

        cp.add(menuCanvas);
        cp.add(treeCanvas);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Binary Tree");
        pack();           // pack al
        setVisible(true);

        timer = new Timer(100, this);
        timer.start();
    }

    public static void main(String[] args){

        TreeGUI treeGUI = new TreeGUI();

        treeGUI.showOptions();
    }

    public void processOptionSelected(){
        if(option.trim().equals("")){
            showOptions();
        }
        else if (option.equals("1"))
        {
            menuText.setText("\nYou have chosen to insert numbers from the keyboard. Enter your numbers seperated by spaces.");

        }
        else if(option.equals("2"))
        {
            menuText.setText("\nYou have chosen to remove a number in the tree. Enter your number.");
        }

        else if(option.equals("3"))
        {
            try{
                menuText.setText("\nThe elements in order are: " + tree.printInOrder()+ "\nPress enter to continue");
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(treeCanvas, "Exception was thrown: " + getFunnyMessage());
                e.printStackTrace(System.out);
            }
        }
        else if(option.equals("4"))
        {
            try{
                menuText.setText("\nThe elements pre order are: " +tree.printPreOrder()+ "\nPress enter to continue");
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(treeCanvas, "Exception was thrown: " + getFunnyMessage());
                e.printStackTrace(System.out);
            }
        }
        else if(option.equals("5"))
        {
            try{
                menuText.setText("\nThe elements post order are: " +  tree.printPostOrder() + "\nPress enter to continue");
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(treeCanvas, "Exception was thrown: " + getFunnyMessage());
                e.printStackTrace(System.out);
            }
        }

        else if(option.equals("6"))
        {
            try{
                menuText.setText("\nThe tree is cleared");
                tree.clear();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(treeCanvas, "Exception was thrown: " + getFunnyMessage());
                e.printStackTrace(System.out);
            }
        }
        else if(option.equals("7"))
        {
            try{
                menuText.setText("\nThe number of nodes are: " + tree.count());
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(treeCanvas, "Exception was thrown: " + getFunnyMessage());
                e.printStackTrace(System.out);
            }
        }

        else if(option.equals("8"))
        {
            try{
                menuText.setText("\nDeleted the minimum element: " + tree.getMin().data+ "\nPress enter to continue");
                tree.removeMin();
            }
            catch (Exception e){
                menuText.setText("\nNothing to delete");
            }

        }
        else if(option.equals("9"))
        {
            try{
                menuText.setText("\nDeleted the maximum element: " + tree.getMax().data+ "\nPress enter to continue");
                tree.removeMax();
            }
            catch (Exception e){
                menuText.setText("\nNothing to delete");
            }
        }
        else if(option.equals("10"))
        {
            menuText.setText("\nYou have chosen to search numbers from the keyboard. Enter your numbers seperated by spaces.");
        }
        else if(option.equals("11"))
        {
            try{
                menuText.setText("\nIs the Tree balanced?: " +  tree.isBalanced()+ "\nPress enter to continue");
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(treeCanvas, "Exception was thrown: " + getFunnyMessage());
                e.printStackTrace(System.out);
            }
        }

        else if(option.equals("12"))
        {
            try{
                menuText.setText("\nThe tree has been rebalanced"+ "\nPress enter to continue");
                tree.rebalanceTree();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(treeCanvas, "Exception was thrown: " + getFunnyMessage());
                e.printStackTrace(System.out);
            }
        }
        else if(option.equals("13"))
        {
            try{
                menuText.setText("\nThe tree has been reflected"+ "\nPress enter to continue");
                tree.mirror();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(treeCanvas, "Exception was thrown: " + getFunnyMessage());
                e.printStackTrace(System.out);
            }
        }

        else {

            optionTextF.setText("Invalid input. Try again");
            menuCanvas.remove(valueTextF);
            menuCanvas.add(optionTextF);
            optionTextF.requestFocus();
            menuCanvas.revalidate();
        }
    }
    public void processOptionValueSelected(){

        if (option.equals("1"))
        {
            try{
                addWords(optionV);
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(treeCanvas, "Exception was thrown: " + getFunnyMessage());
                e.printStackTrace(System.out);
            }
        }
        else if(option.equals("2"))
        {
            try{
                tree.remove(Integer.parseInt(optionV));
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(treeCanvas, "Exception was thrown: " + getFunnyMessage());
                e.printStackTrace(System.out);
            }
        }
        else if(option.equals("10"))
        {
            menuText.setText(search(optionV));
        }
        else{
            menuCanvas.remove(valueTextF);
            menuCanvas.add(optionTextF);
            optionTextF.requestFocus();
            menuCanvas.revalidate();
        }


    }
    public void addWords(String words) throws Exception{
        Scanner dataScan = new Scanner(words);
        while (dataScan.hasNextInt()){
            tree.insert(dataScan.nextInt());
        }
    }
    // parse a line of ints and see if they exist in the tree
    public String search(String words){
        Scanner dataScan = new Scanner(words);

        int temp;
        String s ="";
        while (dataScan.hasNextInt()){
            temp = dataScan.nextInt();
            if(tree.contains(temp)){
                s = s + (temp + " was found \n");
            }
            else{
                s = s + (temp + " was not found \n");
            }
        }
        dataScan.close();
        return s;
    }


    public  void showOptions(){
        menuText.setText("\nYour Options Are:\n" +
                "[1] Insert some numbers \n" +
                "[2] Remove a number from tree\n" +
                "[3] Display all values inOrder\n" +
                "[4] Display all values preOrder\n" +
                "[5] Display all values postOrder\n" +
                "[6] Clear the tree\n" +
                "[7] Count number of elements in tree\n" +
                "[8] Delete the mininimum value\n" +
                "[9] Delete the maximum value\n" +
                "[10] Search for elements \n" +
                "[11] Is tree balanced? \n" +
                "[12] Rebalance tree \n"+
                "[13] Mirror the tree \n");

    }
    public void actionPerformed(ActionEvent ev){
        if (ev.getSource() == optionTextF)
        {
            option = optionTextF.getText();
            option = option.trim();
            optionTextF.setText("");
            if (option.equals("Invalid input. Try again")){
                // do not do anything
            }

            else if (option.length()>0){
                menuCanvas.remove(optionTextF);
                menuCanvas.add(valueTextF);
                valueTextF.requestFocus();
                menuCanvas.revalidate();
                processOptionSelected();
            }
            if (option.equals("")){
                showOptions();
                menuCanvas.remove(valueTextF);
                menuCanvas.add(optionTextF);
                optionTextF.requestFocus();
                menuCanvas.revalidate();
            }
        }
        if (ev.getSource() == valueTextF)
        {

            optionV = valueTextF.getText();
            optionV = optionV.trim();
            valueTextF.setText("");
            if (optionV.equals("")){
                showOptions();

            }
            else{
                processOptionValueSelected();
                //showOptions();
            }

            menuCanvas.remove(valueTextF);
            menuCanvas.add(optionTextF);
            optionTextF.requestFocus();
            menuCanvas.revalidate();
        }


        else if (ev.getSource() == timer){

            menuCanvas.repaint();
            treeCanvas.repaint();
        }
    }





    public void keyPressed(KeyEvent evt) {}

    public void keyReleased(KeyEvent evt) {}

    public void keyTyped(KeyEvent evt) {}


    public String getFunnyMessage(){
        String[] s = {"You messed up son!", "I worry about the future", "???", "N00B", "Task failed successfully", "I do not know what's wrong", "Catastrophic Failure"};


        int num = (int)(Math.random()*s.length);

        return s[num];

    }

    class DrawTree extends JPanel{

        public BinaryTree tree;

        public DrawTree(BinaryTree tree){
            this.tree = tree;
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setFont(new Font("Tahoma", Font.BOLD, 15));
            try{
                drawThisTree(g, 0, getWidth(), 0, getHeight() / tree.height(tree.root), tree.root, 0 , 0);
            }
            catch(Exception e){}
        }

        public void drawThisTree(Graphics g, int StartWidth, int EndWidth, int StartHeight, int Level, Node node, int oldX, int oldY) {
            String data = String.valueOf(node.data);
            g.setFont(new Font("Tahoma", Font.BOLD, 15));
            FontMetrics fm = g.getFontMetrics();
            int dataWidth = fm.stringWidth(data);

            g.drawString(node.data + " ", (StartWidth + EndWidth) / 2 - dataWidth / 2, StartHeight + Level / 2);

            if (oldX !=0 && oldY !=0){
                g.drawLine( (StartWidth + EndWidth) / 2 - dataWidth / 2, StartHeight + Level / 2, oldX, oldY );
            }
            if (node.left != null)
                drawThisTree(g, StartWidth, (StartWidth + EndWidth) / 2, StartHeight + Level, Level, node.left, (StartWidth + EndWidth) / 2 - dataWidth / 2, StartHeight + Level / 2 );

            if (node.right != null)
                drawThisTree(g, (StartWidth + EndWidth) / 2, EndWidth, StartHeight + Level, Level, node.right, (StartWidth + EndWidth) / 2 - dataWidth / 2, StartHeight + Level / 2);
        }


    }
}