package br.ufsm.csi.app.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "tb_role")
public class RoleModel implements GrantedAuthority {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID roleId;

  @Enumerated(EnumType.STRING)
  private RoleName roleName;

  @Override
  public String getAuthority() {
    return this.roleName.toString();
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public UUID getRoleId() {
    return roleId;
  }

  public void setRoleId(UUID roleId) {
    this.roleId = roleId;
  }

  public RoleName getRoleName() {
    return roleName;
  }

  public void setRoleName(RoleName roleName) {
    this.roleName = roleName;
  }

}
