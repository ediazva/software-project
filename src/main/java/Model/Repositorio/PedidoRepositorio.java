package Model.Repositorio;

import Model.Dominio.Pedidos.Pedido;

/**
 * 
 */
public class PedidoRepositorio {

    /**
     * Default constructor
     */
    public PedidoRepositorio() {
    }

    /**
     * @param pedido 
     * @return
     */
    public void crear(PedidoRepositorio pedido) {
        // TODO implement here
        return null;
    }

    /**
     * @param id 
     * @return
     */
    public Pedido buscar(Long id) throws Exception {
        // TODO implement here
        throw  new Exception("Pedido no encontrado");
    }

    /**
     * @param id 
     * @return
     */
    public void eliminar(int id) {
        // TODO implement here
        return null;
    }

    /**
     * @param id 
     * @param pedido 
     * @return
     */
    public void actualizar(int id, PedidoRepositorio pedido) {
        // TODO implement here
        return null;
    }

}