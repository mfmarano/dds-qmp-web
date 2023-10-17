package controller;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import model.RepositorioGuardarropas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class PrendasController implements WithSimplePersistenceUnit {

  public ModelAndView obtener(Request request, Response response) {
    var id = Long.parseLong(request.params("id"));

    Map<String, Object> modelo = new HashMap<>();

    modelo.put("anio", LocalDate.now().getYear());
    modelo.put("sesionIniciada", request.session().attribute("user_id") != null);

    modelo.put("guardarropas", RepositorioGuardarropas.instance().obtener(id));
    return new ModelAndView(modelo, "prendas/detalle.html.hbs");
  }
}
