package client;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class ClientRFC {
    public static Calendar calendar = new GregorianCalendar();
    public static Scanner entrada = new Scanner(System.in);
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");

    public static void main(String[]args) throws MalformedURLException {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("http://localhost:1200"));
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);
        String option = "",nombre = "",apellidoP = "",curp = "", rfc = "",date = "",month = "",year = "",apellidoM = "",birdth="",mensaje = "";
        int dia = 0, mes = 0, anio = 0;
        Object[]value;
        do{
            System.out.println("0.Salir\n" +
                    "1.Registrar nuevo usuario\n" +
                    "2.Editar usuario\n" +
                    "3.Consultar usuario\n" +
                    "4.Eliminar usuario");
            System.out.print("Escriba el número de la opción que desea realizar: ");
            option = entrada.next();
            if(!isNumber(option)){
                System.out.println("Escriba un número que se encuentre dentro del rango para continuar");
            }else{
                switch (Integer.parseInt(option)){
                    case 0:
                        System.out.println("Fin del programa");
                        break;
                    case 1:
                        System.out.println("Rellene todos los campos para continuar");
                        System.out.println("Nombre");
                        nombre = entrada.next();
                        System.out.println("Apellido Paterno");
                        apellidoP = entrada.next();
                        System.out.println("Apellido Materno");
                        apellidoM = entrada.next();
                        System.out.println("CURP");
                        curp = entrada.next();
                        do {
                            System.out.println("Día de nacimiento");
                            date = entrada.next();
                            if(!isNumber(date)){
                                System.out.println("Escriba el número del día en que nacio");
                            }else{
                                dia = Integer.parseInt(date);
                            }
                        }while (!isNumber(date));
                        do{
                            System.out.println("Mes de nacimiento");
                            month = entrada.next();
                            if(!isNumber(month)){
                                System.out.println("Escriba el número del mes en que nació");
                            }else{
                                mes = Integer.parseInt(month)-1;
                            }
                        }while (!isNumber(month));
                        do{
                            System.out.println("Año de nacimiento");
                            year = entrada.next();
                            if(!isNumber(year)){
                                System.out.println("Escriba el número del año en que nació");
                            }else{
                                anio = Integer.parseInt(year);
                            }
                        }while (!isNumber(year));
                        setDateCalendar(dia,mes,anio);
                        Date born = getDateCalendar();
                        birdth = DateAString(born);
                        rfc = setRFC(apellidoP,apellidoM,nombre,year,month,date);

                        value = new Object[6];
                        value[0] = nombre;
                        value[1] = apellidoP;
                        value[2] = apellidoM;
                        value[3] = curp;
                        value[4] = birdth;
                        value[5] = rfc;
                        try{
                            mensaje = (String) client.execute("Methods.savePerson",value);
                            System.out.println(mensaje);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        break;
                    case 3:
                        System.out.println("Escriba el CURP de la persona que quiere actualizar");
                        curp = entrada.next();
                        break;
                    case 4:
                        System.out.println("Escriba el CURP de la persona que quiere eliminar");
                        curp = entrada.next();
                        value = new Object[1];
                        value[0] = curp;
                        try{
                            mensaje = (String) client.execute("Methods.deletePerson",value);
                            System.out.println(mensaje);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                    default:
                        System.out.println("Número fuera del rango establecido. Ingrese un dato válido");
                        break;
                }
            }
        }while (!option.equals("0"));
    }
    public static boolean isNumber(String aux){
        try{
            int num =Integer.parseInt(aux);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public static String DateAString(Date fecha){
        return sdf.format(fecha);
    }
    public static void setDateCalendar (int dia, int mes, int anio){
        calendar.set(anio,mes,dia);
    }
    public static Date getDateCalendar(){
        return calendar.getTime();
    }
    public static String setRFC(String appellidoP, String appellidoM,String nombre,String anio,String mes,String dia){
        String rfc = "";
        rfc = appellidoP.trim().substring(0,2);
        rfc = rfc.concat(appellidoM.trim().substring(0,1));
        rfc = rfc.concat(nombre.trim().substring(0,1));
        rfc = rfc.concat(anio.trim().substring(2,4));
        rfc = rfc.concat(mes);
        rfc = rfc.concat(dia);
        rfc.toUpperCase();
        final String cadena = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        final int longitud = 3;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < longitud; i++){
            double alatorio = Math.random()* cadena.length();
            int posicion = (int) alatorio;
            char letra = cadena.charAt(posicion);
            sb.append(letra);
        }
        rfc = rfc.concat(sb.toString());
        return rfc;
    }
}
