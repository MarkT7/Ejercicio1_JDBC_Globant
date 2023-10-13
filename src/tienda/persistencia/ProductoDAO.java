package tienda.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import tienda.entidades.Producto;

public final class ProductoDAO extends DAO{
    
    public void guardarProducto(Producto producto) throws Exception{
        
        try {
            if (producto == null) {
                throw new Exception("Debe indicar un producto");
            }
            String sql = "INSERT INTO producto (codigo, nombre, precio, codigo_fabricante) "+
                "VALUES ('" + producto.getCodigo() + "' , '" +
                producto.getNombre() + "' , '" + 
                producto.getPrecio() + "' , '" +
                producto.getCodigoFabricante() + "');" ;
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void modificarProducto(Producto producto) throws Exception{
        
        try {
            if (producto == null) {
                throw new Exception("Debe indicar un producto");
            }
            
            String sql = "UPDATE producto "+
                    "SET nombre = '" + producto.getNombre() + "', "+
                    "precio = " + producto.getPrecio() + ", "+
                    "codigo_fabricante = "+ producto.getCodigoFabricante()+" "+
                    "WHERE codigo = "+producto.getCodigo()+";";
            
            insertarModificarEliminar(sql);
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminarProducto(Producto producto) throws Exception{
        
        try {
            String sql = "DELETE FROM producto WHERE codigo = '" +
                    producto.getCodigo() + "';";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        }
        
    }
    
    public Producto buscarProductoPorCodigo(int codigo) throws Exception{
        
        try {
            String sql = "SELECT * FROM producto "+
                    "WHERE codigo = '" + codigo +"'; ";
            consultarBase(sql);
            
            Producto producto = null; 
            
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
            }
            
            desconectarBase();
            return producto;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }
    
    public Collection<Producto> listarProductos() throws Exception{
        
        try {
            
            String sql = "SELECT * FROM producto";
            consultarBase(sql);
            
            Producto producto = null;
            
            Collection<Producto> productos = new ArrayList();
            
            while(resultado.next()){
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigo(resultado.getInt(4));
                productos.add(producto);
            }
            
            desconectarBase();
            return productos;
            
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw e;
        }
    }
    
    public Collection<Producto> listarPortatiles() throws Exception{
        
        try {
            
            String sql = "SELECT * FROM producto WHERE nombre LIKE '%portátil%'";
            consultarBase(sql);
            
            Producto portatil = null;
            
            Collection<Producto> portatiles = new ArrayList();
            
            while(resultado.next()){
                portatil = new Producto();
                portatil.setCodigo(resultado.getInt(1));
                portatil.setNombre(resultado.getString(2));
                portatil.setPrecio(resultado.getDouble(3));
                portatil.setCodigo(resultado.getInt(4));
                portatiles.add(portatil);
            }
            
            desconectarBase();
            return portatiles;
            
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw e;
        }
    }
    
    public Producto listarBarato() throws Exception{
        
        try {
            String sql = "SELECT nombre, precio FROM producto WHERE precio = (" +
            "SELECT MIN(precio) FROM producto );";
            consultarBase(sql);
            
            Producto barato = null;
            
            while(resultado.next()){
                barato = new Producto();
                barato.setNombre(resultado.getString(1));
                barato.setPrecio(resultado.getDouble(2));
            }

            desconectarBase();
            return barato;
            
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw e;
        }
    }
}
