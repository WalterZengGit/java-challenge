package jp.co.axa.apidemo.services;

import java.util.List;
import java.util.Optional;

import jp.co.axa.apidemo.entities.UserInfo;

public interface UserInfoService {

	public List<String> getAllUserNames();

	public Optional<UserInfo> getByUsername(String username);

}
