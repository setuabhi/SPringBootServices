public class CQRS {

  /*  public class UserCommandController  { // Command  Microservice
        @PostMapping("/Create")
        public ResponseEntity<String> createUser(@RequestBody User user) {
            userRepository.saveUser(user);
            return ResponseEntity.ok("User created successfully");
        }
    }

    public class UserQueryController { // Query  Microservice
        @GetMapping("/user/{id}")
        public ResponseEntity<User> getUser(@PathVariable Long id) {
            return ResponseEntity.ok(userRepository.findById(id).orElse(null));
        }
    }*/
}
