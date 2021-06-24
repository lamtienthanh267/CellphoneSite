package com.project.admin.users.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.models.entities.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
	@Query("SELECT profile FROM UserProfile profile WHERE profile.id = :id")
	public UserProfile getProfileById(@Param("id") Integer id);
}
