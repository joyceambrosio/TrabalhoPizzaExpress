/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.regex.Pattern;
import view.ViewMenu;

/**
 *
 * @author Natalia
 */
public class PresenterRelatorio {

    private ViewMenu view;

    public PresenterRelatorio(ViewMenu menu) {
        view = menu;
    }

    public String pegarDataInicio() {
        String dataInicio = view.getjFormattedTextFieldDataInicio().getText();
        Scanner scan = new Scanner(dataInicio);
        String dia = "", mes = "", ano = "";

        while (scan.hasNext()) {
            dia = scan.useDelimiter("/").next();
            mes = scan.next();
            ano = scan.next();
        }

        return ano + "-" + dia + "-" + mes;
    }

    public String pegarDataFim() {
        String dataInicio = view.getjFormattedTextFieldDataFim().getText();
        Scanner scan = new Scanner(dataInicio);
        String dia = "", mes = "", ano = "";

        while (scan.hasNext()) {
            dia = scan.useDelimiter("/").next();
            mes = scan.next();
            ano = scan.next();
        }

        return ano + "-" + dia + "-" + mes;
    }

}
