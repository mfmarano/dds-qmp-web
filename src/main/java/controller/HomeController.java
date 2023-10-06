package controller;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class HomeController implements WithSimplePersistenceUnit {

  public ModelAndView home(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    modelo.put("anio", LocalDate.now().getYear());
    return new ModelAndView(modelo, "index.html.hbs");
  }
}
