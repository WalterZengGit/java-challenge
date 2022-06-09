package jp.co.axa.apidemo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axa.apidemo.entities.UserInfo;
import jp.co.axa.apidemo.repositories.UserInfoRepository;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoRepository repo;

	/**
	 * fake data for local test
	 */
	@PostConstruct
	void init() {
		List<UserInfo> userList = List.of(
				UserInfo.builder()
						.username("admin")
						.password("admin123")
						.role("ADMIN").build(),
				UserInfo.builder()
						.username("viewer")
						.password("viewer123")
						.role("ROLE_VIEWER").build(),
				UserInfo.builder()
						.username("editor")
						.password("editor123")
						.role("ROLE_EDITOR").build());
		repo.saveAll(userList);
	}
	
	@Override
	public List<String> getAllUserNames() {
		return repo.findAll().stream()
				.map(UserInfo::getUsername)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<UserInfo> getByUsername(String username) {
		return repo.findByUsername(username);
	}

}
