package com.pinux.controller.user;


import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import com.pinux.entity.user.Users;
import com.pinux.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pinux
 * @since 2021-03-12
 */
@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "multiThreadExport",method = RequestMethod.GET)
    public void multiThreadExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Users> rows = userService.getUsersList();

        BigExcelWriter writer= (BigExcelWriter) ExcelUtil.getBigWriter();
        //设置表头
        writer.addHeaderAlias("id", "id");
        writer.addHeaderAlias("userName", "用户名");
        writer.addHeaderAlias("userSex", "性别");
        writer.addHeaderAlias("nickName", "昵称");
        writer.write(rows);
        String sheetName = "用户表";
        String fileName = sheetName + ".xlsx";
        String userAgent = request.getHeader("user-agent");
        if (userAgent != null && userAgent.indexOf("Firefox") >= 0 || userAgent.indexOf("Chrome") >= 0 || userAgent.indexOf("Safari") >= 0) {
            fileName = new String((fileName).getBytes(), "ISO8859-1");
        } else {
            fileName = URLEncoder.encode(fileName, "UTF8"); //其他浏览器
        }
        response.setContentType("application/octet-stream");
        //设置导出Excel的名称
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        ServletOutputStream out=response.getOutputStream();

        writer.flush(out);
        writer.close();
        IoUtil.close(out);
    }
}
