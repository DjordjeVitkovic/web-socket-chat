package com.chat.websocketchat.user;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @MessageMapping("/user.addUser")
  @SendTo("/user/topic")
  public ResponseEntity<User> addUser(@Payload User user) {
    return ResponseEntity.ok(userService.saveUser(user));
  }

  @MessageMapping("/user.disconnectUser")
  @SendTo("/user/topic")
  public ResponseEntity<User> disconnect(@Payload User user) {
    userService.disconnect(user);
    return ResponseEntity.ok(user);
  }

  @GetMapping("/users")
  public ResponseEntity<List<User>> findConnectedUsers() {
    return ResponseEntity.ok(userService.findConnectedUsers());
  }
}
