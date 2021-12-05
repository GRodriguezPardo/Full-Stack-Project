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
    String Port = System.getenv("PORT");
    JpaSchemaExport.execute("db", "schema.sql", true, true);
    Bootstrap.main(argv);
    Spark.port(Integer.decode(Port));
    DebugScreen.enableDebugScreen();
    Router.configure();
  }
}

