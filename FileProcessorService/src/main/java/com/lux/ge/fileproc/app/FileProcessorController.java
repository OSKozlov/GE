package com.lux.ge.fileproc.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileProcessorController {

    @RequestMapping("/healthCheck")
    @ResponseBody
    public String healthCheck() {
        return "I'm healthy!";
    }

}