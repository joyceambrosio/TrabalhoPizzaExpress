/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter.exceptions;

/**
 *
 * @author joyce
 */
public class CustomException extends Exception {

    public CustomException() {
    }

    public CustomException(String msg) {
        super(msg);
    }
}
