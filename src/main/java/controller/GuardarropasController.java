package controller;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import model.Color;
import model.Guardarropa;
import model.Prenda;
import model.RepositorioGuardarropas;
import model.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class GuardarropasController implements WithSimplePersistenceUnit {

  public ModelAndView listar(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    modelo.put("anio", LocalDate.now().getYear());
    modelo.put("guardarropas", RepositorioGuardarropas.instance().obtenerTodos());
    return new ModelAndView(modelo, "guardarropas/index.html.hbs");
  }

  public ModelAndView obtener(Request request, Response response) {
    var id = Long.parseLong(request.params("id"));

    Map<String, Object> modelo = new HashMap<>();
    modelo.put("anio", LocalDate.now().getYear());
    modelo.put("guardarropas", RepositorioGuardarropas.instance().obtener(id));
    return new ModelAndView(modelo, "guardarropas/detalle.html.hbs");
  }
}
