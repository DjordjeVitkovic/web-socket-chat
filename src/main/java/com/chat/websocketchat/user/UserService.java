package com.chat.websocketchat.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.chat.websocketchat.user.Status.OFFLINE;
import static com.chat.websocketchat.user.Status.ONLINE;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public User saveUser(User user) {
    user.setStatus(ONLINE);
    return userRepository.save(user);
  }

  public void disconnect(User user) {
    var storedUser = userRepository.findById(user.getNickName())
      .orElse(null);
    if (storedUser != null) {
      storedUser.setStatus(OFFLINE);
      userRepository.save(user);
    }
  }

  public List<User> findConnectedUsers() {
    return userRepository.findAllByStatus(ONLINE);
  }

}
