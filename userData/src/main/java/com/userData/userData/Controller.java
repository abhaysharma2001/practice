package com.userData.userData;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    Service userService;
    @PostMapping("/addData")
    public User addData(@RequestBody User user){
        return userService.addData(user);
    }

    @GetMapping("/getData")
    public List<User> getData(){
        return userService.getData();
    }


    @GetMapping("getDataInExcel")

    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<User> list = userService.getData();

        dbToExcel excelExporter = new dbToExcel(list);

        excelExporter.export(response);
    }
}
