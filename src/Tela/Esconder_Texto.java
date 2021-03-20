package Tela;

import Fun��es.Backup_BMP;
import Fun��es.Esteganografia;
import Fun��es.ImagemBMP;
import java.io.File;
import javax.swing.JOptionPane;

/**
 * @Author Jorgi Bolonhezi
 * @IDE Apache Netbeans 12.3 & Eclipse Luna
 */

public class Esconder_Texto extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	/**
     * Creates new form Esconder_Texto
     */
    public Esconder_Texto() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jEsconderTexto = new javax.swing.JButton();
        jFileChooser = new javax.swing.JFileChooser();
        jTexto = new javax.swing.JScrollPane();
        jAreaTexto = new javax.swing.JTextArea();
        jMenuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemEsconderTexto = new javax.swing.JMenuItem();
        jMenuItemExtrairTexto = new javax.swing.JMenuItem();
        jMenuItemCr�ditos = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Esconder Texto - INFO7010");

        jEsconderTexto.setText("ESCONDER TEXTO");
        jEsconderTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEsconderTextoActionPerformed(evt);
            }
        });

        jAreaTexto.setColumns(20);
        jAreaTexto.setRows(5);
        jTexto.setViewportView(jAreaTexto);

        jMenu1.setText("Op��es");

        jMenuItemEsconderTexto.setText("Esconder Texto");
        jMenuItemEsconderTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEsconderTextoActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemEsconderTexto);

        jMenuItemExtrairTexto.setText("Extrair Texto");
        jMenuItemExtrairTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExtrairTextoActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemExtrairTexto);

        jMenuItemCr�ditos.setText("Cr�ditos");
        jMenuItemCr�ditos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCr�ditosActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemCr�ditos);

        jMenuBar.add(jMenu1);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jEsconderTexto, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                    .addComponent(jFileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTexto, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(jEsconderTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void jMenuItemEsconderTextoActionPerformed(java.awt.event.ActionEvent evt) {                                                       
        Esconder_Texto Esconder = new Esconder_Texto();
        Esconder.setLocationRelativeTo(null);
        Esconder.setVisible(true);
    }                                                      

    private void jMenuItemExtrairTextoActionPerformed(java.awt.event.ActionEvent evt) {                                                      
        Extrair_Texto Extrair = new Extrair_Texto();
        Extrair.setLocationRelativeTo(null);
        Extrair.setVisible(true);
    }                                                     

    private void jMenuItemCr�ditosActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        JOptionPane.showMessageDialog(null, "Programa desenvolvido por Jorgi Bolonhezi para a Disciplina de Criptografia do Programa de P�s-Gradua��o em Inform�tica da Universidade Federal do Paran�.");
    }                                                 

    private void jEsconderTextoActionPerformed(java.awt.event.ActionEvent evt) {                                               
        
        File ArquivoSelecionado = jFileChooser.getSelectedFile();
        String Caminho_da_Imagem = ArquivoSelecionado.getAbsolutePath();
        Backup_BMP Backup = new Backup_BMP();
        Backup.Backup(Caminho_da_Imagem, "Imagem_Original.bmp");
        ImagemBMP Imagem = new ImagemBMP(Caminho_da_Imagem);
        String Texto_para_Esconder = jAreaTexto.getText();
        Esteganografia.Esconder_Texto(Imagem, Texto_para_Esconder);
        Imagem.Salvar_Imagem_BMP(Caminho_da_Imagem);
        jAreaTexto.setText("");
        JOptionPane.showMessageDialog(null, "Texto Escondido com Sucesso!");
        
    }                                              

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Esconder_Texto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Esconder_Texto().setVisible(true);
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JTextArea jAreaTexto;
    private javax.swing.JButton jEsconderTexto;
    private static javax.swing.JFileChooser jFileChooser;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem jMenuItemCr�ditos;
    private javax.swing.JMenuItem jMenuItemEsconderTexto;
    private javax.swing.JMenuItem jMenuItemExtrairTexto;
    private javax.swing.JScrollPane jTexto;
    // End of variables declaration                   
}
