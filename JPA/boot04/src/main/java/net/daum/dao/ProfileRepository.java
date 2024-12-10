package net.daum.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.daum.vo.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

}
