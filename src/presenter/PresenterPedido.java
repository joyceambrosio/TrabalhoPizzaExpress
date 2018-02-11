/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import colections.Pedidos;
import com.sun.javafx.geom.PickRay;
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

        try {
            populaMenuPedidos();
        } catch (SQLException ex) {
            Logger.getLogger(PresenterPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                if (linha >= 0) {
                    int idP = (int) menu.getjTablePedido().getValueAt(linha, 0);
                    try {
                        Pedido p = Pedidos.getInstancia().getPedidoById(idP);
                        
                        if (p.nextStatusPedido()) {
                            Pedidos.getInstancia().updatePedido(p);
                            populaMenuPedidos();
                            JOptionPane.showMessageDialog(menu, "Etapa modificada");
                        } else {
                            JOptionPane.showMessageDialog(menu, "Não é possível alterar um pedido nesse status");
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(PresenterPedido.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }
        });
    }

    public void anteriorEtapa() {
        menu.getjButtonEtapaAnterior().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linha = menu.getjTablePedido().getSelectedRow();

                if (linha == -1) {
                    JOptionPane.showMessageDialog(menu, "Selecione um pedido para alterar seu status");
                }
                if (linha >= 0) {
                    int idP = (int) menu.getjTablePedido().getValueAt(linha, 0);
                    try {
                        Pedido p = Pedidos.getInstancia().getPedidoById(idP);
                        if (p.lastStatusPedido()) {
                            Pedidos.getInstancia().updatePedido(p);
                            populaMenuPedidos();
                            JOptionPane.showMessageDialog(menu, "Etapa modificada");
                        } else {
                            JOptionPane.showMessageDialog(menu, "Não é possível alterar um pedido nesse status");
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(PresenterPedido.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }
        });
    }

    public void escutaCheckAberto() {
        menu.getjCheckBoxAbertos().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    populaMenuPedidos();
                } catch (SQLException ex) {
                    Logger.getLogger(PresenterPedido.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void escutaCheckPreparo() {
        menu.getjCheckBoxProducao().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    populaMenuPedidos();
                } catch (SQLException ex) {
                    Logger.getLogger(PresenterPedido.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void escutaCheckEntrega() {
        menu.getjCheckBoxEntrega().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    populaMenuPedidos();
                } catch (SQLException ex) {
                    Logger.getLogger(PresenterPedido.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void escutaCheckConcluido() {
        menu.getjCheckBoxConcluidos().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    populaMenuPedidos();
                } catch (SQLException ex) {
                    Logger.getLogger(PresenterPedido.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void populaMenuPedidos() throws SQLException {

        Object colunas[] = {"ID", "Nome", "Endereço", "Etapa", "Entregador", "Total"};
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
            int id = p.getId();
            String nome = p.getCliente().getNome();
            String end = p.getCliente().getEndereco().getEnderecoCompleto();
            String status = p.getStatusPedido();
            String entregador;

            if (p.getEntregador() == null) {
                entregador = "Casa";
            } else {
                entregador = p.getEntregador().getNome();
            }
            double total = p.getTotalPedido();
            tabela.addRow(new Object[]{id, nome, end, status, entregador, total});
        }
    }
}
