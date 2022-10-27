/**
 * 
 */
package com.sedodream.boggle.sih.exceptions;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class BoardSizeException extends Exception {
    public BoardSizeException(){
        super();
    }
    public BoardSizeException(String message){
        super(message);
    }
}
