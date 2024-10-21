package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="reservas")
public class Reserva implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private Double valor;
    private String formaDePago;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL)
    private List<Huesped> huespedes;

    public Reserva() {}

    public Reserva(LocalDate fechaEntrada, LocalDate fechaSalida, Double valor, String formaDePago) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valor = valor;
        this.formaDePago = formaDePago;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }
}
