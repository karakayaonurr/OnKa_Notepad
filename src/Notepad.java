import java.awt.*;
        import java.awt.event.*;
        import javax.swing.*;
        import java.io.*;

/**
 * Created by TCOKARAKAYA on 16.03.2023.
 */

public class Notepad extends JFrame implements ActionListener {

    private JTextArea textArea = new JTextArea("", 0,0);
    private JScrollPane scrollPane = new JScrollPane(textArea);

    private JMenuBar menuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu("File");
    private JMenuItem newMenuItem = new JMenuItem("New");
    private JMenuItem openMenuItem = new JMenuItem("Open");
    private JMenuItem saveMenuItem = new JMenuItem("Save");
    private JMenuItem exitMenuItem = new JMenuItem("Exit");

    public Notepad() {
        this.setSize(500, 300);
        this.setTitle("Notepad");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        textArea.setFont(new Font("Century Gothic", Font.BOLD, 12));
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        menuBar.add(fileMenu);
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        setJMenuBar(menuBar);
        newMenuItem.addActionListener(this);
        openMenuItem.addActionListener(this);
        saveMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newMenuItem) {
            textArea.setText("");
        } else if (e.getSource() == openMenuItem) {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    textArea.read(reader, null);
                    reader.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error opening file: " + ex.getMessage());
                }
            }
        } else if (e.getSource() == saveMenuItem) {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    textArea.write(writer);
                    writer.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
                }
            }
        } else if (e.getSource() == exitMenuItem) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Notepad notepad = new Notepad();
        notepad.setVisible(true);
    }
}
