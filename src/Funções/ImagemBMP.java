package Funções;

/**
 * @Author Jorgi Bolonhezi
 * @IDE Apache Netbeans 12.3 & Eclipse Luna
 */

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;

public class ImagemBMP {

    private BufferedImage Imagem_em_Buffer;

    public ImagemBMP(String Nome_do_Arquivo) {
        // Checa se o arquivo selecionado é um BMP
        if (Nome_do_Arquivo.length() < 4 || !Nome_do_Arquivo.substring(Nome_do_Arquivo.length() - 4, Nome_do_Arquivo.length()).equals(".bmp")) {
            Nome_do_Arquivo = Nome_do_Arquivo + ".bmp";
        }
        try {
            try (DataInputStream DataImput_BMP = new DataInputStream(new FileInputStream(new File(Nome_do_Arquivo)))) {
                DataImput_BMP.skipBytes(18); // Pula os bytes com os headers
                int Largura_Em_PX_da_Imagem = Inteiros_de_32bits(DataImput_BMP);
                int Altura_Em_PX_da_Imagem = Inteiros_de_32bits(DataImput_BMP);
                DataImput_BMP.skipBytes(28); // Pula os bytes que são utilizados para calcular o tamanho da imagem em KB/MB/GB
                // Cria uma imagem em memória identica e preencha os pixels
                Imagem_em_Buffer = new BufferedImage(Largura_Em_PX_da_Imagem, Altura_Em_PX_da_Imagem, BufferedImage.TYPE_INT_RGB);
                int Conteúdo = (4 - ((Largura_Em_PX_da_Imagem * 3) % 4)) % 4; // numero de bytes dos pixels que formam a imagem em cada linha
                // Lê os pixels da imagem
                for (int y = Altura_Em_PX_da_Imagem - 1; y >= 0; y--) {
                    for (int x = 0; x < Largura_Em_PX_da_Imagem; x++) {
                        Imagem_em_Buffer.setRGB(x, y, Inteiros_de_24bits(DataImput_BMP));
                    }
                    DataImput_BMP.skipBytes(Conteúdo); // pula o conteúdo da imagem
                }
            }
        } catch (IOException Error) {
            JOptionPane.showMessageDialog(null, "Problema na Leitura do Arquivo: " + Nome_do_Arquivo);
        }
    }

    private ImagemBMP(int Largura_da_Imagem, int Altura_da_Imagem) {
        // Definindo pixels para largura e altura da imagem
        Imagem_em_Buffer = new BufferedImage(Largura_da_Imagem, Altura_da_Imagem, BufferedImage.TYPE_INT_RGB);
    }

    public static ImagemBMP Montagem_de_Pixels(int Largura_da_Imagem, int Altura_da_Imagem, PixelBMP Pixel) {
        // constrõe uma imagem a partir de pixels recebidos com Largura e Altura definidas
        ImagemBMP ImagemRGB = new ImagemBMP(Largura_da_Imagem, Altura_da_Imagem);
        for (int Altura = 0; Altura < Altura_da_Imagem; Altura++) {
            for (int Largura = 0; Largura < Largura_da_Imagem; Largura++) {
                ImagemRGB.setPixelRGB(Altura, Largura, Pixel);
            }
        }
        return ImagemRGB;
    }

    public static ImagemBMP Branco(int Largura_da_Imagem, int Altura_da_Imagem) {
        // metodo cria uma imagem de altura e largura definidas totalmente em branco
        return Montagem_de_Pixels(Largura_da_Imagem, Altura_da_Imagem, PixelBMP.Branco());
    }

    public static ImagemBMP Preto(int Largura_da_Imagem, int Altura_da_Imagem) {
        /// metodo cria uma imagem de altura e largura definidas totalmente em preto
        return Montagem_de_Pixels(Largura_da_Imagem, Altura_da_Imagem, PixelBMP.Preto());
    }

    private static int Inteiros_de_32bits(DataInputStream Data_Imput) {
        //metodo para ler enteiros de 32bits
        byte[] Byte = new byte[4];
        int Resultado = 0;
        try {
            Data_Imput.read(Byte);
            Resultado = Byte[0] & 0xFF;
            Resultado = Resultado + ((Byte[1] & 0xFF) << 8);
            Resultado = Resultado + ((Byte[2] & 0xFF) << 16);
            Resultado = Resultado + ((Byte[3] & 0xFF) << 24);
        } catch (IOException Error) {
        }
        return Resultado;
    }

    private static int Inteiros_de_24bits(DataInputStream Data_Imput) {
        //metodo para ler enteiros de 24bits
        byte[] Byte = new byte[3];
        int Resultado = 0;
        try {
            Data_Imput.read(Byte);
            Resultado = Byte[0] & 0xFF;
            Resultado = Resultado + ((Byte[1] & 0xFF) << 8);
            Resultado = Resultado + ((Byte[2] & 0xFF) << 16);
        } catch (IOException Error) {
        }
        return Resultado;
    }

    public boolean Salvar_Imagem_BMP(String Nome_do_Arquivo) {
        if (Nome_do_Arquivo.length() < 4 || !Nome_do_Arquivo.substring(Nome_do_Arquivo.length() - 4, Nome_do_Arquivo.length()).equals(".bmp")) {
            Nome_do_Arquivo = Nome_do_Arquivo + ".bmp";
        }
        try {
            try (DataOutputStream Data_Output_BMP = new DataOutputStream(new FileOutputStream(Nome_do_Arquivo))) {
                //escreve o header do fixeiro
                Data_Output_BMP.write(0x42);
                Data_Output_BMP.write(0x4D); //Tipo do arquivo
                int Largura_da_Imagem = Imagem_em_Buffer.getWidth();
                int Altura_da_Imagem = Imagem_em_Buffer.getHeight();
                int Conteúdo_da_Imagem = (4 - ((Largura_da_Imagem * 3) % 4)) % 4; // número de bytes
                Escreve_Inteiro_de_32bits(Data_Output_BMP, Altura_da_Imagem * (Largura_da_Imagem * 3 + Conteúdo_da_Imagem) + 54); // tamanho do arquivo bmp
                Escreve_Inteiro_de_32bits(Data_Output_BMP, 0); // Reservado para o header
                Escreve_Inteiro_de_32bits(Data_Output_BMP, 54);
                Escreve_Inteiro_de_32bits(Data_Output_BMP, 40);
                Escreve_Inteiro_de_32bits(Data_Output_BMP, Largura_da_Imagem); // Largura da imagem 
                Escreve_Inteiro_de_32bits(Data_Output_BMP, Altura_da_Imagem); // Altura da imagem
                Data_Output_BMP.write(1);
                Data_Output_BMP.write(0);
                Data_Output_BMP.write(24);
                Data_Output_BMP.write(0); // Número de bits por pixel
                Escreve_Inteiro_de_32bits(Data_Output_BMP, 0); // sem comprensão da imagem
                Escreve_Inteiro_de_32bits(Data_Output_BMP, Altura_da_Imagem * (Largura_da_Imagem * 3 + Conteúdo_da_Imagem)); // tamanho da imagem em bytes
                Escreve_Inteiro_de_32bits(Data_Output_BMP, 2851);
                Escreve_Inteiro_de_32bits(Data_Output_BMP, 2851); // resolução em altura x largura
                Escreve_Inteiro_de_32bits(Data_Output_BMP, 0);
                Escreve_Inteiro_de_32bits(Data_Output_BMP, 0);
                for (int Altura = Altura_da_Imagem - 1; Altura >= 0; Altura--) {
                    for (int Largura = 0; Largura < Largura_da_Imagem; Largura++) {
                        Escreve_Inteiro_de_24bits(Data_Output_BMP, Imagem_em_Buffer.getRGB(Largura, Altura));
                    }
                    //conteúdo
                    for (int Conteúdo = 0; Conteúdo < Conteúdo_da_Imagem; Conteúdo++) {
                        Data_Output_BMP.write(0);
                    }
                }
                //fecha o arquivo
            }
            return true;
        } catch (IOException Error) {
            JOptionPane.showMessageDialog(null, "ERROR : " + Error);
            return false;
        }
    }

    private static void Escreve_Inteiro_de_32bits(DataOutputStream Saida, int Inteiro_32bits) {
        // Escreve o pixel correspondente nos 4 bytes em formato little endian
        try {
            Saida.write((Inteiro_32bits) & 0xFF);
            Saida.write((Inteiro_32bits >> 8) & 0xFF);
            Saida.write((Inteiro_32bits >> 16) & 0xFF);
            Saida.write((Inteiro_32bits >> 24) & 0xFF);
        } catch (IOException Error) {
        }
    }

    private static void Escreve_Inteiro_de_24bits(DataOutputStream Saida, int Inteiro_24bits) {
        // Escreve o pixel correspondente nos 3 bytes
        try {
            Saida.write((Inteiro_24bits) & 0xFF);
            Saida.write((Inteiro_24bits >> 8) & 0xFF);
            Saida.write((Inteiro_24bits >> 16) & 0xFF);
        } catch (IOException Error) {
        }
    }

    public int Altura_da_Imagem() {
        return Imagem_em_Buffer.getHeight();
    }

    public int Largura_da_Imagem() {
        return Imagem_em_Buffer.getWidth();
    }

    public PixelBMP getPixelRGB(int Posição_do_Pixel_na_Altura_da_Imagem, int Posição_do_Pixel_na_Largura_da_Imagem) {
        int Pixel = Imagem_em_Buffer.getRGB(Posição_do_Pixel_na_Largura_da_Imagem, Posição_do_Pixel_na_Altura_da_Imagem);
        return new PixelBMP((Pixel >> 16) & 0xFF, (Pixel >> 8) & 0xFF, Pixel & 0xFF);
    }

    public void setPixelRGB(int Posição_do_Pixel_na_Altura_da_Imagem, int Posição_do_Pixel_na_Largura_da_Imagem, PixelBMP Pixel) {
        int Pixel_Auxiliar = Pixel.getB() + (Pixel.getG() << 8) + (Pixel.getR() << 16);
        Imagem_em_Buffer.setRGB(Posição_do_Pixel_na_Largura_da_Imagem, Posição_do_Pixel_na_Altura_da_Imagem, Pixel_Auxiliar);
    }

}
