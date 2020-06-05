package com.dupake.order.controller;

import com.dupake.common.dto.UserDTO;
import com.dupake.common.message.BaseResult;
import com.dupake.common.message.Result;
import com.dupake.order.utils.ExportExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/17 10:05
 * @description 导入模板
 */
@RestController
@Api(tags = "文件控制器")
@RequestMapping(value = "file")
public class FileController {

    @ApiOperation(value = "查询用户信息", notes = "查询用户信息 - for-web")
    @GetMapping(value = "/hello")
    public Result hello() {
        int a = 1 / 0;
        return Result.ok("order receive message!");
    }


    @ApiOperation(value = "下载模板", notes = "web")
    @ResponseBody
    @PostMapping("/getExcel")
    public Result getExcel(HttpServletResponse response, HttpServletRequest request) throws Exception {

        String[] columnNames = {"规则库id", "类型", "名称", "分值", "策略描述"};
//        String[] columnNames = {
//                super.messageSourceName(MahjongConstant.mahjong_ai_strategyId),
//                super.messageSourceName(MahjongConstant.mahjong_ai_typeName),
//                super.messageSourceName(MahjongConstant.mahjong_ai_strategyName),
//                super.messageSourceName(MahjongConstant.mahjong_ai_baseScore),
//                super.messageSourceName(MahjongConstant.mahjong_ai_description)};
        String fileName = "AI.xlsx";

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {

//            response.setContentType("application/vnd.ms-excel;charset=utf-8");
//            response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes()));
//            CommonResult<List<AiStrategyNameDTO>> commonResult = subBossService.listAiStrategyNameAllByType(0);
//            if (!commonResult.isSuccess()) {
//                return commonResult;
//            }
//            List list = commonResult.getData();
            List list = Arrays.asList();

            ExportExcelUtil<Object> exportEquipExcelUtil = new ExportExcelUtil<>();
//            exportEquipExcelUtil.exportExcel(fileName, columnNames, list, response.getOutputStream(), ExportExcelUtil.EXCEl_FILE_2007);
            exportEquipExcelUtil.exportExcel(fileName, columnNames, list, os, ExportExcelUtil.EXCEl_FILE_2007);
            return Result.ok(os.toByteArray());
        } catch (Exception e) {
//            LoggerTool.error(e.getMessage());
        } finally {
            os.close();
        }

        return Result.error("ERROR", "");
    }

    @PostMapping(value = "/uploadExcel", headers = "Content-Type=multipart/form-data")
    @ApiOperation(value = "导入", notes = "web")
    public Result uploadExcel(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {

        String name = file.getOriginalFilename();

        if (file.getSize() > 1024 * 1024 * 5) {
//            return ResultUtil.error(ResultStatusEnum.FILE_UPLOAD_MAX.getCode(), ResultStatusEnum.FILE_UPLOAD_MAX.getDesc());
        }

        StringBuffer buffer = new StringBuffer("\n");
        List<UserDTO> strategyNameDTOS = new LinkedList<>();
        int count = 0;
        try {
            if (name != null) {
                InputStream inputStream = file.getInputStream();
                Workbook book = null;
                if (isExcel2003(name)) {
                    book = new HSSFWorkbook(inputStream);
                }
                if (isExcel2007(name)) {
                    book = new XSSFWorkbook(inputStream);
                }
                Sheet sheet = book.getSheetAt(0);
                int allRowNum = sheet.getLastRowNum();
                if (allRowNum == 0) {
                    buffer.append(BaseResult.SUCCESS.getMessage());
                }
                for (int i = 1; i <= allRowNum; i++) {
                    try {
                        count = i;
                        //加载状态值，当前进度
                        UserDTO dto = new UserDTO();//我需要插入的数据类型
                        Row row = sheet.getRow(i); //获取第i行
                        if (row != null) {
                            Cell cell1 = row.getCell(0); //获取第1个单元格的数据
                            Cell cell2 = row.getCell(1);
                            Cell cell3 = row.getCell(2);
                            Cell cell4 = row.getCell(3);
                            Cell cell5 = row.getCell(4);
                            dto.setUsername(ExportExcelUtil.getCellValue(cell1));//策略id
                            if (cell2 != null) {//类型名称
                                dto.setUsername(ExportExcelUtil.getCellValue(cell2));
                            }
                            if (cell3 != null) {//策略名称
                                dto.setUsername(ExportExcelUtil.getCellValue(cell3));
                            }
                            if (cell4 != null) {//底分
                                dto.setUsername(ExportExcelUtil.getCellValue(cell4));
                            }
                            if (cell5 != null) {//描述
                                dto.setUsername(ExportExcelUtil.getCellValue(cell5));
                            }
                            strategyNameDTOS.add(dto);
                        }
                    } catch (Exception e) {
                        buffer.append("第" + i + "行数据不能为空异常");
                        return Result.error("", buffer.toString());
                    }
                }
                //策略名称和策略id唯一性校验
                return Result.ok();
            }

        } catch (Exception e) {
//            LoggerTool.error("数据导入异常", e);
        }

        return Result.ok(buffer.toString());
    }

    /***
     *判断文件类型是不是2003版本
     * @param
     * @return
     */
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * 判断文件类型是不是2007版本
     *
     * @param
     * @return
     */
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }


}
