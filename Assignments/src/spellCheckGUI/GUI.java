package spellCheckGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Casey on 7/21/2014.
 */
public class GUI extends JPanel implements ActionListener {

    private JLabel title;
    private JPanel forDictionary;
    private JPanel forTextFile;
    private JPanel forRadioButtons;
    private JPanel forWriteFile;
    private JTextArea output;
    private JTextField dictionary;
    private JTextField textFile;
    private JTextField writeFile;
    private JRadioButton printScreen;
    private JRadioButton printFile;
    private JButton runCheck;
    public String[] argsToPass = new String[3];

    public GUI() {
        runCheck.addActionListener(this);
        makeObjects();
        theLayout();
    }

    private void makeObjects() {
        // title description
        title = new JLabel("SpellCheck Program");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

        //dictionary panel
        forDictionary = new JPanel();
        forDictionary.add(new JLabel("Add Dictionary File: "));
        dictionary = new JTextField("dictionary.txt", 16);
        forDictionary.add(dictionary);
//        forDictionary.add(new JFileChooser(FileSystemView.getFileSystemView()));

        // text file panel
        forTextFile = new JPanel();
        forTextFile.add(new JLabel("Add Text File to Spellcheck: "));
        textFile = new JTextField("text_file.txt", 16);
        forTextFile.add(textFile);
//        forTextFile.add(new JFileChooser(FileSystemView.getFileSystemView()));

        // option panel
        forRadioButtons = new JPanel();
        ButtonGroup options = new ButtonGroup();
        printFile = new JRadioButton("Print to File", false);
        printScreen = new JRadioButton("Print to Screen", true);
        options.add(printScreen);
        options.add(printFile);
        forRadioButtons.add(printFile);
        forRadioButtons.add(printScreen);

        // write to file panel
        forWriteFile = new JPanel();
        forWriteFile.add(new JLabel("File Name to Print to: "));
        writeFile = new JTextField("misspelled.txt", 16);
        forWriteFile.add(writeFile);

        // create text area to print
        output = new JTextArea(20, 30);

        // instantiate Jbutton
        runCheck = new JButton("Run SpellCheck");
        runCheck.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
    }

    private void theLayout() {
        setLayout(new GridBagLayout());
        add(title);
        add(forDictionary);
        add(forTextFile);
        add(forRadioButtons);
        add(forWriteFile);
        add(output);
        add(runCheck);
    }

    public void actionPerformed(ActionEvent event) {
        argsToPass[0] = dictionary.getText();
        argsToPass[1] = textFile.getText();
        if (printScreen.isSelected())
            argsToPass[2] = "-p";
        else
            argsToPass[2] = "-f";
    }

}
