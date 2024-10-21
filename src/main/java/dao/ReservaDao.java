package dao;

import modelo.Reserva;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReservaDao {

    private EntityManager em;

    public ReservaDao(EntityManager em){this.em = em;}

    public Reserva guardar(LocalDate fechaEntrada, LocalDate fechaSalida, Double valor, String formaDePago){
        Reserva reserva = new Reserva(fechaEntrada,fechaSalida,valor,formaDePago);
        em.getTransaction().begin();
        this.em.persist(reserva);
        em.getTransaction().commit();
        em.close();
        return reserva;
    }


    public DefaultTableModel listar(){
        em.getTransaction().begin();
        List<Reserva> listaReserva = em.createQuery("SELECT e FROM Reserva as e",Reserva.class).getResultList();
        Object[][] data = new Object[listaReserva.size()][];
        int i = 0;
        for(Reserva reserva : listaReserva){
            data[i] = new Object[]{reserva.getId(),reserva.getFechaEntrada(),reserva.getFechaSalida(),
                    reserva.getValor(),reserva.getFormaDePago()};
            i++;
        }
        String [] columnas = {"Numero de reserva", "Fecha Check In", "Fecha Check Out", "Valor", "Forma de pago"};
        DefaultTableModel modelo = new DefaultTableModel(data,columnas);
        em.close();
        return modelo;
    }


    public void editar(JTable tablareservas){
    Integer filaSeleccionada = tablareservas.getSelectedRow();
    Long id = Long.parseLong(tablareservas.getValueAt(filaSeleccionada,0).toString());
    String fechaChekIn = tablareservas.getValueAt(filaSeleccionada,1).toString();
    String fechaChekOut = tablareservas.getValueAt(filaSeleccionada,2).toString();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaChekInModificada = LocalDate.parse(fechaChekIn,dtf);
        LocalDate fechaChekOutModificada = LocalDate.parse(fechaChekOut,dtf);
        Double valorModificado = Double.parseDouble(tablareservas.getValueAt(filaSeleccionada,3).toString());
        String FormaPago = tablareservas.getValueAt(filaSeleccionada,4).toString();

        em.getTransaction().begin();
        Reserva reserva = em.find(Reserva.class,id);
        if(reserva!=null){
            reserva.setFechaEntrada(fechaChekInModificada);
            reserva.setFechaSalida(fechaChekOutModificada);
            reserva.setValor(valorModificado);
            reserva.setFormaDePago(FormaPago);
            em.merge(reserva);
            em.getTransaction().commit();
    }
    else{
            em.getTransaction().rollback();
        }
        em.close();
    }


    public void remover(Long id) {
        Reserva reserva = em.find(Reserva.class,id);
        if (reserva != null){
            em.getTransaction().begin();
            em.remove(reserva);
            em.getTransaction().commit();
        }
            em.close();
    }

    public DefaultTableModel buscarReservasPorApellidoHuesped(String apellido) {
        String jpql = "SELECT r FROM Reserva r JOIN r.huespedes h WHERE h.apellido = :apellido";
        TypedQuery<Reserva> query = em.createQuery(jpql, Reserva.class);
        query.setParameter("apellido", apellido);

        List<Reserva> listaReserva = query.getResultList();
        Object[][] data = new Object[listaReserva.size()][];
        int i = 0;
        for (Reserva reserva : listaReserva) {
            data[i] = new Object[] { reserva.getId(), reserva.getFechaEntrada(), reserva.getFechaSalida(),
                    reserva.getValor(), reserva.getFormaDePago() };
            i++;
        }
        String[] columnas = { "Número de reserva", "Fecha Check In", "Fecha Check Out", "valor", "forma de pago" };
        DefaultTableModel modelo = new DefaultTableModel(data, columnas);
        em.close();
        return modelo;
    }

    public DefaultTableModel buscarReservaPorId(Long id) {
        em.getTransaction().begin();
        List<Reserva> listaReserva = em.createQuery("SELECT r FROM Reserva r WHERE r.id = :id", Reserva.class)
                .setParameter("id", id).getResultList();
        Object[][] data = new Object[listaReserva.size()][];
        int i = 0;
        for (Reserva reserva : listaReserva) {
            data[i] = new Object[] { reserva.getId(), reserva.getFechaEntrada(), reserva.getFechaSalida(),
                    reserva.getValor(), reserva.getFormaDePago() };
            i++;
        }
        String[] columnas = { "Número de reserva", "Fecha Check In", "Fecha Check Out", "valor", "forma de pago" };
        DefaultTableModel modelo = new DefaultTableModel(data, columnas);
        em.close();
        return modelo;
    }

}
