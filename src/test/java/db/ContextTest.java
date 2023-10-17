package db;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.flbulgarelli.jpa.extras.test.SimplePersistenceTest;
import model.Color;
import model.Guardarropa;
import model.Prenda;
import model.RepositorioGuardarropas;
import model.Usuario;
import org.junit.jupiter.api.Test;

public class ContextTest implements SimplePersistenceTest {

  @Test
  void contextUp() {
    var pantalon = new Prenda("Pantalón negro", Color.NEGRO);

    var guardarropa = new Guardarropa("primavera");
    guardarropa.agregarPrenda(pantalon);

    var usuario = new Usuario("dani", "dani@dani.com", "123456");
    usuario.agregarGuardarropa(guardarropa);

    persist(usuario);

    var guardarropas = RepositorioGuardarropas.instance().obtenerTodos();
    assertEquals(1, guardarropas.size());
  }


}
