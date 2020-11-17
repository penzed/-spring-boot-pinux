package com.pinux.controller.exam;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pinux
 * @since 2019-09-24
 */
@RestController
@RequestMapping("/exam")
public class ExamController {

    @RequestMapping("/")
    String index(){
        return "hello 阿斯顿撒阿打算的撒的 阿斯蒂芬boot阿斯顿发送到发送到发放";
    }


}/**/
