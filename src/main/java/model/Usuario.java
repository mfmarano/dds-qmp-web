package model;

import org.apache.commons.codec.digest.DigestUtils;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "Usuarios")
public class Usuario extends BaseEntity {

  @Column
  private String nombre;

  @Column
  private String hashContrasenia;

  @Column
  private String email;

  @OneToMany(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "usuario_id")
  private Collection<Guardarropa> guardarropas = new ArrayList<>();

  public Usuario() { }

  public Usuario(String nombre, String email, String contrasenia) {
    this.nombre = nombre;
    this.hashContrasenia = DigestUtils.sha256Hex(contrasenia);
    this.email = email;
  }

  public void agregarGuardarropa(Guardarropa guardarropa) {
    this.guardarropas.add(guardarropa);
  }

  public Collection<Guardarropa> getGuardarropas() {
    return guardarropas;
  }

}
