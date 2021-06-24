package com.project.admin.users.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.models.entities.UserProfile;

@Service
public class UserProfileService {
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	
	public UserProfile getUserProfileByUser(Integer id) {
		return userProfileRepository.getProfileById(id);
	}
	
	public UserProfile addProfile(UserProfile userProfile) {
		return userProfileRepository.save(userProfile);
	}
}
