/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import colections.Pedidos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Pedido;
import view.ViewMenu;

/**
 *
 * @author Natalia
 */
public class PresenterPedido {

    private ViewMenu menu;

    public PresenterPedido(ViewMenu menu) {
        this.menu = menu;

        populaMenuPedidos();
        proximaEtapa();
        anteriorEtapa();
        novoPedido();

        escutaCheckAberto();
        escutaCheckPreparo();
        escutaCheckEntrega();
        escutaCheckConcluido();
        detalhesPedido();
        configuraMenu();
    }

    public void novoPedido() {
        menu.getjButtonNovoPedido().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PresenterCadastrarPedido presenterPedido = PresenterCadastrarPedido.getInstancia();
                } catch (SQLException ex) {
                    Logger.getLogger(PresenterMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }
    
      public void configuraMenu() {
        menu.getjCheckBoxAbertos().setSelected(true);
    }


    public void detalhesPedido() {
        menu.getjButtonDetalhesPedido().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int linha = menu.getjTablePedido().getSelectedRow();

                if (linha == -1) {
                    JOptionPane.showMessageDialog(menu, "Selecione um pedido para visualizar os detalhes");
                }

            }
        });
    }

    public void proximaEtapa() {
        menu.getjButtonEtapaProx().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linha = menu.getjTablePedido().getSelectedRow();

                if (linha == -1) {
                    JOptionPane.showMessageDialog(menu, "Selecione um pedido para alterar seu status");
                }
            }
        });
    }

    public void anteriorEtapa() {
        menu.getjButtonEtapaAnterior().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                int linha = view.getjTablePedido().getSelectedRow();
//
//                if (linha == -1) {
//                    JOptionPane.showMessageDialog(view, "Selecione um pedido para alterar seu status");
//                }
//                if (linha >= 0) {
//                    String nome = view.getjTablePedido().getValueAt(linha, 0).toString();
//                    Cliente c = Clientes.getInstancia().getClienteByNome(nome);
//                    String situacao = view.getjTablePedido().getValueAt(linha, 2).toString();
//                    for (Pedido p : Pedidos.getInstancia().getLista()) {
//                        if (p.getCliente().equals(c) && p.getStatusPedido().equals(situacao)) {
//
//                            if (p.lastStatusPedido()) {
//                                populaMenuPedidos();
//                                JOptionPane.showMessageDialog(view, "Etapa modificada");
//                            } else {
//                                JOptionPane.showMessageDialog(view, "Não é possível alterar um pedido nesse status");
//                            }
//
//                        }
//                    }
//                }

            }
        });
    }

    public void escutaCheckAberto() {
        menu.getjCheckBoxAbertos().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populaMenuPedidos();
            }
        });
    }

    public void escutaCheckPreparo() {
        menu.getjCheckBoxProducao().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populaMenuPedidos();
            }
        });
    }

    public void escutaCheckEntrega() {
        menu.getjCheckBoxEntrega().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populaMenuPedidos();
            }
        });
    }

    public void escutaCheckConcluido() {
        menu.getjCheckBoxConcluidos().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populaMenuPedidos();
            }
        });
    }

    public void populaMenuPedidos() {

        Object colunas[] = {"Nome", "Endereço", "Etapa", "Entregador", "Total"};
        DefaultTableModel tabela = new DefaultTableModel(colunas, 0);

        menu.getjTablePedido().setModel(tabela);

        boolean abertos = menu.getjCheckBoxAbertos().isSelected();
        boolean preparo = menu.getjCheckBoxProducao().isSelected();
        boolean entrega = menu.getjCheckBoxEntrega().isSelected();
        boolean concluido = menu.getjCheckBoxConcluidos().isSelected();
        ArrayList<Pedido> pfiltro = new ArrayList<>();

        //não sabia como fazer melhor. sorry
        for (Pedido p : Pedidos.getInstancia().getPedidos()) {
            if (abertos) {
                if (p.getStatusPedido().equals("Aberto")) {
                    pfiltro.add(p);
                }
            }
            if (preparo) {
                if (p.getStatusPedido().equals("Em produção")) {
                    pfiltro.add(p);
                }
            }

            if (entrega) {
                if (p.getStatusPedido().equals("Em entrega")) {
                    pfiltro.add(p);
                }
            }
            if (concluido) {
                if (p.getStatusPedido().equals("Concluído")) {
                    pfiltro.add(p);
                }
            }
        }
        for (Pedido p : pfiltro) {

            String nome = p.getCliente().getNome();
            String end = p.getCliente().getEndereco().getEnderecoCompleto();
            String status = p.getStatusPedido();
            String entregador;

            if (p.getEntregador() == null) {
                entregador = "Casa";
            } else {
                entregador = p.getEntregador().getNome();

            }
            double total = p.getNumTotalPedido();
            tabela.addRow(new Object[]{nome, end, status, entregador, total});
        }
    }
}
