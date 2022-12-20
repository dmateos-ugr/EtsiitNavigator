import javax.swing.*;

public class infoPlanta {

    popUpClass[] planta;
    int total=0;

    infoPlanta(int numbotones, String[] nombres, String[] info, JFrame sup){

        total=numbotones;
        planta=new popUpClass[total];


        for(int i=0; i<total; i++){
            planta[i]=new popUpClass(nombres[i],info[i],sup);
        }

       // sup.requestFocus();

    }

    JButton getButton(int a){
        return planta[a].Button();
    }

    public int getNum(){
        return total;
    }

}


