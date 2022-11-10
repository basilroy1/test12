package com.example.sampledeploy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
  @GetMapping("/sample-deploy.azurewebsites.net/")
  public String re(){
    return "rerere";
  }
}
