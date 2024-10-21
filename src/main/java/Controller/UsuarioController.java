package Controller;

import dao.UsuarioDao;
import modelo.Usuario;
import utils.JPAUtils;

import javax.persistence.EntityManager;

public class UsuarioController {

    private UsuarioDao usuarioDao;

    private EntityManager em;

    public UsuarioController() {
        this.em = JPAUtils.getEntityManager();
        this.usuarioDao = new UsuarioDao(em);
    }

    public void nuevoUsuario(String nombre, String email, String password) {
        this.usuarioDao.nuevoUsuario(nombre, email, password);
    }

    public boolean acceder(String email, String password) {
        return this.usuarioDao.acceder(email, password);

    }

    public Usuario obtenerUsuario(String email, String password) {
        return this.usuarioDao.obtenerUsuario(email, password);
    }
}
