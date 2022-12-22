import java.awt.*;

public final class PlantasCompletas {

    public static Planta getPlanta(int a,Image img, Dimension _img, Dimension _old){
        Planta res = null;
        switch(a){
            case 7: {
                //completa -> botones: 2
                String uno=" La Escuela Técnica Superior de Ingenierías Informática y de Telecomunicación (ETSIIT) de Granada es un centro universitario perteneciente a la Universidad de Granada,\n " +
                        "dedicado a la docencia e investigación de los estudios relacionados con la informática, las ciencias de la computación y las telecomunicaciones.\n" +
                        "En los últimos años se sitúa entre las cincuenta mejores escuelas a nivel mundial en la materia Ciencias de la Computación, concretamente, durante el año 2017 se situó en la posición 33 de acuerdo al ranking de Shanghái. \n" +
                        "Se ubica en el Campus Universitario de Aynadamar, junto a la Facultad de Bellas Artes y al Centro de Investigación en Tecnologías de la Información y la comunicación de la UGR (CITIC-UGR).";

                String dos="Situandonos en este punto podremos acceder a los dos edificios principales de la etsiit, el primero en el que se encuentra la secretaria, la biblioteca y la administración y el segundo en el que se encuentran las aulas. \n" +
                        "También podemos ir a las aulas prefabricadas";


                String[] nomCompleta = new String[]{"ETSIIT", "Patio"};
                String[] infoCompleta = new String[]{uno, dos};
                Rectangle[] rectBoton = new Rectangle[]{
                        new Rectangle(80,80,100,100),
                        new Rectangle(256,459,350,100)
                };
                res = new Planta(img, _img,_old, nomCompleta, infoCompleta, rectBoton);
            }
            break;
            case 6: {
                //quinta -> botones: 0
                String[] nomQuinta= new String[]{ "ETSIIT quinta"};
                String[] infoQuinta = new String[]{"ETSIIT quinta"};

                Rectangle[] rectBoton = new Rectangle[]{
                        new Rectangle(0,0,0,0)
                };
                res = new Planta(img, _img,_old, nomQuinta, infoQuinta, rectBoton);
            }
            break;
            case 5: {
                //cuarta -> botones: 1
                String uno= "En esta planta no hay aulas solo despachos";
                String[] nomCuarta = new String[]{"Cuarta planta"};
                String[] infoCuarta = new String[]{uno};


                Rectangle[] rectBoton = new Rectangle[]{
                        new Rectangle(229,268,50,50),
                };
                res = new Planta(img, _img,_old, nomCuarta, infoCuarta, rectBoton);
            }
            break;
            case 4: {
                //tercera -> botones: 1
                String uno="Despacho del profesor Marcelino Cabrera, profesor de la asignatura Nuevos Paradigmas de Interacción.";
                String[] nomTercera = new String[]{"Despacho 21"};
                String[] infoTercera = new String[]{uno};
                Rectangle[] rectBoton = new Rectangle[]{
                        new Rectangle(200,100,200,200),
                };
                res = new Planta(img, _img,_old, nomTercera, infoTercera, rectBoton);
            }
            break;

            case 3: {
                //segunda -> botones: 1
                String uno=" Los departamentos con sede principal en el centro son: Departamento de Arquitectura y Tecnología de Computadores, Departamento de Ciencias de la Computación e Inteligencia Artificial,\n" +
                        "Departamento de Lenguajes y Sistemas Informáticos y Departamento de Teoría de la Señal, Telemática y Comunicaciones";

                String[] nomSegunda = new String[]{"Departamentos"};
                String[] infoSegunda = new String[]{uno};

                Rectangle[] rectBoton = new Rectangle[]{
                        new Rectangle(200,140,200,200),
                };
                res = new Planta(img, _img,_old, nomSegunda, infoSegunda, rectBoton);
            }
            break;
            case 2: {
                //primera -> botones: 2
                String uno="Horario de lunes a viernes de 8:30 a 20:30, dispone de un amplio de catálogo de libros disponibles para su prestamo";
                String dos="Aulas de estudio grupales que se pueden alquilar durante 3 horas para realizar trabajos grupales ";

                String[] nomPrimera = new String[]{"Biblioteca", "Aulas de estudio"};
                String[] infoPrimera = new String[]{uno, dos};
                Rectangle[] rectBoton = new Rectangle[]{
                        new Rectangle(221,161,200,200),
                        new Rectangle(130,375,100,100)
                };
                res = new Planta(img, _img,_old, nomPrimera, infoPrimera, rectBoton);
            }
            break;

            case 1: {
                //baja -> botones: 2
                String uno="La Secretaría de la ETSIIT se encuentra en el hall principal, constituyendo el centro administrativo del centro. Los trámites administrativos relacionados de matriculación, \n" +
                        "solicitud de becas y otros muchos se realizan a través de la Administración Electrónica. El horario de atención al público es de 9 a 14 horas, de lunes a viernes";
                String dos="Baños de la planta 0 del edificio de aulas";
                String[] nomBaja = new String[]{"Secretaria", "Baños edificio aulas"};
                String[] infoBaja = new String[]{uno,dos};
                Rectangle[] rectBoton = new Rectangle[]{
                        new Rectangle(200,200,200,200),
                        new Rectangle(530,320,100,100)
                };
                res = new Planta(img, _img, _old, nomBaja, infoBaja, rectBoton);
            }
            break;


            case 0: {
                //sotano -> botones: 3
                String uno="Comedores UGR su horario es de lunes a viernes de 13:00 a 15:30 horas. Precio: 3.5 euros";
                String dos="Cafeteria de la ETSIIT su horario de lunes a viernes de 8:30 a 20:30";
                String tres="Salon de actos de la ETSIIT con una capacidad de 196 personas";

                String[] nomSotano = new String[]{"Comedores","Cafeteria","Salon de actos"};
                String[] infoSotano = new String[]{uno,dos,tres};
                Rectangle[] rectBoton = new Rectangle[]{
                        new Rectangle(312,280,200,100),
                        new Rectangle(165,231,100,100),
                        new Rectangle(265,240,100,100)
                };
                res = new Planta(img, _img,_old, nomSotano, infoSotano, rectBoton);
            }
            break;
        }
        return res;
    }
}
