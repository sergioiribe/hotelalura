package dao;

import modelo.Huesped;
import modelo.Reserva;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HuespedDao {

    private EntityManager em;

    public HuespedDao(EntityManager em) {this.em = em;}

    public void guardar(String nombre, String apellido, LocalDate fechaDeNacimiento, String nacionalidad,
                        String telefono, Reserva reserva) {
        Huesped huesped = new Huesped(nombre, apellido, fechaDeNacimiento, nacionalidad, telefono, reserva);
        em.getTransaction().begin();
        this.em.persist(huesped);
        em.getTransaction().commit();
        em.close();
    }

    public DefaultTableModel listar(){
        em.getTransaction().begin();
        List<Huesped> listaHuespedes = em.createQuery("SELECT e FROM Huesped as e", Huesped.class).getResultList();
        Object[][] data = new Object[listaHuespedes.size()][];
        int i = 0;
        for(Huesped huesped : listaHuespedes){
            data[i] = new Object[] {huesped.getId(), huesped.getNombre(), huesped.getApellido(), huesped.getFechaNacimiento(),
                    huesped.getNacionalidad(), huesped.getTelefono(), huesped.getReserva().getId()};
            i++;
        }
        String[] columnas = { "Número de Huesped", "Nombre", "Apellido", "Fecha de Nacimiento", "Nacionalidad",
                "Telefono", "Número de Reserva" };
        DefaultTableModel modelo = new DefaultTableModel(data, columnas);
        em.close();
        return modelo;
    }


    public void actualizar(JTable tablaHuespedes) {
        Integer filaSeleccionada = tablaHuespedes.getSelectedRow();
        Long id = (Long) tablaHuespedes.getValueAt(filaSeleccionada, 0);
        String nombreModificado = tablaHuespedes.getValueAt(filaSeleccionada, 1).toString();
        String apellidoModificado = tablaHuespedes.getValueAt(filaSeleccionada, 2).toString();
        String fechaNacimiento = tablaHuespedes.getValueAt(filaSeleccionada, 3).toString();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNacimientoModificada = LocalDate.parse(fechaNacimiento, dtf);
        String nacionalidadModificada = tablaHuespedes.getValueAt(filaSeleccionada, 4).toString();
        String Telefono = tablaHuespedes.getValueAt(filaSeleccionada, 5).toString();

        em.getTransaction().begin();
        Huesped huesped = em.find(Huesped.class, id);
        if (huesped != null) {
            huesped.setNombre(nombreModificado);
            huesped.setApellido(apellidoModificado);
            huesped.setFechaNacimiento(fechaNacimientoModificada);
            huesped.setNacionalidad(nacionalidadModificada);
            huesped.setTelefono(Telefono);
            em.merge(huesped);
            em.getTransaction().commit();
        } else {
            em.getTransaction().rollback();
        }

        em.close();
    }

    public void remover(Long id) {
        Huesped huesped = em.find(Huesped.class, id);
        if (huesped != null) {
            em.getTransaction().begin();
            em.remove(huesped);
            em.getTransaction().commit();
        }
        em.close();
    }

    public DefaultTableModel buscarPorNumeroDeReservaOApellido(Long NumeroDeReserva, String apellido) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Huesped> query = builder.createQuery(Huesped.class);
        Root<Huesped> from = query.from(Huesped.class);

        Predicate filtro = builder.and();

        if (apellido != null && !apellido.trim().isEmpty()) {
            filtro = builder.and(filtro, builder.equal(from.get("apellido"), apellido));
        }
        if (NumeroDeReserva != null && !(NumeroDeReserva == 0)) {
            filtro = builder.and(filtro, builder.equal(from.get("reserva"), NumeroDeReserva));
        }

        query = query.where(filtro);

        em.getTransaction().begin();
        List<Huesped> listaHuespedes = em.createQuery(query).getResultList();

        Object[][] data = new Object[listaHuespedes.size()][];
        int i = 0;
        for (Huesped huesped : listaHuespedes) {
            data[i] = new Object[] { huesped.getId(), huesped.getNombre(), huesped.getApellido(),
                    huesped.getFechaNacimiento(), huesped.getNacionalidad(), huesped.getTelefono(),
                    huesped.getReserva().getId() };
            i++;
        }
        String[] columnas = { "Número de Huesped", "Nombre", "Apellido", "Fecha de Nacimiento", "Nacionalidad",
                "Telefono", "Número de Reserva" };
        DefaultTableModel modelo = new DefaultTableModel(data, columnas);
        em.close();

        return modelo;
    }
}
