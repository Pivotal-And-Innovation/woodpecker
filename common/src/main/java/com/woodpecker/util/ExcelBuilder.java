package com.woodpecker.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 生成excel表工具类
 *
 * @author Relaxier
 * @since 2017年03月30日
 */
public final class ExcelBuilder {

    /**
     * 单元格最大长度
     */
    private static final int MAX_CELL_LENGTH = 65000;

    /**
     * 获取excel表的 {@code byte[]}
     *
     * @param title    excel表题
     * @param colName  每列名字字符串数组
     * @param dataList excel表每行对象
     * @return excel表 {@code byte[]}
     * @throws IOException 输入输出异常
     */
    public static byte[] exportExcel(String title, String[] colName, List<Object[]> dataList) throws IOException {
        /*create the workbook*/
        HSSFWorkbook workbook = new HSSFWorkbook();
        /*create the sheet through the workbook*/
        HSSFSheet sheet = workbook.createSheet(title);

        /*sheet样式定义, 分别获取列头样式对象和单元格样式对象*/
        HSSFCellStyle columnTopStyle = headCellStyle(workbook);
        HSSFCellStyle style = dataCellStyle(workbook);

        // 所需列数
        int columnNum = colName.length;
        // 创建最开始的列头行
        HSSFRow hssfRow = sheet.createRow(0);

        /*将列头设置到sheet的单元格中*/
        for (int n = 0; n < columnNum; n++) {
            // 创建单元格：列头行
            HSSFCell cell = hssfRow.createCell(n);
            // 设置单元格的数据类型
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            HSSFRichTextString text = new HSSFRichTextString(colName[n]);
            // 设置该单元格的值：我们所传入的
            cell.setCellValue(text);
            // 设置列头行单元格样式
            cell.setCellStyle(columnTopStyle);
        }

        /*数据设置到sheet对应的单元格中*/
        for (int i = 0; i < dataList.size(); i++) {
            Object[] data = dataList.get(i);
            /*创建数据行：dataList中的每个元素都对应一个数据行*/
            HSSFRow row = sheet.createRow(i + 1);

            for (int j = 0; j < data.length; j++) {
                // 创建该行的单元格, 并设置其可填充的数据类型
                HSSFCell cell = row.createCell(j, HSSFCell.CELL_TYPE_STRING);
                // 设置单元格值
                if (data[j] == null) {
                    cell.setCellValue("");
                } else {
                    cell.setCellValue(data[j].toString());
                }
                // 设置数据单元格样式
                cell.setCellStyle(style);
            }
        }

        /*让列宽随着导出的列长自动适应*/
        for (int colNum = 0; colNum < columnNum; colNum++) {
            int columnWidth = sheet.getColumnWidth(colNum) / 256;
            for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                HSSFRow currentRow;
                // 当前行未被使用过
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }
                if (currentRow.getCell(colNum) != null) {
                    HSSFCell currentCell = currentRow.getCell(colNum);
                    if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                        int length = currentCell.getStringCellValue().getBytes().length;
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
            }
            if (colNum == 0) {
                sheet.setColumnWidth(colNum, (columnWidth - 2) * 256 > MAX_CELL_LENGTH ? MAX_CELL_LENGTH : (columnWidth - 2) * 256);
            } else {
                sheet.setColumnWidth(colNum, (columnWidth + 4) * 256 > MAX_CELL_LENGTH ? MAX_CELL_LENGTH : (columnWidth - 2) * 256);
            }
        }

        // 转换成字节数组
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        return bos.toByteArray();
    }

    /**
     * 定制列头单元格样式
     *
     * @param workbook 工作簿
     * @return 单元格样式
     */
    private static HSSFCellStyle headCellStyle(HSSFWorkbook workbook) {
        /*设置字体*/
        HSSFFont font = workbook.createFont();
        //设置字体大小;
        font.setFontHeightInPoints((short) 11);
        //字体加粗;
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字;
        font.setFontName("宋体");

        /*定制样式*/
        HSSFCellStyle style = workbook.createCellStyle();
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;

    }

    /**
     * 定制数据单元格样式
     *
     * @param workbook 工作簿
     * @return 单元格样式
     */
    private static HSSFCellStyle dataCellStyle(HSSFWorkbook workbook) {
        /*设置字体*/
        HSSFFont font = workbook.createFont();
        //设置字体名字;
        font.setFontName("宋体");

        /*定制样式*/
        HSSFCellStyle style = workbook.createCellStyle();
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;
    }

    public static void main(String[] args) {

    }

    /*js端代码*/
//    $http({
//        url: 'mongo/exportCSV',
//                method: "POST",
//                data: excelParam,
//                responseType: 'arraybuffer'
//    }).success(function (data, status, headers, config) {
//        var blob = new Blob([data], {type: "text/plain;charset=utf8"});
//        var now = new Date();
//        var date = $filter('date')(now, 'yyyy-MM-dd HH:mm:ss');
//        saveAs(blob, '同业个人信息统计-' + date + '.xls');
//        console.log("导出excel成功.时间：", date);
//    }).error(function (data, status, headers, config) {
//        console.log("导出excel失败.时间：", data);
//    });

}
