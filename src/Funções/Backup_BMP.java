package Funções;

/**
 * @Author Jorgi Bolonhezi
 * @IDE Apache Netbeans 12.3 & Eclipse Luna
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Backup_BMP {

    FileChannel sourceInicial = null, sourceFinal = null;

    public void Backup(String Caminho_de_Origem, String Caminho_de_Destino) {

        try {
            try {
                sourceInicial = new FileInputStream(Caminho_de_Origem).getChannel();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Backup_BMP.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                sourceFinal = new FileOutputStream(Caminho_de_Destino).getChannel();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Backup_BMP.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                sourceInicial.transferTo(0, sourceInicial.size(), sourceFinal);
            } catch (IOException ex) {
                Logger.getLogger(Backup_BMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            if (sourceInicial != null && sourceInicial.isOpen()) {
                try {
                    sourceInicial.close();
                } catch (IOException ex) {
                    Logger.getLogger(Backup_BMP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (sourceFinal != null && sourceFinal.isOpen()) {
                try {
                    sourceFinal.close();
                } catch (IOException ex) {
                    Logger.getLogger(Backup_BMP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
