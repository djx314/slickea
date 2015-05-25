package org.xarcher.ea.test.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "myServant")
public class Servant {

  @Id
  @Column(name = "s_id")
  Long id;

  @Column(name = "true_name")
  protected String name;

  String master;

  String phantasm;

  public Servant(String name, String master, String phantasm) {
    this.name = name;
    this.master = master;
    this.phantasm = phantasm;
  }

  public Servant() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "s_master")
  public String getMaster() {
    return master;
  }

  public void setMaster(String master) {
    this.master = master;
  }

  public String getPhantasm() {
    return phantasm;
  }

  public void setPhantasm(String phantasm) {
    this.phantasm = phantasm;
  }

}