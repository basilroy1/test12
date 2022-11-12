package com.example.sampledeploy.controller;

import com.example.sampledeploy.view.MainView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
  @GetMapping("/")
  public MainView re(){
    return new MainView();
  }
}
