/**
 * 
 */
package com.sedodream.swtTest;

import org.eclipse.swt.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class SWTHello {
    public static void main(String[]args){
        Display display = new Display();
        Shell shell = new Shell(display);
        
        shell.setLayout(new FillLayout(SWT.VERTICAL));
        Label label = new Label(shell,SWT.NONE);
        label.setText("Hello son");
        Button button = new Button(shell,SWT.NONE);

        button.setText("Do it");
        
        shell.pack();
        label.pack();
        button.pack();
        shell.open();
        
        while(!shell.isDisposed()){
            if(!display.readAndDispatch()){
                display.sleep();
            }
        }
        display.dispose();
        label.dispose();
    }
}
