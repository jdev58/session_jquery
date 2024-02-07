package com.example.front;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@RestController
@RequestMapping("/rest")
@CrossOrigin(origins = "*")
public class MyRest {

    @GetMapping("/captcha")
    public ResponseEntity<String> captcha(HttpServletRequest request ){

        Random rand = new Random();
        Integer value = rand.nextInt(10000);
        String t = value.toString();
        System.out.println(t);
        request.getSession().setAttribute("mycap", t);
        System.out.println("sessionID="+request.getSession().getId());
        return new ResponseEntity<>(t, HttpStatus.OK);

    }


    @GetMapping("/get")
    public ResponseEntity<String> test2(HttpServletRequest request ){
        System.out.println("sessionID="+request.getSession().getId());
        String t  = (String) request.getSession().getAttribute("mycap");
        System.out.println(t);
        return new ResponseEntity<>(t, HttpStatus.OK);
        // warning your system is under attack, we are hacking you...
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(HttpServletRequest request, @RequestBody AppVM appVM) {
        String t  = (String) request.getSession().getAttribute("mycap");
        System.out.println(t);
        return new ResponseEntity<String>(t, HttpStatus.OK);
    }


    @GetMapping("/appvm")
    public AppVM getAPPVM(HttpServletRequest request ){
        AppVM vm = new AppVM();
        vm.setName("aaa");
        return vm;
        // warning your system is under attack, we are hacking you...
    }
}
