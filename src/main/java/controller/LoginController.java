package controller;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import model.RepositorioUsuarios;
import model.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class LoginController implements WithSimplePersistenceUnit {
  public ModelAndView mostrarLogin(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();

    modelo.put("anio", LocalDate.now().getYear());
    modelo.put("sesionIniciada", request.session().attribute("user_id") != null);

    return new ModelAndView(modelo, "formulario-login.html.hbs");
  }

  public Void crearSesion(Request request, Response response) {
    try {
      Usuario usuario = RepositorioUsuarios.instance().buscarPorUsuarioYContrasenia(
          request.queryParams("nombre"),
          request.queryParams("contrasenia"));

      request.session().attribute("user_id", usuario.getId());
      response.redirect("/");
      return null;
    } catch (Exception e) {
      response.redirect("/login");
      return null;
    }
  }

  public Void cerrarSesion(Request request, Response response) {
    request.session().attribute("user_id", null);
    response.redirect("/");
    return null;
  }
}