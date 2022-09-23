package server;

import java.util.ArrayList;

public class Methods {
    public Methods(){}

    public String savePerson(String nombre, String apellidoP, String apellidoM, String nacimiento,String curp, String rfc){
        DaoRFC daoRFC = new DaoRFC();
        boolean result = daoRFC.savePerson(nombre,apellidoP,apellidoM,curp,nacimiento,rfc);
        return "Registro exitoso\n" +
                "Nombre: "+nombre+"\n" +
                "Apellido Paterno: "+apellidoP+"\n" +
                "Apellido Materno: "+apellidoM+"\n" +
                "CURP: "+curp+"\n" +
                "Fecha de nacimiento: "+nacimiento+"\n" +
                "RFC (Generado automáticamente): "+rfc+"\n";
    }

    public String deletePerson(String curp){
        DaoRFC daoRFC = new DaoRFC();
        boolean result = daoRFC.deletePerson(curp);
        return "Usuario eliminado";
    }
    public String updatePerson(String nombre, String apellidoP, String apellidoM, String nacimiento, String curp, String rfc, String curpActual){
        DaoRFC daoRFC = new DaoRFC();
        boolean result = daoRFC.updatePerson(nombre,apellidoP,apellidoM,nacimiento,curp,rfc,curpActual);
        return "Usuario actualizado";
    }
}
