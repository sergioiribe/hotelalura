package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="huespedes")
public class Huesped implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String nacionalidad;
    private String Telefono;

    // Campo relacionado con la entidad de reserva
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Numero_de_reserva")
    private Reserva reserva;

    // Constructor Vacio
    public Huesped() {}

    // Constructor con Parametros
    public Huesped(String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad, String telefono, Reserva reserva) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.Telefono = telefono;
        this.reserva = reserva;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}
