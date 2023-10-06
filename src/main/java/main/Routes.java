package main;

import controller.GuardarropasController;
import controller.HomeController;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import javax.persistence.PersistenceException;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Routes implements WithSimplePersistenceUnit {

  public static void main(String[] args) {
    new Routes().start();
  }

  public void start() {
    System.out.println("Iniciando servidor");

    Bootstrap bootstrap = new Bootstrap();
    bootstrap.run();

    Spark.port(8555);
    Spark.staticFileLocation("/public");

    HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

    HomeController homeController = new HomeController();
    Spark.get("/", homeController::home, engine);

    GuardarropasController guardarropasController = new GuardarropasController();
    Spark.get("/guardarropas", guardarropasController::listar, engine);
    Spark.get("/guardarropas/:id", guardarropasController::obtener, engine);

    Spark.before((request, response) -> {
      entityManager().clear();
    });
  }
}
