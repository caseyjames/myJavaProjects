package myGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Casey on 7/21/2014.
 */
public class GUI extends JPanel implements ActionListener {

    private JLabel title;
    private JPanel titlePanel;
    private JPanel forDictionary;
    private JPanel forTextFile;
    private JPanel forRadioButtons;
    private JPanel forWriteFile;
    public static JTextArea output;
    private JTextField dictionary;
    private JTextField textFile;
    private JTextField writeFile;
    private JRadioButton printScreen;
    private JRadioButton printFile;
    private JButton runCheck = new JButton("Run SpellCheck");
    private String[] argsToPass = new String[3];

    public GUI() {
        makeObjects();
        theLayout();
        runCheck.addActionListener(this);
    }

    private void makeObjects() {
        // title description
        titlePanel = new JPanel();
        title = new JLabel("SpellCheck Program");
        title.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 36));
        titlePanel.add(title);

        //dictionary panel
        forDictionary = new JPanel();
        JLabel dictionaryLabel = new JLabel("Dictionary File Name: ");
        dictionaryLabel.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
        forDictionary.add(dictionaryLabel);
        dictionary = new JTextField("dictionary.txt", 26);
        dictionary.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
        forDictionary.add(dictionary);
//        forDictionary.add(new JFileChooser(FileSystemView.getFileSystemView()));

        // text file panel
        forTextFile = new JPanel();
        JLabel textFileLabel = new JLabel("Text File to Spellcheck: ");
        textFileLabel.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
        forTextFile.add(textFileLabel);
        textFile = new JTextField("hello_world.txt", 26);
        textFile.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
        forTextFile.add(textFile);
//        forTextFile.add(new JFileChooser(FileSystemView.getFileSystemView()));

        // option panel
        forRadioButtons = new JPanel();
        ButtonGroup options = new ButtonGroup();
        printFile = new JRadioButton("Print to File    ", false);
        printScreen = new JRadioButton("Print to Screen", true);
        printFile.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
        printScreen.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
        options.add(printScreen);
        options.add(printFile);
        forRadioButtons.add(printFile);
        forRadioButtons.add(printScreen);

        // write to file panel
        forWriteFile = new JPanel();
        JLabel writeFileLabel = new JLabel("File to Write to: ");
        writeFileLabel.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
        forWriteFile.add(writeFileLabel);
        writeFile = new JTextField("misspelled.txt", 26);
        writeFile.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
        forWriteFile.add(writeFile);

        // create text area to print
        output = new JTextArea(26, 40);
        output.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
        output.setText("\t");

        // instantiate Jbutton
        runCheck.setFont(new Font(Font.MONOSPACED, Font.BOLD, 26));
    }

    private void theLayout() {
        setLayout(new GridLayout(7, 1));
        add(titlePanel);
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

//        output = new JTextArea(26, 40);
//        output.setText("\t");
        SpellChecker.main(argsToPass);
    }

}
