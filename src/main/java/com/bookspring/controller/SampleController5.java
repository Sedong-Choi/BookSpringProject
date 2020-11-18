package com.bookspring.controller;

import com.bookspring.domain.ProductVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController5 {
    @RequestMapping("/doJSON")
    @ResponseBody  //ResponseBody를 사용하면 json data를 직접 view부분으로 보낼 수 있다.
    public ProductVO doJSON(){
        ProductVO vo = new ProductVO("샘플상품",30000);
        return vo;
    }
}
