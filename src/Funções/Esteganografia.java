package Funções;

/**
 * @Author Jorgi Bolonhezi
 * @IDE Apache Netbeans 12.3 & Eclipse Luna
 */

public class Esteganografia {

    public static final String Characteres_UTF8
            = "abcdefghijklmnopqrstuvwxyzŕâéčęëîďôůűü˙ç"
            + "ABCDEFGHIJKLMNOPQRSTUVWXYZŔÂČÉĘËÎĎÔŮŰÜÇ"
            + "0123456789"
            + " ,.;:!?'Ťť\""
            + "()+-*/=<>[]{}|%"
            + "&~#@\\^"
            + "\t\n\r"
            + "§";

    
    
    public static int Posição(char Character) {
        int Posição = 0;
        while (Posição < Characteres_UTF8.length() && Characteres_UTF8.charAt(Posição) != Character) {
            Posição++;
        }
        return Posição;
    }
    
    

    public static PixelBMP Esconder_um_Charactere(PixelBMP Pixel, char Character) {
        
        //Esconde um caractere em um pixel
        int R = Pixel.getR(), B = Pixel.getB(), G = Pixel.getR(), Posição_do_Character_em_Characteres = Posição(Character);
        if (Posição_do_Character_em_Characteres == Characteres_UTF8.length()) {
            Posição_do_Character_em_Characteres = Posição('?');
        }

        //checa as posições no alfabeto UTF8
        int Chacarater_em_R = (Posição_do_Character_em_Characteres / 25) % 5, Chacarater_em_G = (Posição_do_Character_em_Characteres / 5) % 5, Chacarater_em_B = Posição_do_Character_em_Characteres % 5;

        //Substitui o bit menos siginificativo do pixel por pela crifra criada.
        int Novo_R = (R / 5) * 5 + Chacarater_em_R, Novo_G = (G / 5) * 5 + Chacarater_em_G, Novo_B = (B / 5) * 5 + Chacarater_em_B;

        if (Novo_R > 255) {
            Novo_R = Novo_R - 5;
        }
        if (Novo_G > 255) {
            Novo_G = Novo_G - 5;
        }
        if (Novo_B > 255) {
            Novo_B = Novo_B - 5;
        }

        return new PixelBMP(Novo_R, Novo_G, Novo_B);
    }

    
    
    public  static char Extrair_um_Charactere(PixelBMP Pixel) {
        //Extrai o charactere escondido em um pixel
        int Charactere_em_R = Pixel.getR(),Charactere_em_G = Pixel.getG(), Charactere_em_B = Pixel.getB(), Classificação = (Charactere_em_R % 5) * 25 + (Charactere_em_G % 5) * 5 + Charactere_em_B % 5;
        char Charactere_no_Pixel = Characteres_UTF8.charAt(Classificação);
        return Charactere_no_Pixel;
    }

    
    
    public static void Esconder_Texto(ImagemBMP Imagem, String Texto_a_ser_Escondido) {

        int Largura_do_Pixel_na_Imagem = 0;
        Texto_a_ser_Escondido = Texto_a_ser_Escondido + "§";

        for (int Altura_do_Pixel_na_Imagem = 0; Altura_do_Pixel_na_Imagem < Imagem.Altura_da_Imagem() && Largura_do_Pixel_na_Imagem < Texto_a_ser_Escondido.length(); Altura_do_Pixel_na_Imagem++) {
            for (int Largura_do_Pixel_da_Imagem = 0; Largura_do_Pixel_da_Imagem < Imagem.Largura_da_Imagem() && Largura_do_Pixel_na_Imagem < Texto_a_ser_Escondido.length(); Largura_do_Pixel_da_Imagem++) {
                PixelBMP Pixel = Esconder_um_Charactere(Imagem.getPixelRGB(Altura_do_Pixel_na_Imagem, Largura_do_Pixel_da_Imagem), Texto_a_ser_Escondido.charAt(Largura_do_Pixel_na_Imagem));
                Imagem.setPixelRGB(Altura_do_Pixel_na_Imagem, Largura_do_Pixel_da_Imagem, Pixel);
                Largura_do_Pixel_na_Imagem++;
            }
        }

    }

    
    
    public static String extraeTexto(ImagemBMP Imagem) {
        
        String Texto_Extraido = "";
        boolean Booleano_Auxiliar = true;
        
        for (int Altura_do_Pixel_na_Imagem = 0; Altura_do_Pixel_na_Imagem < Imagem.Altura_da_Imagem() && Booleano_Auxiliar; Altura_do_Pixel_na_Imagem++) {
            for (int Largura_do_Pixel_na_Imagem = 0; Largura_do_Pixel_na_Imagem < Imagem.Largura_da_Imagem() && Booleano_Auxiliar; Largura_do_Pixel_na_Imagem++) {
                Texto_Extraido = Texto_Extraido + Extrair_um_Charactere(Imagem.getPixelRGB(Altura_do_Pixel_na_Imagem, Largura_do_Pixel_na_Imagem));
                if (Extrair_um_Charactere(Imagem.getPixelRGB(Altura_do_Pixel_na_Imagem, Largura_do_Pixel_na_Imagem)) == '§') {
                    Booleano_Auxiliar = false;
                }
            }
        }
        return Texto_Extraido;
    }
    
}
