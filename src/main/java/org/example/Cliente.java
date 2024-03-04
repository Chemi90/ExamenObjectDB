package org.example;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Data
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Long totalDeVentas = 0L; // Valor por defecto
    private String estado = "activo"; // Valor por defecto

    // Constructor completo
    public Cliente(String nombre, long totalDeVentas, String estado) {
        this.nombre = nombre;
        this.totalDeVentas = totalDeVentas;
        this.estado = estado;
    }

    // Constructor que solo recibe el nombre, los otros campos toman valores por defecto
    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    // Constructor por defecto necesario para JPA
    public Cliente() {
    }
}
