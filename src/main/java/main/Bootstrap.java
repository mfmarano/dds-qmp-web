package main;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import model.Color;
import model.Guardarropa;
import model.Prenda;
import model.Usuario;

/**
 * Ejecutar antes de levantar el servidor por primera vez
 *
 * @author flbulgarelli
 */
public class Bootstrap implements WithSimplePersistenceUnit {

  public static void main(String[] args) {
    new Bootstrap().run();
  }

  public void run() {
    withTransaction(() -> {
      var pantalon = new Prenda("Pantalón negro", Color.NEGRO);

      var guardarropa = new Guardarropa("primavera");
      guardarropa.agregarPrenda(pantalon);

      var usuario = new Usuario("dani", "dani@dani.com");
      usuario.agregarGuardarropa(guardarropa);

      persist(usuario);
    });
  }
}
