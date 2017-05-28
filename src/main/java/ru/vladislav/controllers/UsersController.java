package ru.vladislav.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.vladislav.dto.UserDto;
import ru.vladislav.dto.UserDtoForRegistration;
import ru.vladislav.services.UsersService;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAll(){
        return new ResponseEntity<List<UserDto>>(usersService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDtoForRegistration user){
        UserDto userDto = usersService.registerUser(user);
        String token = usersService.login(user.getName(), user.getPassword());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Auth-Token", token);
        return new ResponseEntity<>(userDto, headers, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestHeader("name") String name, @RequestHeader("password") String password){
        String token = usersService.login(name, password);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Auth-Token", token);
        return new ResponseEntity<Object>(usersService.getUserByName(name), headers, HttpStatus.CREATED);
    }

    @GetMapping("/users/{user_id}")
    public ResponseEntity<UserDto> getById(@PathVariable("user_id") Long id){
        return new ResponseEntity<UserDto>(usersService.getUserById(id), HttpStatus.OK);
    }

    /*
    @RequestMapping(value = "/{user_id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<UserDto> updateUserById(@RequestBody UserDtoForRegistration user, @PathVariable("user_id") Long id){
    }
    */

    @DeleteMapping("/users/{user_id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("user_id") Long id, @RequestHeader("name") String name, @RequestHeader("password") String password){
        usersService.removeUserById(id, name, password);
        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }


}
