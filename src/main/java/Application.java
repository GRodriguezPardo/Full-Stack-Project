import org.uqbarproject.jpa.java8.extras.export.JpaSchemaExport;
import server.Bootstrap;
import server.Router;
import spark.Spark;
import spark.debug.DebugScreen;

/**
 * Application. Es la clase main para generar el Schema.
 */
public class Application {
  public static void main(String[] argv) throws Exception {
    JpaSchemaExport.execute("db", "schema.sql", true, true);
    Bootstrap.main(argv);
    Spark.port(8080);
    DebugScreen.enableDebugScreen();
    Router.configure();
  }
}

