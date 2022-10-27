/**
 * 
 */
package com.sedodream.boggle.sih.exceptions;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class BoardDataException extends RuntimeException {
    public BoardDataException(){
        super();
    }
    public BoardDataException(String message){
        super(message);
    }
}
