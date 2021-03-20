package Funções;

/**
 * @Author Jorgi Bolonhezi
 * @IDE Apache Netbeans 12.3 & Eclipse Luna
 */

public class PixelBMP {

    private int R, G, B;

    //Construtor do Pixel RGB
    //Requisitos 0<=R<256 0<=G<256 0<=B<256
    public PixelBMP(int R, int G, int B) {
        this.R = R;
        this.G = G;
        this.B = B;
    }

    //gera um pixel branco
    public static PixelBMP Branco() {
        return new PixelBMP(255, 255, 255);
    }

    //gera um pixel preto
    public static PixelBMP Preto() {
        return new PixelBMP(0, 0, 0);
    }

    //gera um pixel cinza com a intensidade desejada
    public static PixelBMP Cinza(int Intensidade) {
        return new PixelBMP(Intensidade, Intensidade, Intensidade);
    }

    //R equivale a cor vermelha que é utilizada juntamente ao azul e verde para gerar as demais cores
    public int getR() {
        return R;
    }

    //G equivale a cor verde que é utilizada juntamente ao azul e vermelha para gerar as demais cores
    public int getG() {
        return G;
    }

    //B equivale a cor vermelha que é utilizada juntamente ao vermelho e verde para gerar as demais cores
    public int getB() {
        return B;
    }

    //converte a lista de inteiro que compõem a cor em uma string com formato de lista
    @Override
    public String toString() {
        return "<" + R + "," + G + "," + B + ">";
    }

}
