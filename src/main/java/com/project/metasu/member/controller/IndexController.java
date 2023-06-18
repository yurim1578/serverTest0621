package com.project.metasu.member.controller;/*
package com.project.metasu.member.controller;

package com.project.metasu.member.controller;

import com.project.metasu.member.repository.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
  private MemberRepository memberRepository;





   @Secured("ROLE_ADMIN")
    @GetMapping("/info")
    @ResponseBody*/
/**//*

    public String info() {
      return "개인정보";
    }



@GetMapping({"", "/"})
public String index() {
  return "index";
}

  @GetMapping("/user")
  @ResponseBody
  public String user() {
    return "user";
  }

  @GetMapping("/admin")
  @ResponseBody
  public String admin() {
    return "admin";
  }




}

*/
