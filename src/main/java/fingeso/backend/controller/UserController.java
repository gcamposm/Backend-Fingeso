package fingeso.backend.controllers;

import fingeso.backend.dto.UserDto;
import fingeso.backend.models.User;
import fingeso.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("/")
  public ResponseEntity<List<UserDto>> getAllUsers(){
    try{
      return ResponseEntity.ok(userService.getAllUser());
    }
    catch(Exception e){
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping("/{firstName}/{lastName}")
  public ResponseEntity<List<UserDto>> findUserByFullName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
    try{
      return ResponseEntity.ok(userService.findUserByFullName(firstName,lastName));
    }
    catch(Exception e){
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<List<UserDto>> findUserById(@PathVariable("id") Integer id){
    try{
      return ResponseEntity.ok(userService.findUserById(id));
    }
    catch(Exception e){
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity updateUserData(@RequestBody User user, @PathVariable String id){

    try{
      int id2 = Integer.getInteger(id);
      userService.updateUserData(user, id2);
      return ResponseEntity.ok(HttpStatus.OK);
    }
    catch (Exception e){
      return ResponseEntity.badRequest().build();
    }

  }

  @PostMapping()
  public ResponseEntity<List<UserDto>> createGuest(@RequestBody User user){

    try{
      return ResponseEntity.ok(userService.createUser(user));
    }
    catch (Exception e){
      return ResponseEntity.badRequest().build();
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity deleteUser (@PathVariable int id){

    try{
      userService.deleteUser(id);
      return ResponseEntity.ok(HttpStatus.OK);
    }
    catch (Exception e){
      return ResponseEntity.badRequest().build();
    }
  }
}
