package persistence;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PersistenceId {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  protected Long id;

  public long getId() {
    return id;
  }

}
