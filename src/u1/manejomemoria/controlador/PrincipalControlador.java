/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package u1.manejomemoria.controlador;

import u1.manejomemoria.modelo.Negocio;
import u1.manejomemoria.vistas.PrincipalVista;
import u1.manejomemoria.modelo.Persona;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;

/**
 *
 * @author Boris Perez
 */
public class PrincipalControlador {
    
    private Negocio negocio;
    private PrincipalVista frame;
    
    public PrincipalControlador(PrincipalVista frame){
        negocio = new Negocio();
        this.frame = frame;
        cargaInicial();
    }
    
    private void cargaInicial(){
       
        System.out.println("Carga inicial");
        
        String id = negocio.crearPersona("Boris", 15);
        actualizarCombos(id);
        
        id = negocio.crearPersona("Laura", 20);
        actualizarCombos(id);
        
        id = negocio.crearPersona("Pedro", 25);
        actualizarCombos(id);
        
    }
    
    public void crearObjeto(){
        
        int edad = Integer.parseInt(frame.getTxtCrearEdad().getText());
        
        String id = negocio.crearPersona(frame.getTxtCrearNombre().getText(), edad);
        frame.getTxtCrearID().setText(id);
        
        actualizarCombos(id);   
        
    }
    
    public void limpiarCrearObjeto(){
        frame.getTxtCrearNombre().setText("");
        frame.getTxtCrearEdad().setText("");
        frame.getTxtCrearID().setText("");
    }
    
    public void limpiarClonarObjeto(){
        frame.getTxtClonarIDDest().setText("");
    }
    
    public void cargarObjeto(){
        
        String sIndice = frame.getCmbImprimirID().getSelectedItem().toString();
        int indice = Integer.parseInt(sIndice);
        
        Persona p = negocio.cargarPersonaIndice(indice);
        
        if(p!=null){
            frame.getTxtCargarNombre().setText(p.getNombre());
            frame.getTxtCargarEdad().setText(""+p.getEdad()); // Se concatena para pasarlo a String
        }else
            System.err.println("El objeto es null");
             
    }
    
    
    
    public void cargarObjetoActualizar(){
        
        Persona p = negocio.cargarPersona(frame.getCmbActualizarID().getSelectedItem().toString());
        
        if(p!=null){
            frame.getTxtActualizarNombre().setText(p.getNombre());
            frame.getTxtActualizarEdad().setText(""+p.getEdad()); // Se concatena para pasarlo a String
        }else
            System.err.println("El objeto es null");
             
    }
    
    public void actualizar(){
        int edad = Integer.parseInt(frame.getTxtActualizarEdad().getText());     
        String id = frame.getCmbActualizarID().getSelectedItem().toString();
        negocio.actualizarPersona(id, frame.getTxtActualizarNombre().getText(), edad);
        
        frame.getTxtActualizarEdad().setText("");
        frame.getTxtActualizarNombre().setText("");
    }
    
    public void clonarObjeto(){
        
        Persona p = negocio.cargarPersona(frame.getCmbClonarIDSource().getSelectedItem().toString());
        
        if(p!=null){
            
            String id = negocio.crearPersona(p.getNombre(), p.getEdad());
            frame.getTxtClonarIDDest().setText(id);
            
            actualizarCombos(id);    
            
        }else
            System.err.println("El objeto es null");
             
    }
    
    private void actualizarCombos(String id){
        
        System.out.println("Actualizando combos");
        
        DefaultComboBoxModel<String> modelo2 = (DefaultComboBoxModel<String>)frame.getCmbClonarIDSource().getModel();
        modelo2.addElement(id);
        
        DefaultComboBoxModel<String> modelo3 = (DefaultComboBoxModel<String>)frame.getCmbActualizarID().getModel();
        modelo3.addElement(id);
        
        DefaultComboBoxModel<String> modelo4 = (DefaultComboBoxModel<String>)frame.getCmbIgualarID().getModel();
        modelo4.addElement(id);        
        
        int totalPersonas = negocio.obtenerCantidadPersonas();
        if(totalPersonas>0){
            DefaultComboBoxModel<String> modelo1 = (DefaultComboBoxModel<String>)frame.getCmbImprimirID().getModel();
            modelo1.removeAllElements();
            for (int i = 0; i < totalPersonas; i++) {
                modelo1.addElement(""+i);
            }
        }
        
    }

    public void igualarObjeto() {
        String id = frame.getCmbIgualarID().getSelectedItem().toString();
        negocio.igualarPersona(id);
        actualizarCombos(id);
       
    }
    
}
