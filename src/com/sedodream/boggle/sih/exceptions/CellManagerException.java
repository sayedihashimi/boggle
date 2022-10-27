/**
 * 
 */
package com.sedodream.boggle.sih.exceptions;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class CellManagerException extends RuntimeException {

    /**
     * 
     */
    public CellManagerException() {
    }

    /**
     * @param arg0
     */
    public CellManagerException(String arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     */
    public CellManagerException(Throwable arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     * @param arg1
     */
    public CellManagerException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

}
