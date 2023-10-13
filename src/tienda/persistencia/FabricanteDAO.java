package tienda.persistencia;
import tienda.entidades.Fabricante;

public final class FabricanteDAO extends DAO{
    
    public void guardarFabricante(Fabricante fabricante) throws Exception{
        
        try {
            if (fabricante == null) {
                throw new Exception("Debe indicar un fabricante");
            }
            if(buscarFabricantePorCodigo(fabricante.getCodigo()) != null){
                throw new Exception("Ya existe un fabricante con ese codigo");
            }
            
            String sql = "INSERT INTO fabricante (codigo, nombre) "+
                    "VALUES ('" + fabricante.getCodigo() + "' , '" +
                    fabricante.getNombre() + "');" ;
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void modificarFabricante(Fabricante fabricante) throws Exception{
        try {
            if (fabricante == null) {
                throw new Exception("Debe indicar un fabricante");
            }
            
            String sql = "UPDATE fabricante "+
                    "SET nombre = '" + fabricante.getNombre() + "' "+
                    "WHERE codigo = '" + fabricante.getCodigo() + "';";
            
            insertarModificarEliminar(sql);
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminarFabricante(Fabricante fabricante) throws Exception{
        
        try {
            String sql = "DELETE FROM fabricante WHERE codigo = '" +
                    fabricante.getCodigo() + "';";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        }
        
    }
    
    public Fabricante buscarFabricantePorCodigo(int codigo) throws Exception{
        
        try {
            String sql = "SELECT * FROM fabricante " + 
                    "WHERE codigo = '" + codigo + "'; ";
            consultarBase(sql);
            
            Fabricante fabricante = null;
            
            while (resultado.next()) {
                
                fabricante = new Fabricante();
                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));
            }
            desconectarBase();
            return fabricante;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
        
    }
}
