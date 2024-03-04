package org.example;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class GestorDB {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("clientes.odb");
    private EntityManager em = emf.createEntityManager();

    public void insertarCliente(Cliente cliente) {
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
    }

    public Cliente getCliente(Long id) {
        Cliente cliente = em.find(Cliente.class, id);
        if (cliente != null) {
            System.out.println("Cliente encontrado: " + cliente.toString());
        }
        return cliente;
    }


    public void listarMejoresClientes(Long cantidad) {
        TypedQuery<Cliente> query = em.createQuery(
                "SELECT c FROM Cliente c WHERE c.estado = 'activo' AND c.totalDeVentas > :cantidad", Cliente.class);
        query.setParameter("cantidad", cantidad);
        List<Cliente> resultados = query.getResultList();

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron clientes que cumplan con los criterios.");
        } else {
            System.out.println("Mejores clientes con ventas mayores a " + cantidad + ":");
            for (Cliente cliente : resultados) {
                System.out.println("Cliente [ID=" + cliente.getId() + ", Nombre=" + cliente.getNombre() + ", Total de Ventas=" + cliente.getTotalDeVentas() + ", Estado=" + cliente.getEstado() + "]");
            }
        }
    }

    public void estadisticas() {
        em.getTransaction().begin();
        Long totalVentas = em.createQuery("SELECT SUM(c.totalDeVentas) FROM Cliente c", Long.class).getSingleResult();
        Double promedioVentasActivos = em.createQuery("SELECT AVG(c.totalDeVentas) FROM Cliente c WHERE c.estado = 'activo'", Double.class).getSingleResult();
        Long clientesInactivosConVentas = em.createQuery("SELECT COUNT(c) FROM Cliente c WHERE c.estado = 'inactivo' AND c.totalDeVentas > 0", Long.class).getSingleResult();

        em.getTransaction().commit();

        System.out.println("Estadísticas:");
        System.out.println("Total de ventas entre todos los clientes: " + totalVentas);
        System.out.println("Promedio de ventas de los clientes activos: " + promedioVentasActivos);
        System.out.println("Cantidad de clientes inactivos con ventas mayor a 0: " + clientesInactivosConVentas);
    }

    public Long obtenerTotalVentas() {
        Long totalVentas = em.createQuery("SELECT SUM(c.totalDeVentas) FROM Cliente c", Long.class)
                .getSingleResult();
        return totalVentas;
    }

    public Double obtenerPromedioVentasActivos() {
        Double promedioVentasActivos = em.createQuery("SELECT AVG(c.totalDeVentas) FROM Cliente c WHERE c.estado = 'activo'", Double.class)
                .getSingleResult();
        return promedioVentasActivos;
    }

    public Long contarClientesInactivosConVentas() {
        Long clientesInactivosConVentas = em.createQuery("SELECT COUNT(c) FROM Cliente c WHERE c.estado = 'inactivo' AND c.totalDeVentas > 0", Long.class)
                .getSingleResult();
        return clientesInactivosConVentas;
    }

    public void close() {
        if (em != null && em.isOpen()) {
            em.close();
        }
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
