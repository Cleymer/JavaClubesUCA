/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import com.sun.xml.internal.ws.streaming.XMLStreamReaderUtil;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Fam Zavala
 */
public class Conexion {
    private final String controlador="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private final String url_BD="jdbc:sqlserver://localhost:1433;databaseName=ClubUCA";
    private final String usuario="sa";
    private final String contraseña="123";

    public Conexion() {
    }
 
    public Connection obtenerConexion(){      
         try {
            Class.forName(controlador);
            return(DriverManager.getConnection(url_BD, usuario, contraseña));
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }  
    
    public void cerrarConexion(Connection conn){
        try {
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public ResultSet listarConsulta(String tsql){
        Statement instruccion = null;
        ResultSet conjuntoResultados = null;
        Connection conn = this.obtenerConexion();
        try{   
            instruccion = conn.createStatement();
            conjuntoResultados = instruccion.executeQuery(tsql);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return conjuntoResultados;
    }
    
    public String validarPWDCoordinacion(String nombre){
        String consulta = "Select * from Coordinacion"
                + "where nombre = '"+nombre+"'";
        String n=null;
        ResultSet rs = null;
        Statement ps = null;
        Conexion conex = new Conexion();
        Connection conn = conex.obtenerConexion();
        
        try{
            
            ps = conn.createStatement();
            rs = conex.listarConsulta(consulta);
            
            if(rs.next()){
                String c = rs.getString("pwd");
                n= c;
            }
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }finally{
            try{
                rs.close();
                ps.close();
                conex.cerrarConexion(conn);
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return n;
    }
}
