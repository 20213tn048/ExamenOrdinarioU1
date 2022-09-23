package server;

import utils.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DaoRFC {
    public boolean savePerson(String name, String ln1, String ln2, String curp, String born,String rfc){
        boolean result = false;
        try(Connection connection = MySQLConnection.getConnection();
            PreparedStatement pstm = connection.prepareStatement("insert into rfc (nombre,apellidoP,apellidoM,curp,born,rfc) values (?,?,?,?,?,?);")){
            pstm.setString(1,name);
            pstm.setString(2,ln1);
            pstm.setString(3,ln2);
            pstm.setString(4,curp);
            pstm.setString(5,born);
            pstm.setString(6,rfc);
            result = pstm.executeUpdate()==1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean deletePerson(String curp){
        boolean result = false;
        try(Connection connection = MySQLConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE from rfc where curp=?;")){
            pstm.setString(1,curp);
            result = pstm.executeUpdate()==1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean updatePerson(String nombre, String apellidoP, String apellidoM, String curp, String born, String rfc, String curpActual){
        boolean result = false;
        try(Connection connection=MySQLConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE rfc set nombre = ?, apellidoP = ?, apellidoM = ?, curp = ?, born = ? ,rfc=? where curp=?")){
            pstm.setString(1,nombre);
            pstm.setString(2,apellidoP);
            pstm.setString(3,apellidoM);
            pstm.setString(4,curp);
            pstm.setString(5,born);
            pstm.setString(6,rfc);
            pstm.setString(7,curpActual);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
