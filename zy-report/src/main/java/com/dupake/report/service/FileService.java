package com.dupake.report.service;

import com.dupake.report.entity.User;
import com.dupake.report.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class FileService {

    /**
     * 导出
     * @param response
     */
    public void export( HttpServletResponse response) {
        List<User> userList = new ArrayList<>();
        for(int i =0 ; i<100;i++){
            User user = User.builder().id(i).age(i).email("test" + i).name("test").createTime(new Date()).build();
            userList.add(user);
        }
        FileUtil.exportExcel(userList, "用户统计", "用户统计", User.class, "user.xls", response);

    }

    /**
     * 导入
     * @param file
     */
    public void importExcel(MultipartFile file) {
        //解析excel，titleRows指的是上面设置的title如果上面设置了，就要写成1代表第一行是标题，
        //写成0代表没有标题headerRows代表表头，当没有标题的时候这个数值设置成1代表第一行是表头，数据在第二行
        List<User> userList = FileUtil.importExcel(file, 1, 1, User.class);
        userList.forEach(user -> log.info(user.getEmail()));

    }
}
