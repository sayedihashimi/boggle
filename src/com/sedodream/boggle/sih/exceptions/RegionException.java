/**
 * 
 */
package com.sedodream.boggle.sih.exceptions;

/**
 * @author Sayed Ibrahim Hashimi (sayed.hashimi@gmail.com)
 */
public class RegionException extends RuntimeException {

    /**
     * 
     */
    public RegionException() {
    }

    /**
     * @param arg0
     */
    public RegionException(String arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     */
    public RegionException(Throwable arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     * @param arg1
     */
    public RegionException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

}
