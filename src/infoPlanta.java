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



    }

    JButton getButton(int a){
        return planta[a].Button();
    }

    JDialog getDialog(int a){
        return planta[a].Dialog();
    }

    public int getNum(){
        return total;
    }

}


