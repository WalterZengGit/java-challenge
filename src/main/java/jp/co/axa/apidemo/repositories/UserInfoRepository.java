package jp.co.axa.apidemo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.axa.apidemo.entities.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

	Optional<UserInfo> findByUsername(String username);
}
