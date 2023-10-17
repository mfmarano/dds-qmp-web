package model;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import org.apache.commons.codec.digest.DigestUtils;

public class RepositorioUsuarios implements WithSimplePersistenceUnit {

  private static final RepositorioUsuarios INSTANCE = new RepositorioUsuarios();

  public static RepositorioUsuarios instance() {
    return INSTANCE;
  }

  public Usuario obtener(long id) {
    return entityManager().find(Usuario.class, id);
  }

  public Usuario buscarPorUsuarioYContrasenia(String nombre, String contrasenia) {
    return entityManager()
        .createQuery("from Usuario where nombre = :nombre and hashContrasenia = :hashContrasenia", Usuario.class)
        .setParameter("nombre", nombre)
        .setParameter("hashContrasenia", DigestUtils.sha256Hex(contrasenia))
        .getResultList()
        .get(0);
  }
}