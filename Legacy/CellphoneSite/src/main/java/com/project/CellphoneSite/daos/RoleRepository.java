package com.project.CellphoneSite.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.CellphoneSite.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	@Query("SELECT role FROM Role role WHERE role.id = :id")
	public Role getRoleById(@Param("id") Integer id);
}
