import org.uqbarproject.jpa.java8.extras.export.JpaSchemaExport;

/** Application. Es la clase main para generar el Schema.
 *
 */
public class Application {
  public static void main(String[] argv) throws Exception {
    JpaSchemaExport.execute("db", "schema.sql", true, true);
  }
}

