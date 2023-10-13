package tienda.servicios;

import tienda.entidades.Fabricante;
import tienda.persistencia.FabricanteDAO;

public class FabricanteService {
    
    public FabricanteDAO dao;
    
    public FabricanteService(){
        this.dao = new FabricanteDAO();
    }
    
    public void crearFabricante(int codigo, String nombre) throws Exception{
        
        try {
            Fabricante fabricante = new Fabricante();
            fabricante.setCodigo(codigo);
            fabricante.setNombre(nombre);
            
            dao.guardarFabricante(fabricante);
        } catch (Exception e) {
            throw e;
        }
    }
}
