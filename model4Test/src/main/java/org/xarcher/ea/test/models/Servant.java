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
  long id;

  @Column(name = "true_name")
  protected String name;

  String master;

  String phantasm;

  @Column(name = "how_old")
  Integer age;

  public Servant(String name, String master, String phantasm, Integer age) {
    this.name = name;
    this.master = master;
    this.phantasm = phantasm;
    this.age = age;
  }

  public Servant() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
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

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }
}