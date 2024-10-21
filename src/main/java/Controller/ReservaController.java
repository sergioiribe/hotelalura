package Controller;

import dao.ReservaDao;
import modelo.Reserva;
import utils.JPAUtils;

import javax.persistence.EntityManager;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;

public class ReservaController {

    private ReservaDao reservaDao;

    private EntityManager em;

    public ReservaController(){
        this.em = JPAUtils.getEntityManager();
        this.reservaDao = new ReservaDao(em);
    }

    public Reserva guardar(LocalDate fechaIngreso, LocalDate fechaSalida, Double valor, String formaDePago){
        return reservaDao.guardar(fechaIngreso, fechaSalida, valor, formaDePago);
    }

    public DefaultTableModel listar(){
        return reservaDao.listar();
    }

    public void modificar(javax.swing.JTable tablaReservas){
        reservaDao.editar(tablaReservas);
    }

    public void eliminar(Long id){
        reservaDao.remover(id);
    }

    public DefaultTableModel buscarPorApellido(String apellido) {
        return reservaDao.buscarReservasPorApellidoHuesped(apellido);
    }

    public DefaultTableModel buscarPorId(Long id) {
        return reservaDao.buscarReservaPorId(id);
    }
}
