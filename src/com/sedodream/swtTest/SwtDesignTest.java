/**
 * 
 */
package com.sedodream.swtTest;

import java.io.*;
import java.util.Date;
import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class SwtDesignTest {

    private Button    buttonCreateFile;
    private Spinner   spinnerNumChars;
    private Text      textFilename;
    protected Shell   shell;
    protected Spinner spinnerNumWords;
    private Random rand;
    
    // -------------------------------------------------------------------------
    // -------------------------------------------------------------------------

    private void CreateFile() {
        try {

            if(rand==null){
                rand = new Random();
            }
            
            String filename = textFilename.getText();

            if ( filename == null || filename.trim().length() <= 0 ) {
                MessageBox mb = new MessageBox(this.shell);
                mb.setMessage("No file to create");
                mb.setText("Error");
                mb.open();
                return;
            }

            File file = new File(filename);
            if ( file.isDirectory() ) {
                MessageBox mb = new MessageBox(this.shell);
                mb.setMessage("You picked a directory");
                mb.setText("Error");
                mb.open();
                return;
            }
            if ( file.exists() ) {
                file.delete();
            }

            int numWords = spinnerNumWords.getSelection();
            // spinnerNumWords.getDigits();
            int numChar = spinnerNumChars.getSelection();
            int debug = 33;
            
            BufferedWriter writer=null;
            try{
                writer = new BufferedWriter(new FileWriter(file));
                for(int i =0;i<numWords;i++){
                    writer.append(GetRandomWord(numChar));
                    writer.newLine();
                }
            }
            finally{
                if(writer != null){
                    writer.flush();
                    writer.close();
                    writer=null;
                }
            }
            //BufferedReader d = new BufferedReader(new FileReader(wordFile));
            
            MessageBox mb = new MessageBox(shell);
            mb.setMessage(String.format("Done creating file saved to\n[%s]", file.getAbsolutePath()));
            mb.setText("Done");
            mb.open();
        }
        catch (Exception ex) {
            MessageBox mb = new MessageBox(shell);
            mb.setMessage("Error: " + ex.getMessage());
            mb.setText("Error");
            mb.open();
        }
    }
    private String GetRandomWord(int length){
        //rand = new Random();
        //rand.setSeed(new Date().getTime());
        //rand.nextInt(length+1);
        StringBuffer buffer = new StringBuffer(length);
        
        int realLength = rand.nextInt(length+1);
        int[]values = new int[realLength];
        for(int i =0;i<realLength;i++){
            int val = rand.nextInt( (90-65 )+1) + 65;
            values[i]=val;
            char c = (char)val;
            buffer.append(c);
        }
        return buffer.toString();
    }
    private void HandleFilenameChanged() {

    }

    private void HandleBrowseForFile(Shell shell) {
        FileDialog saveDialog = new FileDialog(shell, SWT.SAVE);
        String[] extensions = { "*.txt", "*.*" };
        saveDialog.setFilterExtensions(extensions);
        String selectedFile = saveDialog.open();

        if ( selectedFile == null || selectedFile.trim().length() <= 0 ) {
            return;
        }
        textFilename.setText(selectedFile);
        buttonCreateFile.setEnabled(true);
        // try {
        // CreateFile(selectedFile);
        // }
        // catch (Exception ex) {
        // MessageBox mb = new MessageBox(shell);
        // mb.setMessage("Error: " + ex.getMessage());
        // mb.setText("Error");
        // mb.open();
        // }

    }

    // -------------------------------------------------------------------------
    // -------------------------------------------------------------------------

    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String[] args) {
        try {
            SwtDesignTest window = new SwtDesignTest();
            window.open();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Open the window
     */
    public void open() {
        final Display display = Display.getDefault();
        createContents();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if ( !display.readAndDispatch() )
                display.sleep();
        }
    }

    /**
     * Create contents of the window
     */
    protected void createContents() {
        shell = new Shell();
        shell.setSize(561, 345);
        shell.setText("SWT Application");

        textFilename = new Text(shell, SWT.BORDER);
        textFilename.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent arg0) {
                HandleFilenameChanged();
            }
        });
        textFilename.setBounds(96, 53, 369, 25);

        buttonCreateFile = new Button(shell, SWT.NONE);
        buttonCreateFile.addMouseListener(new MouseAdapter() {
            public void mouseDown(MouseEvent arg0) {
                CreateFile();
            }
        });
        buttonCreateFile.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent arg0) {
            }
        });
        buttonCreateFile.setText("Create File");
        buttonCreateFile.setBounds(0, 96, 75, 23);
        buttonCreateFile.setEnabled(false);

        final Label labelNumRecords = new Label(shell, SWT.NONE);
        labelNumRecords.setText("Number words");
        labelNumRecords.setBounds(0, 10, 75, 13);

        spinnerNumWords = new Spinner(shell, SWT.BORDER);
        spinnerNumWords.setSelection(5);
        spinnerNumWords.setMinimum(1);
        spinnerNumWords.setMaximum(1000000);
        spinnerNumWords.setBounds(95, 10, 138, 17);

        final Label labelNumCharacters = new Label(shell, SWT.NONE);
        labelNumCharacters.setText("Num Characters");
        labelNumCharacters.setBounds(0, 29, 89, 13);

        spinnerNumChars = new Spinner(shell, SWT.BORDER);
        spinnerNumChars.setSelection(40);
        spinnerNumChars.setMinimum(1);
        spinnerNumChars.setMaximum(1000000);
        spinnerNumChars.setBounds(96, 30, 138, 17);

        final Label fileNameLabel = new Label(shell, SWT.NONE);
        fileNameLabel.setText("File name");
        fileNameLabel.setBounds(0, 59, 75, 13);

        final Button buttonBrowse = new Button(shell, SWT.NONE);
        buttonBrowse.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent arg0) {
                HandleBrowseForFile(shell);
            }
        });
        buttonBrowse.setText("...");
        buttonBrowse.setBounds(471, 51, 32, 23);
        //
    }

}
