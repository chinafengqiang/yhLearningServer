package com.smlearning.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bookController")
public class BookController extends BaseController{
  @RequestMapping("/managerBook")
  public String managerBook() {
    return "jsp/system/book/managerBook";
  }
}
