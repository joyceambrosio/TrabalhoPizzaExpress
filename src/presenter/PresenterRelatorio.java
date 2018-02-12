/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import colections.Pedidos;
import dao.DaoRelatorio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Pedido;
import model.SalarioComissao;
import view.ViewMenu;

/**
 *
 * @author Natalia
 */
public class PresenterRelatorio {

    private ViewMenu view;
    private double totalCaixa = 0;
    private double salarios = 0;
    private DaoRelatorio dao = new DaoRelatorio();

    public PresenterRelatorio(ViewMenu menu) {

        view = menu;
        configuraTela();
        pesquisar();

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

        return ano + "-" + mes + "-" + dia;
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

        return ano + "-" + mes + "-" + dia;
    }

    public void pesquisar() {
        view.getjButtonGerarRelatorio().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (view.getjFormattedTextFieldDataInicio().getText().equals("  /  /    ") || view.getjFormattedTextFieldDataFim().getText().equals("  /  /    ")) {
                    JOptionPane.showMessageDialog(null, "Informar uma data de início e fim");
                } else {

                    Object colunas[] = {"ID", "Nome", "Salario/Comissão",};
                    DefaultTableModel tabela = new DefaultTableModel(colunas, 0);

                    view.getjTableRelatório().setModel(tabela);
                    if (view.getjComboBoxEmitirRelatorio().getSelectedItem().equals("Fluxo de caixa")) {
                        try {
                            salarios = 0;
                            for (SalarioComissao s : dao.getComComissao(pegarDataInicio(), pegarDataFim())) {
                                salarios = salarios + s.getSalario();
                                tabela.addRow(new Object[]{s.getId(), s.getNome(), s.getSalario()});
                            }

                            for (SalarioComissao s : dao.getSemComissao(pegarDataInicio(), pegarDataFim())) {
                                salarios = salarios + s.getSalario();
                                tabela.addRow(new Object[]{s.getId(), s.getNome(), s.getSalario()});
                            }

                            totalCaixa = dao.getEntradaCaixa(pegarDataInicio(), pegarDataFim());
                            String sal = "" + salarios;
                            String caixa = "" + totalCaixa;
                            view.getjLabelTotalDeVendas().setText(caixa);
                            double lucro = totalCaixa - salarios;
                            String l = "" + lucro;
                            view.getjLabelTotalLucro().setText(l);
                            view.getjLabelSalarios().setText(sal);

                        } catch (SQLException ex) {
                            Logger.getLogger(PresenterRelatorio.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (view.getjComboBoxEmitirRelatorio().getSelectedItem().equals("Folha de pagamento")) {
                        try {
                            salarios = 0;
                            for (SalarioComissao s : dao.getComComissao(pegarDataInicio(), pegarDataFim())) {
                                salarios = salarios + s.getSalario();
                                tabela.addRow(new Object[]{s.getId(), s.getNome(), s.getSalario()});
                            }
                            for (SalarioComissao s : dao.getSemComissao(pegarDataInicio(), pegarDataFim())) {
                                salarios = salarios + s.getSalario();
                                tabela.addRow(new Object[]{s.getId(), s.getNome(), s.getSalario()});
                            }
                            totalCaixa = dao.getEntradaCaixa(pegarDataInicio(), pegarDataFim());
                            String sal = "" + salarios;
                            view.getjLabelTotalLucro().setText("");
                            view.getjLabelTotalDeVendas().setText("");
                            view.getjLabelSalarios().setText(sal);

                        } catch (SQLException ex) {
                            Logger.getLogger(PresenterRelatorio.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
            }
        });
    }

    public void configuraTela() {
        view.getjComboBoxEmitirRelatorio().addItem("Fluxo de caixa");
        view.getjComboBoxEmitirRelatorio().addItem("Folha de pagamento");
    }

    public void fluxoDeCaixa() {
        try {
            for (Pedido p : Pedidos.getInstancia().getPedidos()) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(PresenterRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
