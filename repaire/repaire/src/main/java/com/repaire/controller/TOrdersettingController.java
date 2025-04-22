package com.repaire.controller;


import com.repaire.service.OrdersettingService;
import com.repaire.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.repaire.util.POIUtils;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sdlg
 * @since 2024-12-26
 */
@RestController
@RequestMapping("/ordersetting")
public class TOrdersettingController {

    @Resource
    private OrdersettingService ordersettingService;

    @RequestMapping("/uploadTempleate")
    public Result uploadTempleate(MultipartFile excelFile){
        try {
            //读取文件信息
            List<String[]> lists = POIUtils.readExcel(excelFile);
            Result result = ordersettingService.saveOrdersettingInfo(lists);
            return  result;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return  new Result(false,"保存失败");
    }

    @RequestMapping("/getOrdersettingInfo")
    public Result getOrdersettingInfo(String date){ // "2024-12  2025-01"
        Result result = ordersettingService.getOrdersettingInfo(date);
        return  result;
    }

    //单独设置某个日期的预约人数
    @RequestMapping("/updateOrdersettingInfo")
    public Result updateOrdersettingInfo(String date,Integer number){
        Result result = ordersettingService.updateOrdersettingInfo(date,number);
        return  result;
    }


}

