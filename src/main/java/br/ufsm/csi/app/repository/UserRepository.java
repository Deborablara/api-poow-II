package br.ufsm.csi.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufsm.csi.app.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
  Optional<UserModel> findByUsername(String username);

  @Query(value = "select u.username, r.role_name, u.password, u.user_id " +
      "from tb_users_roles as us_roles " +
      "inner join tb_user u on us_roles.user_id = u.user_id " +
      "inner join tb_role r on us_roles.role_id = r.role_id " +
      "where us_roles.user_id = :userId ", nativeQuery = true)
  UserModel getUserWithAutorities(Long userId);
}
