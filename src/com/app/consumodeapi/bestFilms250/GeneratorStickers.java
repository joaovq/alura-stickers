package com.app.consumodeapi.bestFilms250;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

public class GeneratorStickers {

    public void create(InputStream inputStream, String nameFile) throws IOException, FileNotFoundException {
//        ler a imagem
//        BufferedImage image = ImageIO.read(new File("input/filme1.jpg"));
//        InputStream inputStream = new FileInputStream(new File("input/filme1.png"));


        BufferedImage image = ImageIO.read(inputStream);

//        cria nova imagem em memória com transparência e com tamanho novo
        int height = image.getHeight();
        int width = image.getWidth();
        int newHeight = height + 200;
        BufferedImage image1 = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);
//        copiar a imagem original pra nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) image1.getGraphics();
        graphics.drawImage(image, 0,0,null);

//        configurar a fonte
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setFont(font);
        graphics.setColor(Color.CYAN);

//        escrever uma frase na nova imagem.
        graphics.drawString("TOPZERA", 180,newHeight-100);
//        escrever a nova imagem em um arquivo
        ImageIO.write(image1,"png", new File("outputStickers/"+nameFile));

        System.out.println("\nSticker Generated!!");
    }
}
