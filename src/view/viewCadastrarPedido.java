/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author joyce
 */
public class viewCadastrarPedido extends javax.swing.JFrame {

    /**
     * Creates new form CadastrarProduto
     */
    public viewCadastrarPedido() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBoxClientes = new javax.swing.JComboBox<>();
        jComboBoxEntregadores = new javax.swing.JComboBox<>();
        jComboBoxProdutos = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProdutos = new javax.swing.JTable();
        jButtonAdicionar = new javax.swing.JButton();
        jButtonRemoverProduto = new javax.swing.JButton();
        jButtonCadastrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelTotalPedido = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabelDesconto = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelSubtotal = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButtonFechar = new javax.swing.JButton();
        jSpinnerQuantidade = new javax.swing.JSpinner();
        jLabelAvisosCliente = new javax.swing.JLabel();
        jLabelAvisosProduto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jComboBoxClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxEntregadores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxProdutos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTableProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto", "Quantidade", "Valor (R$)", "Total (R$)"
            }
        ));
        jScrollPane1.setViewportView(jTableProdutos);

        jButtonAdicionar.setBackground(new java.awt.Color(51, 51, 51));
        jButtonAdicionar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAdicionar.setText("Adicionar");
        jButtonAdicionar.setBorder(null);
        jButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarActionPerformed(evt);
            }
        });

        jButtonRemoverProduto.setBackground(new java.awt.Color(51, 51, 51));
        jButtonRemoverProduto.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRemoverProduto.setText("Remover Produto");
        jButtonRemoverProduto.setBorder(null);

        jButtonCadastrar.setBackground(new java.awt.Color(51, 51, 51));
        jButtonCadastrar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCadastrar.setText("Cadastrar");
        jButtonCadastrar.setBorder(null);
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });

        jLabel1.setText("Cliente");

        jLabel2.setText("Entregador");

        jLabel3.setText("Produto");

        jLabel4.setText("Total");

        jLabelTotalPedido.setText("LabelTotal");

        jLabel6.setText("Desconto");

        jLabelDesconto.setText("LabelDesconto");

        jLabel5.setText("SubTotal");

        jLabelSubtotal.setText("LabelSubtotal");

        jLabel7.setText("Quantidade");

        jButtonFechar.setBackground(new java.awt.Color(51, 51, 51));
        jButtonFechar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonFechar.setText("Fechar");
        jButtonFechar.setBorder(null);
        jButtonFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFecharActionPerformed(evt);
            }
        });

        jSpinnerQuantidade.setModel(new javax.swing.SpinnerNumberModel(1, 0, null, 1));

        jLabelAvisosCliente.setForeground(new java.awt.Color(153, 0, 0));

        jLabelAvisosProduto.setForeground(new java.awt.Color(153, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelAvisosProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonRemoverProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelAvisosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelSubtotal, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelDesconto, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelTotalPedido, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jComboBoxClientes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxEntregadores, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBoxProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerQuantidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxEntregadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(jSpinnerQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonRemoverProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabelSubtotal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelAvisosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelDesconto)
                        .addComponent(jLabel6)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelAvisosProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelTotalPedido)
                            .addComponent(jLabel4))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void jButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAdicionarActionPerformed

    private void jButtonFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFecharActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonFecharActionPerformed

    public JButton getjButtonFechar() {
        return jButtonFechar;
    }

    public JSpinner getjSpinnerQuantidade() {
        return jSpinnerQuantidade;
    }

    

    public JTable getjTableProdutos() {
        return jTableProdutos;
    }

    public JButton getjButtonAdicionar() {
        return jButtonAdicionar;
    }

    public JButton getjButtonCadastrar() {
        return jButtonCadastrar;
    }

    public JButton getjButtonRemoverProduto() {
        return jButtonRemoverProduto;
    }

    public JComboBox<String> getjComboBoxClientes() {
        return jComboBoxClientes;
    }

    public JComboBox<String> getjComboBoxEntregadores() {
        return jComboBoxEntregadores;
    }

    public JComboBox<String> getjComboBoxProdutos() {
        return jComboBoxProdutos;
    }

    public JLabel getjLabelDesconto() {
        return jLabelDesconto;
    }

    public JLabel getjLabelSubtotal() {
        return jLabelSubtotal;
    }

    public JLabel getjLabelTotalPedido() {
        return jLabelTotalPedido;
    }

    public JLabel getjLabelAvisosCliente() {
        return jLabelAvisosCliente;
    }

    public JLabel getjLabelAvisosProduto() {
        return jLabelAvisosProduto;
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdicionar;
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JButton jButtonFechar;
    private javax.swing.JButton jButtonRemoverProduto;
    private javax.swing.JComboBox<String> jComboBoxClientes;
    private javax.swing.JComboBox<String> jComboBoxEntregadores;
    private javax.swing.JComboBox<String> jComboBoxProdutos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelAvisosCliente;
    private javax.swing.JLabel jLabelAvisosProduto;
    private javax.swing.JLabel jLabelDesconto;
    private javax.swing.JLabel jLabelSubtotal;
    private javax.swing.JLabel jLabelTotalPedido;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerQuantidade;
    private javax.swing.JTable jTableProdutos;
    // End of variables declaration//GEN-END:variables
}