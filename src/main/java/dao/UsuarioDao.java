package dao;

import modelo.Usuario;

import javax.persistence.EntityManager;

public class UsuarioDao {
    private EntityManager em;

    public UsuarioDao(EntityManager em){this.em = em;}

    //Crea metodo para guardar un usuario nuevo en la base de datos
    public void nuevoUsuario(String nombre, String email, String password){
        Usuario usuario = new Usuario(nombre, email, password);
        em.getTransaction().begin();
        this.em.persist(usuario);
        em.getTransaction().commit();
        em.close();
    }

    //Metodo para verificar las credenciales del usuario
    public boolean acceder(String email, String password){
        String jpql = "SELECT u FROM Usuario u WHERE u.email = :email";
        em.getTransaction().begin();
        Usuario usuario = em.createQuery(jpql, Usuario.class).setParameter("email", email).getSingleResult();
        em.close();
        return usuario != null && usuario.getPassword().equals(password);
    }

    //Metodo para obtener un usuario a partir de su email y contraseña
    public Usuario obtenerUsuario(String email, String password){
        em.getTransaction().begin();
        String jpql = "SELECT u FROM Usuario u WHERE u.email = :email";
        Usuario usuario = em.createQuery(jpql, Usuario.class).setParameter("email", email).getSingleResult();
        em.close();
        return usuario;
    }
}
