package com.sjzx.controller;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.sjzx.exception.ServiceException;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {

    protected static ExcelTypeEnum checkParam(MultipartFile file, HttpServletRequest request){
        /*if(StringUtils.isEmpty(request.getParameter("id"))){
            throw new ServiceException("公司id不能为空");
        }*/

        if(null == file){
            throw new ServiceException("上传文件为空!");
        }

        String fileName = file.getOriginalFilename();
        if (!fileName.matches("^.+\\.(?i)(xls)$")
                && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new ServiceException("上传文件格式不正确");
        }

        ExcelTypeEnum typeEnum;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            typeEnum = ExcelTypeEnum.XLSX;
        }else{
            typeEnum = ExcelTypeEnum.XLS;
        }
        return typeEnum;
    }
}
