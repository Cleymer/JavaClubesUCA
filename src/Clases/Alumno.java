/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import Clases.Conexion;
import clases.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Administrador
 */
public class Alumno extends Persona{

    private String id;
    private String tiposangre;
    private String enfermedades;
    private int ciudad;
    private String direccion;
    private String tutor;
    private String telftutor;
    private String correotutor;
    private boolean beca;
    private String tipobeca;
    private String añocursando;
    private String carrera;
    private boolean activo;

    public Alumno() {
    }

    public Alumno(String id, String tiposangre, String enfermedades, int ciudad, String direccion, String tutor, String telftutor, String correotutor, boolean beca, String tipobeca, String añocursando, String carrera, boolean activo, String nombre, String apellidos, Date fechanac, String email, String telefono, String sexo, String pw) {
        super(nombre, apellidos, fechanac, email, telefono, sexo, pw);
        this.id = id;
        this.tiposangre = tiposangre;
        this.enfermedades = enfermedades;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.tutor = tutor;
        this.telftutor = telftutor;
        this.correotutor = correotutor;
        this.beca = beca;
        this.tipobeca = tipobeca;
        this.añocursando = añocursando;
        this.carrera = carrera;
        this.activo = activo;
    }

    

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTiposangre() {
        return tiposangre;
    }

    public void setTiposangre(String tiposangre) {
        this.tiposangre = tiposangre;
    }

    public String getEnfermedades() {
        return enfermedades;
    }

    public void setEnfermedades(String enfermedades) {
        this.enfermedades = enfermedades;
    }

    public int getCiudad() {
        return ciudad;
    }

    public void setCiudad(int ciudad) {
        this.ciudad = ciudad;
    }

    

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getTelftutor() {
        return telftutor;
    }

    public void setTelftutor(String telftutor) {
        this.telftutor = telftutor;
    }

    public String getCorreotutor() {
        return correotutor;
    }

    public void setCorreotutor(String correotutor) {
        this.correotutor = correotutor;
    }

    public boolean isBeca() {
        return beca;
    }

    public void setBeca(boolean beca) {
        this.beca = beca;
    }

    public String getTipobeca() {
        return tipobeca;
    }

    public void setTipobeca(String tipobeca) {
        this.tipobeca = tipobeca;
    }

    public String getAñocursando() {
        return añocursando;
    }

    public void setAñocursando(String añocursando) {
        this.añocursando = añocursando;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

     @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = String.valueOf(sdf.format(fechanac));
        return "Alumno{" + "id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fechaNac=" + fecha + ", tipoSangre=" + tiposangre + ", enfermedades=" + enfermedades + ", telefono="+ telefono+ ", email = "+ email + "sexo=" + sexo + ", ciudad=" + ciudad +
                ", direccion=" + direccion + ", tutor=" + tutor + ", telftutor =" + telftutor + ", correotutor=" + correotutor + ", beca=" + beca +
                 ", tipoBeca=" + tipobeca + ", añoCursando=" + añocursando + ", carrera=" + carrera +
                ", activo=" + activo + '}';
    }
    
      /*Metodos que si utilizo*/
      
    public boolean editarEstado(String id){
        boolean b = false;
        String consulta = "";
        Conexion conex = new Conexion();
        PreparedStatement instruccion = null;
        Connection conn = conex.obtenerConexion();
        
        try{
            consulta = "Update Alumno set activo=0 where id='"+id+"'";
            instruccion = conn.prepareStatement(consulta);
            if(instruccion.executeUpdate()==1){
                b=true;
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }finally {
            try {
                instruccion.close();
                conex.cerrarConexion(conn);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
        return b;
    }  
    
    public DefaultTableModel verFacultad(String idclub, String idfacultad){
        DefaultTableModel tabla = new DefaultTableModel();
        
        String consulta = "Select a.id, a.nombre, a.apellidos, c.nombre as clubN, ca.nombre as carrer from Alumno a, Club c, Solicitud s, Carrera ca, Facultad f "
                + "where a.id = s.alumno and c.id = s.club and a.carrera = ca.id and a.activo=1 and ca.facultad = f.id and c.nombre='"+idclub+"' and f.nombre='"+idfacultad+"' and s.estadosol = 'Aceptada'";
        
        ResultSet rs = null;
        Statement ps = null;
        Conexion conex = new Conexion();
        Connection conn = conex.obtenerConexion();
        try{
            
            ps = conn.createStatement();
            rs = conex.listarConsulta(consulta);
            String titulos[]={"ID","Nombre","Apellidos","Carrera","Club"};
            tabla.setColumnIdentifiers(titulos);
            while(rs.next()){
                Object fila[] = new Object[7];
                fila[0]=rs.getString("id");
                fila[1]=rs.getString("nombre");
                fila[2]=rs.getString("apellidos");
                fila[4]=rs.getString("clubN");
                fila[3]=rs.getString("carrer");
                tabla.addRow(fila);
                
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
        return tabla;
    }
    
    public DefaultTableModel verCarrera(String idclub, String idcarrera){
        DefaultTableModel tabla = new DefaultTableModel();
        String consulta = "select a.id, a.nombre, a.apellidos, c.nombre as clubN, ca.nombre as carrer from Alumno a, Club c, Solicitud s, Carrera ca where a.id = s.alumno and c.id = s.club and a.carrera = ca.id and a.activo=1 and c.nombre='"+idclub+"' and ca.nombre='"+idcarrera+"' and s.estadosol = 'Aceptada'" ;
        ResultSet rs = null;
        Statement ps = null;
        Conexion conex = new Conexion();
        Connection conn = conex.obtenerConexion();
        try{
            
            ps = conn.createStatement();
            rs = conex.listarConsulta(consulta);
            String titulos[]={"ID","Nombre","Apellidos","Carrera","Club"};
            tabla.setColumnIdentifiers(titulos);
            while(rs.next()){
                Object fila[] = new Object[7];
                fila[0]=rs.getString("id");
                fila[1]=rs.getString("nombre");
                fila[2]=rs.getString("apellidos");
                fila[4]=rs.getString("clubN");
                fila[3]=rs.getString("carrer");
                tabla.addRow(fila);
                
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
        return tabla;
    }
    
    public DefaultTableModel verAño(String idclub, String idaño){
        DefaultTableModel tabla = new DefaultTableModel();
        
        /*String consulta = "Select a.id, a.nombre, a.apellidos, c.nombre as clubN, ca.nombre as carrer from Alumno a, Club c, Solicitud s, Carrera ca, Facultad f "
                + "where a.id = s.alumno and c.id = s.club and a.carrera = ca.id and ca.facultad = f.id and c.nombre='"+idclub+"' and f.nombre='"+idcarrera+"'";
        */
        String consulta = "select a.id, a.nombre, a.apellidos, a.añocursando,c.nombre as clubN from Alumno a, Club c, Solicitud s where a.id = s.alumno and c.id = s.club and a.activo=1 and c.nombre='"+idclub+"' and a.añocursando='"+idaño+"' and s.estadosol = 'Aceptada'";
        ResultSet rs = null;
        Statement ps = null;
        Conexion conex = new Conexion();
        Connection conn = conex.obtenerConexion();
        try{
            
            ps = conn.createStatement();
            rs = conex.listarConsulta(consulta);
            String titulos[]={"ID","Nombre","Apellidos","Año","Club"};
            tabla.setColumnIdentifiers(titulos);
            while(rs.next()){
                Object fila[] = new Object[7];
                fila[0]=rs.getString("id");
                fila[1]=rs.getString("nombre");
                fila[2]=rs.getString("apellidos");
                fila[3]=rs.getString("añocursando");
                fila[4]=rs.getString("clubN");
                tabla.addRow(fila);
                
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
        return tabla;
    }
    
    public DefaultComboBoxModel llenarClub(){
        
        DefaultComboBoxModel cmbClub = new DefaultComboBoxModel();
        ResultSet rs = null;
        Statement ps = null;
        Conexion conex = new Conexion();
        Connection conn = conex.obtenerConexion();
        try{
            String consulta = "Select * From Club";
            ps = conn.createStatement();
            rs = conex.listarConsulta(consulta);
            
            while(rs.next()){
                String nombre = rs.getString("nombre");
                cmbClub.addElement(nombre);
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
        return cmbClub;
    }
    
    public DefaultComboBoxModel llenarFacultad(){
        
        DefaultComboBoxModel cmbFacultad = new DefaultComboBoxModel();
        ResultSet rs = null;
        Statement ps = null;
        Conexion conex = new Conexion();
        Connection conn = conex.obtenerConexion();
        try{
            String consulta = "Select * From Facultad";
            ps = conn.createStatement();
            rs = conex.listarConsulta(consulta);
            
            while(rs.next()){
                String nombre = rs.getString("nombre");
                cmbFacultad.addElement(nombre);
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
        return cmbFacultad;
    }
    
    public DefaultComboBoxModel llenarCarrera(){
        
        DefaultComboBoxModel cmbCarrera = new DefaultComboBoxModel();
        ResultSet rs = null;
        Statement ps = null;
        Conexion conex = new Conexion();
        Connection conn = conex.obtenerConexion();
        try{
            String consulta = "Select * From Carrera";
            ps = conn.createStatement();
            rs = conex.listarConsulta(consulta);
            
            while(rs.next()){
                String nombre = rs.getString("nombre");
                cmbCarrera.addElement(nombre);
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
        return cmbCarrera;
    }
    
   
    public DefaultListModel verRegistro(String nose){
        DefaultListModel lista = new DefaultListModel();
        String consulta = "Select * from Alumno inner join Solicitud on alumno.id = solicitud.alumno "
                + "inner join Club on club.id = solicitud.club where alumno.activo=1 and club.nombre='"+nose+"' and solicitud.estadosol = 'Aceptada'";
                    
         
        ResultSet rs = null;
        Statement ps = null;
        Conexion conex = new Conexion();
        Connection conn = conex.obtenerConexion();
        try{
            
            ps = conn.createStatement();
            rs = conex.listarConsulta(consulta);
            
            
                while(rs.next()){
                    String id = rs.getString("id");
                    lista.addElement(id);
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
        
        return lista;
    }
    
    public String calcularEstCarrera(String idClub, String idCarrera){
        String total=null;
        String consulta = "select count(*) as contar from Alumno a, Club c, Solicitud s, Carrera ca where a.id = s.alumno and c.id = s.club and a.carrera = ca.id and " +
                        "a.activo=1 and c.nombre='"+idClub+"' and ca.nombre='"+idCarrera+"' and s.estadosol = 'Aceptada'";
        
        ResultSet rs = null;
        Statement ps = null;
        Conexion conex = new Conexion();
        Connection conn = conex.obtenerConexion();
        
        try{
            
            ps = conn.createStatement();
            rs = conex.listarConsulta(consulta);
            
            if(rs.next()){
                String contar = rs.getString("contar");
                total=contar;
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
        return total;
    }
    
    public String calcularEstFaculta(String idClub, String idFacultad){
        String consulta = "Select count(*) as Contar from Alumno a, Club c, Facultad f, Carrera ca, Solicitud s "
                + "where a.id=s.alumno and s.club=c.id and a.carrera=ca.id and ca.facultad = f.id and a.activo=1 and c.nombre='"+idClub+"' and f.nombre='"+idFacultad+"' and s.estadosol = 'Aceptada'";
        String facultad=null;
        ResultSet rs = null;
        Statement ps = null;
        Conexion conex = new Conexion();
        Connection conn = conex.obtenerConexion();
        
        try{
            
            ps = conn.createStatement();
            rs = conex.listarConsulta(consulta);
            
            if(rs.next()){
                String contar = rs.getString("Contar");
                facultad = contar;
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
        return facultad;
    }
    
    public String calcularEstAño(String club, String año){
        String consulta = "select count(*) as anios from Alumno a, Club c, Solicitud s where a.id = s.alumno and c.id = s.club and a.activo=1 and c.nombre='"+club+"' and a.añocursando='"+año+"' and s.estadosol = 'Aceptada'";
        String total=null;
        ResultSet rs = null;
        Statement ps = null;
        Conexion conex = new Conexion();
        Connection conn = conex.obtenerConexion();
        try{
            
            ps = conn.createStatement();
            rs = conex.listarConsulta(consulta);
            
            while(rs.next()){
                String anio = rs.getString("anios");
                total = anio;
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
        return total;
    }
    
        public DefaultComboBoxModel llenarComboBoxCoordinacion(){
        
        DefaultComboBoxModel cmb = new DefaultComboBoxModel();
        ResultSet rs = null;
        Statement ps = null;
        Conexion conex = new Conexion();
        Connection conn = conex.obtenerConexion();
        try{
            String consulta = "Select * From coordinacion";
            ps = conn.createStatement();
            rs = conex.listarConsulta(consulta);
            
            while(rs.next()){
                String nombre = rs.getString("nombre");
                cmb.addElement(nombre);
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
        return cmb;
    }
    public String validarCorreoCoordinacion(String x){
        String consulta = "Select * from Coordinacion c, club cb where cb.cordinacion = c.id and cb.nombre='"+x+"'";
        String n=null;
        ResultSet rs = null;
        Statement ps = null;
        Conexion conex = new Conexion();
        Connection conn = conex.obtenerConexion();
        
        try{
            
            ps = conn.createStatement();
            rs = conex.listarConsulta(consulta);
            
            if(rs.next()){
                String c = rs.getString("email");
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
        
    public String validarPWDCoordinacion(String x){
        String consulta = "Select * from Coordinacion c, club cb where cb.cordinacion = c.id and cb.nombre= '"+x+"'";
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
