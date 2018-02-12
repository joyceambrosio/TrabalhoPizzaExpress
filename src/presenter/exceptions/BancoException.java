/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter.exceptions;

import java.sql.SQLException;

/**
 *
 * @author Joyce
 */
public class BancoException extends SQLException{

    public BancoException(String reason, String SQLState, int vendorCode) {
        super(reason, SQLState, vendorCode);
    }

    public BancoException(String reason, String SQLState) {
        super(reason, SQLState);
    }

    public BancoException(String reason) {
        super(reason);
    }

    public BancoException() {
    }
    
}
