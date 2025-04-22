package com.repaire.controller;


import com.repaire.pojo.TRequest;
import com.repaire.service.RequestService;
import com.repaire.util.PageResult;
import com.repaire.util.QueryPageBean;
import com.repaire.util.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lzy
 * @since 2024-12-26
 */
@RestController
@RequestMapping("/request")
public class TRequestController {
    @Value("${setmealpic.path}")
    private String setmealPath;
    @Resource
    private RequestService requestService;

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = requestService.findPage(queryPageBean);
        return pageResult;
    }
    @RequestMapping("/allocate")
    public Result allocate(@RequestBody TRequest request) {
        Result result = requestService.allocate(request);
        return result;
    }
    @RequestMapping("/saveRequest")
    public Result saveRequest(@RequestBody TRequest request) {
        Result result = requestService.saveRequest(request);
        return result;
    }
    @RequestMapping("/uploadFile")
    public Result uploadFile(MultipartFile imgFile) {
        //获取图片的原名称 abc.245.jpg
        String originalFilename = imgFile.getOriginalFilename();
        int lastIndexOf = originalFilename.lastIndexOf('.');
        String str1 = originalFilename.substring(lastIndexOf);//.jpg
        String uuidStr = UUID.randomUUID().toString();//fdhfjsd534f
        String newFileName = uuidStr + str1;//fdhfjsd534f.jpg
        File file = new File(setmealPath, newFileName);
        try {
            imgFile.transferTo(file);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new Result(true, "上传成功", newFileName);
    }

}

