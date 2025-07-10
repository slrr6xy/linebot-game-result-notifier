package com.example.linebot.repository;

import com.example.linebot.model.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, String> {
}
