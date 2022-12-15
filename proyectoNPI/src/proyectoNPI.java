import java.io.IOException;
import java.util.Scanner;

public class proyectoNPI {

    public static void main(String[] args) throws IOException {

        int num;
        int continuar=1;

        Scanner leer=new Scanner(System.in);

        DisplayImage abc;
        abc=new DisplayImage();
        abc.Showimg();

        while(continuar==1){
            System.out.println("Introduzca un numero");
            num=leer.nextInt();
            if(num==1){
                abc.hideview();
                abc.ShowimgSube();
            }else if(num==2){
                abc.hideview();
                abc.ShowimgBaja();
            }else{
                continuar=0;
            }
        }

        System.exit(0);

    }

}
