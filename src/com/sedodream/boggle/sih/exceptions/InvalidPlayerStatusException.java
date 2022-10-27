/**
 * 
 */
package com.sedodream.boggle.sih.exceptions;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class InvalidPlayerStatusException extends RuntimeException {
    public InvalidPlayerStatusException(){
        super();
    }
    public InvalidPlayerStatusException(String message){
        super(message);
    }
}
