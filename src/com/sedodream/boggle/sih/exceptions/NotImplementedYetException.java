/**
 * 
 */
package com.sedodream.boggle.sih.exceptions;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class NotImplementedYetException extends RuntimeException {

    /**
     * 
     */
    public NotImplementedYetException() {
    }

    /**
     * @param message
     */
    public NotImplementedYetException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public NotImplementedYetException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public NotImplementedYetException(String message, Throwable cause) {
        super(message, cause);
    }

}
