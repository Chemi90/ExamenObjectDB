package org.example;

public class App {
    public static void main(String[] args) {
        GestorDB gestor = new GestorDB();
/*
        // Crear y añadir alumnos con datos completos
        gestor.insertarCliente(new Cliente("Cliente 1", 1500L, "activo"));
        gestor.insertarCliente(new Cliente("Cliente 2", 300L, "activo"));
        gestor.insertarCliente(new Cliente("Cliente 3", 1200L, "inactivo"));
        gestor.insertarCliente(new Cliente("Cliente 4", 0L, "inactivo"));
        gestor.insertarCliente(new Cliente("Cliente 5", 500L, "activo"));
        // Añadir más alumnos si es necesario
*/
        // Luego llamar a los métodos para listar, etc.
        gestor.getCliente(1L);
        gestor.getCliente(2L);
        gestor.getCliente(3L);
        gestor.getCliente(4L);
        gestor.getCliente(5L);

        // Llamar a los métodos para obtener las estadísticas
        gestor.estadisticas();

        // Llamadas a métodos individuales (si es necesario comprobar su funcionamiento individualmente)
        Long totalVentas = gestor.obtenerTotalVentas();
        System.out.println("Total de ventas: " + totalVentas);

        Double promedioVentasActivos = gestor.obtenerPromedioVentasActivos();
        System.out.println("Promedio de ventas de los clientes activos: " + promedioVentasActivos);

        Long clientesInactivosConVentas = gestor.contarClientesInactivosConVentas();
        System.out.println("Clientes inactivos con ventas mayores a 0: " + clientesInactivosConVentas);

        // Ejemplo de cómo listar los mejores clientes
        System.out.println("Listado de los mejores clientes con ventas mayores a 500:");
        gestor.listarMejoresClientes(500L); // Suponiendo que '500' es el umbral de ventas para los mejores clientes


        // Cerrar la conexión con la base de datos
        gestor.close();
    }
}