package br.ufsm.csi.app.forms;

public class UserForm {
  private String name;
  private String password;
  private Long role_id;

  public UserForm(String name, String password, Long role_id) {
    this.name = name;
    this.password = password;
    this.role_id = role_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Long getRole_id() {
    return role_id;
  }

  public void setRole_id(Long role_id) {
    this.role_id = role_id;
  }
}
