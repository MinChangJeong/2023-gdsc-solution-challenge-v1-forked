package gdsc.netwalk.business.user.controller;

import gdsc.netwalk.business.user.service.UserService;
import gdsc.netwalk.common.model.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1")
public class UserController{

    @Autowired
    UserService userService;

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<Payload> login(@RequestBody Payload request) {
        Payload response = userService.login(request);
        return ResponseEntity.ok(response);
    }
}
