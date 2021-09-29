package persistence;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class PersistenceId {

  @Id
  @GeneratedValue
  private long id;

  public long getId() {
    return id;
  }

}
