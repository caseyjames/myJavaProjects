package myGUI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Driver class for the spell checking utility.
 * <p/>
 * This class contains a main method which provides a command-line interface specific to the spell checking utility. The
 * interface must not run the spell checker unless the user input parameters are valid.
 *
 * @author Paymon Saebi
 */
public class SpellChecker {
    public static void main(String[] args) {
        File dictionary = null;
        File document = null;
        String option = "";

        //DO: Check parameter size according to the handout
        if (args.length < 2 || args.length > 3) {
            GUI.output.append("Incorrect number of arguments!\n");
//            System.out.println("Incorrect number of arguments!");
            return;
        }

        //DO: Instantiate the dictionary File object using args[0]
        dictionary = new File(args[0]);

        //DO: Check to see if this dictionary file is a normal file
        //Use the File class isFile() method
        if (! dictionary.isFile()) {
            GUI.output.append("Unable to use the dictionary file!\n");
//            System.out.println("Unable to use the dictionary file!");
            return;
        }

        //DO: Instantiate the document File object using args[1]
        document = new File(args[1]);

        //DO: Check to see if this document file is a normal file
        //Use the File class isFile() method
        if (! document.isFile()) {
            GUI.output.append("Unable to use the document file!\n");
//            System.out.println("Unable to use the document file!");
            return;
        }

        // If a third parameter was passed for the options, check its validity
        if (args.length == 3)
            if (args[2].equalsIgnoreCase("-p") || args[2].equalsIgnoreCase("-f"))
                option = args[2];
            else {
                GUI.output.append("Invalid printing or filing option argument!\n");
//                System.out.println("Invalid printing or filing option argument!");
                return;
            }

        // Passing the dictionary file, document file, and the option
        run_spell_check(dictionary, document, option);
    }

    public static void run_spell_check(File dic, File doc, String option) {
        // Creating a new SpellCheckerUtil object with the dictionary file
        SpellCheckUtil mySC = new SpellCheckUtil(dic);

        // Creating a list of misspelled words after checking spellcheking the document
        List<String> misspelledWords = mySC.spellCheck(doc);

        if (misspelledWords.size() == 0){
            GUI.output.setText("There are no misspelled words in file " + doc + ".\n");
        }
//            System.out.println("\nThere are no misspelled words in file " + doc + ".\n");
        else {
            GUI.output.setText("There are " + misspelledWords.size() + " misspelled words in file " + doc + ".\n");
//            System.out.println("\nThere are " + misspelledWords.size() + " misspelled words in file " + doc + ".");

            if (option.equals("-p")) {
                //DO: Print every misspelled word on a new line
                for (String word : misspelledWords)
                        GUI.output.append(word+"\n");
//                    System.out.println(word);

            } else if (option.equals("-f"))
                try {
                    FileWriter writer = new FileWriter("misspelled.txt");

                    // write each misspelled word to the output
                    for (String word : misspelledWords)
                        writer.write(word + "\n");

                    // close the file writer
                    writer.close();

                    GUI.output.append("Please see misspelled.txt for a list of the words.\n");
//                    System.out.println("Please see misspelled.txt for a list of the words.");
                }
                catch (IOException e) {
                    GUI.output.append("Unable to create a file for the misspelled words!\n");
//                    System.out.println("Unable to create a file for the misspelled words!");
                    return;
                }

            GUI.output.append("\nHave a nice day!\n");
//            System.out.println("\nHave a nice day!\n");
        }
    }
}
