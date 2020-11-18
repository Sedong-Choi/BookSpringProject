package com.bookspring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleController4 {
    private static final Logger logger =
            LoggerFactory.getLogger(SampleController4.class);

    @RequestMapping("/doE")
    public String doE(RedirectAttributes rttr){
        logger.info("doE called but redirect to /doF..");

        //addFlashAttribute를 사용하면 redirect시 url에 안남는다 ..
        // 하지만 왜 doF 에서 msg 를 못받는지??
        // 잠시 session에 담았다 redirect 후 소멸한다고 한다. 그럼 안나오는게 맞는거
        rttr.addFlashAttribute("msg","This is the Message!! with redirected");

        //addAttribute 사용시 url에 남는다. doF에서 msg를 받을 수 있다.
        //rttr.addAttribute("msg","This is the Message!! with redirected");


        return "redirect:/doF";
    }

    @RequestMapping("/doF")
    public void doF(String msg){
        // doE 에서 setting 한 msg가 나와야 하는데 왜....
        // logger에서는 안나오지만 jsp에서 ${msg} 를 받아올 수있다.

         logger.info("doF called ..."+ msg);

    }
}
