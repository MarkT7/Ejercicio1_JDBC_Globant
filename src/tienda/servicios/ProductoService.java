package tienda.servicios;

import java.util.Collection;
import tienda.entidades.Producto;
import tienda.persistencia.ProductoDAO;

public class ProductoService {
    
    public ProductoDAO dao;
    
    public ProductoService(){
        this.dao = new ProductoDAO();
    }
    
    public Collection<Producto> listarProductos() throws Exception{
        
        try {
            
            Collection<Producto> productos = dao.listarProductos();
            return productos;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Collection<Producto> listarPortatiles() throws Exception{
        
        try {
            
            Collection<Producto> portatiles = dao.listarPortatiles();
            return portatiles;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Producto listarBarato() throws Exception{
        
        try {
            
            Producto barato = dao.listarBarato();
            return barato;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void crearProducto(int codigo, String nombre, double precio, int codigoFabricante) throws Exception{
        
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre");
            }
            if (buscarProductoPorCodigo(codigo) != null) {
                throw new Exception("Ya existe un producto con el codigo indicado " + codigo);
            }
            
            Producto producto = new Producto();
            producto.setCodigo(codigo);
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCodigoFabricante(codigoFabricante);
            dao.guardarProducto(producto);
            
        } catch (Exception e) {
            throw e;
        }
    }

    private Object buscarProductoPorCodigo(int codigo) throws Exception {
        
        try {
            Producto producto = dao.buscarProductoPorCodigo(codigo);
            return producto;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void editarProducto(int codigo, String nombre, double precio, int codigoFabricante) throws Exception{
        
        try {
            Producto producto = new Producto();
            producto.setCodigo(codigo);
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCodigoFabricante(codigoFabricante);
            
            dao.modificarProducto(producto);
        } catch (Exception e) {
            throw e;
        }
    }
}
