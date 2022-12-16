
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public final class DisplayImage {
    BufferedImage imgs[]=new BufferedImage[7];
    BufferedImage currentimg;
    JFrame frame;
    int currentIndex=0;
    public void crearArray() throws IOException{
        BufferedImage img0=ImageIO.read(new File("Planos/completa.jpg"));
        BufferedImage img1=ImageIO.read(new File("Planos/quinta.jpg"));
        BufferedImage img2=ImageIO.read(new File("Planos/cuarta.jpg"));
        BufferedImage img3=ImageIO.read(new File("Planos/tercera.jpg"));
        BufferedImage img4=ImageIO.read(new File("Planos/segunda.jpg"));
        BufferedImage img5=ImageIO.read(new File("Planos/primera.jpg"));
        BufferedImage img6=ImageIO.read(new File("Planos/baja.jpg"));

        imgs[0]=img0;
        imgs[1]=img1;
        imgs[2]=img2;
        imgs[3]=img3;
        imgs[4]=img4;
        imgs[5]=img5;
        imgs[6]=img6;

    }

    private String getTitulo(int pos){
        String vuelta="";


        if(pos==0) {
            vuelta = "Plano ETSIIT completo";
        }else if(pos==1){
            vuelta="Plano ETSIIT quinta planta";
        }else if(pos==2){
        vuelta="Plano ETSIIT cuarta planta";
        }else if(pos==3){
            vuelta="Plano ETSIIT tercera planta";
        }else if(pos==4){
            vuelta="Plano ETSIIT segunda planta";
        }else if(pos==5){
            vuelta="Plano ETSIIT primera planta";
        }else if(pos==6){
            vuelta="Plano ETSIIT planta baja";
        }

        return vuelta;

    }
    public DisplayImage() throws IOException
    {

        crearArray();

    }

    public void Showimg() throws IOException
    {

        currentimg=imgs[currentIndex];

        Image result=currentimg.getScaledInstance(1300,1000,Image.SCALE_SMOOTH);
        ImageIcon icon=new ImageIcon(result);
        frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(1200,800);
        String Titulo=getTitulo(currentIndex);
        frame.setTitle(Titulo);

        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public void ShowimgSube() throws IOException
    {

        currentIndex = currentIndex+1;
        if(currentIndex==7){
            currentIndex=0;
        }

        currentimg=imgs[currentIndex];
        Image result=currentimg.getScaledInstance(1300,1000,Image.SCALE_SMOOTH);
        ImageIcon icon=new ImageIcon(result);

        frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(500,500);
        String Titulo=getTitulo(currentIndex);
        frame.setTitle(Titulo);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public void ShowimgBaja() throws IOException
    {

        currentIndex = currentIndex - 1;
        if(currentIndex==-1){
            currentIndex=6;
        }


        currentimg=imgs[currentIndex];
        Image result=currentimg.getScaledInstance(1300,1000,Image.SCALE_SMOOTH);
        ImageIcon icon=new ImageIcon(result);

        frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(500,500);
        String Titulo=getTitulo(currentIndex);
        frame.setTitle(Titulo);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public void hideview() throws IOException{
        frame.setVisible(false);
    }



}