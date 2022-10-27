/**
 * 
 */
package com.sedodream.boggle.sih.exceptions;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class InvalidBoardRequestException extends RuntimeException {

    /**
     * 
     */
    public InvalidBoardRequestException() {
    }

    /**
     * @param arg0
     */
    public InvalidBoardRequestException(String arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     */
    public InvalidBoardRequestException(Throwable arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     * @param arg1
     */
    public InvalidBoardRequestException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

}
