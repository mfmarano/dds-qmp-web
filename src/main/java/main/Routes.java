package main;

import controller.GuardarropasController;
import controller.HomeController;
import controller.LoginController;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import javax.persistence.PersistenceException;

public class Routes implements WithSimplePersistenceUnit {

  public static void main(String[] args) {
    new Routes().start();
  }

  public void start() {
    System.out.println("Iniciando servidor");

    new Bootstrap().run();

    Spark.port(8555);
    Spark.staticFileLocation("/public");

    HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
    HomeController homeController = new HomeController();
    GuardarropasController guardarropasController = new GuardarropasController();
    LoginController loginController = new LoginController();

    Spark.get("/", homeController::home, engine);

    Spark.get("/login", loginController::mostrarLogin, engine);
    Spark.post("/login", loginController::crearSesion);
    // TODO: adaptar para que sea Spark.delete(...);
    Spark.get("/logout", loginController::cerrarSesion);

    Spark.get("/guardarropas", guardarropasController::listar, engine);
    Spark.get("/guardarropas/:id", guardarropasController::obtener, engine);

    Spark.exception(PersistenceException.class, (e, request, response) -> {
      response.redirect("/500");
    });

    Spark.before((request, response) -> {
      entityManager().clear();

      // TODO: mejorar
      if (request.pathInfo().startsWith("/guardarropas") &&
          request.session().attribute("user_id")  == null) {
        response.redirect("/");
      }
    });
  }
}
