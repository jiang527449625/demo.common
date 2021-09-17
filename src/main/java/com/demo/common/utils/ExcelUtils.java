package com.demo.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ExcelUtils {

	/**
	 * 导出excel,普通形式(流的形式直接下载)
	 * @param response
	 * @param workbook
	 * @param fileName
	 * @throws Exception
	 */
	public static void exportExcel(HttpServletResponse response, Workbook workbook, String fileName) throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		workbook.write(os);
		os.flush();
		byte[] buf = os.toByteArray();
		InputStream is = new ByteArrayInputStream(buf, 0, buf.length);
		FileUtils.downloadFile(response, is, fileName);
		is.close();
		os.close();
	}

	/**
	 * 导出excel,普通形式(先保存一份到服务器，之后从服务器下载)
	 * @param response
	 * @param filePath 在服务器中导出的excel文件路径
	 * @param fileName 下载的excel名字
	 * @param workbook
	 */
	public static void exportExcel(HttpServletResponse response, String filePath, String fileName, Workbook workbook)
			throws Exception {
		FileOutputStream fos = new FileOutputStream(new File(filePath));
		workbook.write(fos);
		fos.close();
		FileUtils.downloadFile(response, filePath, fileName);
	}
	
	public static int rowNo = 0;
	/**
	 * 
	 * @param vo 封装好的数据
	 * @param sheetName HSSFSheet名称
	 * @param cdNames 表头展示列
	 * @param dataHerder 表头列名与实体类属性对应字段
	 * @param rowCellIndexs 行合并时需要合并的列下标
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static<T> HSSFWorkbook exportData(T vo, String sheetName, String[][] cdNames, String[] dataHerder, Integer[] rowCellIndexs) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		rowNo = 0;
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetName);
        HSSFCellStyle style = wb.createCellStyle();
        sheet.setDefaultColumnWidth(12);
        sheet.setDefaultRowHeightInPoints(25);

        //居中
        style.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont fontHead = wb.createFont();
        fontHead.setFontName("宋体");
        fontHead.setFontHeightInPoints((short)14);// 设置字体大小
        style.setFont(fontHead);

      //设置边框
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
//      设置表格表头
        HSSFRow rowTitle_ = sheet.createRow(rowNo);
        HSSFCell cellTitle_0 = rowTitle_.createCell(0);
        cellTitle_0.setCellValue(sheetName);
        cellTitle_0.setCellStyle(style);
        sheet.addMergedRegion(new Region(0, (short) (0), 0, (short) (cdNames[0].length - 1)));
        
        int rowCount = rowNo + 1;
        int hbrowCount = 0;
        for(int r = 0; r < cdNames.length; r++){
        	rowNo++;
        	int cellCount = 0;
            HSSFRow rowTitle = sheet.createRow(rowNo);
            for(int i = 0; i < cdNames[r].length; i++){
            	String cd = (cdNames[r][i]).replace(" ", "");
            	HSSFCell cellTitle = rowTitle.createCell(i);
            	cellTitle.setCellValue(cd);
            	cellTitle.setCellStyle(style);
            	if(StringUtil.isNull(cd).equals("")){
            		if(i+1 < cdNames[r].length){
            			if(!StringUtil.isNull((cdNames[r][i+1]).replace(" ", "")).equals("")){
            				sheet.addMergedRegion(new Region(rowCount, (short)cellCount, rowCount, (short) i));
            			}
            		}else if(i == cdNames[r].length - 1){
            			sheet.addMergedRegion(new Region(rowCount, (short)cellCount, rowCount, (short) i));
            		}
            		if(hbrowCount == 0){
            			hbrowCount = i - 1;
            		}
            	}
            	if(hbrowCount > 0 && i < hbrowCount){
            		sheet.addMergedRegion(new Region(rowCount-1, (short)i, rowCount, (short) i));
            	}
            	if(!StringUtil.isNull(cd).equals("")){
            		cellCount = i;
            	}
            }
            rowCount ++;
        }
        
        if(vo!=null){
        	int rowNoCount = rowNo;
        	//先把数据放上去再列合并
        	dataListUtil(style, sheet, vo, dataHerder);

//        	行合并
//        	++表示开始新的一行
        	rowNoCount++;
        	for(Integer c : rowCellIndexs){
        		int startCount = rowNoCount;
            	int endCount = rowNoCount;
            	int r = 0;
            	String cellValue = null;
            	Iterator<Row> rowIt = sheet.rowIterator();
        		while(rowIt.hasNext()) {
        			Row row = rowIt.next();
        			if(r >= rowNoCount){
    	        		Iterator<Cell> cellIt = row.cellIterator();
            			int cindex = 0;
            			while(cellIt.hasNext()) {
            				Cell cell = cellIt.next();
                        	if(cindex==c){
                        		if(!cell.toString().equals(cellValue)){
                        			if(cellValue != null){
                        				if(startCount != endCount){ // 开始行不等于结束的时候合并行
                        					sheet.addMergedRegion(new Region(startCount, (short)((int)c), endCount, (short)((int)c)));
                        				}
                        				startCount = cell.getRowIndex();
                            			endCount = cell.getRowIndex();
                        			}
                        			cellValue = cell.toString();
                        		}else{
                        			endCount ++ ;
                        		}
                        		break;
                        	}
                        	cindex++;
                        }
        			}
        			r++;
        		}
        	}
        }
        return wb;
	}
	
	/**
	 * 
	 * @param style 单元格样式
	 * @param sheet 
	 * @param vo 数据
	 * @param dataHerder 数据展示列对应的实体属性名称
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static<T> void dataListUtil (HSSFCellStyle style, HSSFSheet sheet, T vo, String[] dataHerder) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		sheet.setDefaultColumnWidth(16);
        sheet.setDefaultRowHeightInPoints(25);
        Field[] ffields = vo.getClass().getSuperclass() != null ? vo.getClass().getSuperclass().getDeclaredFields() : new Field[0];//获取这个类的父类的所有属性(这个getDeclaredFields获取本类)
		Field[] dfields = vo.getClass().getDeclaredFields();//获得JavaBean全部属性
		Field[] fields = Arrays.copyOf(ffields, ffields.length + dfields.length);
		System.arraycopy(dfields, 0, fields, ffields.length, dfields.length);
		boolean flag = false;
		for(int i = 0;i < fields.length;i++){//遍历属性，比对
    		Field field = fields[i];
    		String fieldName = field.getName();
    		if(fieldName.equals("dataList")){//dataList  为实体类的list固定写法如有其他的实体类此属性明必须保持一致
    			String getMethodName = "get"
                        + fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);//拿到属性的get方法
    			Method getMethod = vo.getClass().getMethod(getMethodName, new Class[] {});//通过JavaBean对象拿到该属性的get方法，从而进行操控
    			List<T> dataList = (List<T>) getMethod.invoke(vo, new Object[] {});//操控该对象属性的get方法，从而拿到属性值
    			if(dataList != null && dataList.size() > 0){
    				Iterator<T> tVo = dataList.iterator();
    				while (tVo.hasNext()) {//记录的迭代器，遍历
    					// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
    					T obj = tVo.next();
    					dataListUtil(style, sheet, obj, dataHerder);
    				}
    				String fieldNameHj = "hbRowType";
    				String getMethodNameHj = "get"
                            + fieldNameHj.substring(0, 1).toUpperCase()
                            + fieldNameHj.substring(1);//拿到属性的get方法
        			Method getMethodHj = vo.getClass().getMethod(getMethodNameHj, new Class[] {});//通过JavaBean对象拿到该属性的get方法，从而进行操控
        			Object hbRowType =  getMethodHj.invoke(vo, new Object[] {});//操控该对象属性的get方法，从而拿到属性值
//        			这里判断是否展示合并列如：小计、合计、总计;数据结构为一个实体没有子集合就是数据行，如果有子集合就是合并列的行，合并列的行展示有hbRowType字段来控制;并且需要合并的列的值都要相同；
//        			如小计：需要合并3列那么这三列就都需要叫小计，否则程序会默认寻找列与列值一样的数据进行合并。
        			if(String.valueOf(hbRowType).equals("1")){
        				rowNo++;
    					HSSFRow rowTitleBus = sheet.createRow(rowNo);
    					int cdCount = 0;
    					String fieldValue = "";
    					int hbStartCount = 0;
    					int hbEndCount = 0;
    					for(String herderStr : dataHerder){
    						for(Field field1 : fields){
    			            	String fieldName1 = field1.getName();
    			            	if(herderStr.equals(fieldName1)){
    			            		String getMethodName1 = "get"
    			                            + fieldName1.substring(0, 1).toUpperCase()
    			                            + fieldName1.substring(1);//拿到属性的get方法
    			        			Method getMethod1 = vo.getClass().getMethod(getMethodName1, new Class[] {});//通过JavaBean对象拿到该属性的get方法，从而进行操控
    			        			Object objValue = getMethod1.invoke(vo, new Object[] {});//操控该对象属性的get方法，从而拿到属性值
    			        			HSSFCell cellTitleBus0 = rowTitleBus.createCell(cdCount);
    			        			if (objValue instanceof String) {
    			        				String textVal = null;
    			                        if (objValue!= null) {
    			                            textVal = String.valueOf(objValue);//转化成String
    			                        }else{
    			                            textVal = null;
    			                        }
    			                        cellTitleBus0.setCellValue(StringUtil.isNull(textVal));
    			                        if(!StringUtil.isNull(fieldValue).equals(StringUtil.isNull(textVal)) && hbEndCount == 0){
        		                        	fieldValue = StringUtil.isNull(textVal);
        		                        	hbStartCount = cdCount;
        		                        }else if(StringUtil.isNull(fieldValue).equals(StringUtil.isNull((textVal)))){
        		                        	hbEndCount = cdCount;
        		                        }
    			        			} else {
    			        				Double textVal = new Double(0);
    			                        if (objValue!= null) {
    			                            textVal = Double.parseDouble(String.valueOf(objValue));//转化成String
    			                        }
    			                        cellTitleBus0.setCellValue(textVal);
    			        			}
    		        		        cellTitleBus0.setCellStyle(style);
    		        		        cdCount ++;
    		        		        break;
    			            	}
    			            }
    					}
    					String fieldNameHbCellType = "hbCellType";
        				String getMethodNamehbCellType = "get"
                                + fieldNameHbCellType.substring(0, 1).toUpperCase()
                                + fieldNameHbCellType.substring(1);//拿到属性的get方法
            			Method getMethodHbCellType = vo.getClass().getMethod(getMethodNamehbCellType, new Class[] {});//通过JavaBean对象拿到该属性的get方法，从而进行操控
            			Object hbCellType =  getMethodHbCellType.invoke(vo, new Object[] {});//操控该对象属性的get方法，从而拿到属性值
    					if(String.valueOf(hbCellType).equals("1")){
    						sheet.addMergedRegion(new Region(rowNo, (short)hbStartCount, rowNo, (short) hbEndCount));
    					}
        			}
    			}else{
    				flag = true;
    			}
    		}
    	}
		if(flag){
			rowNo++;
			HSSFRow rowTitleBus = sheet.createRow(rowNo);
			int cdCount = 0;
			for(String herderStr : dataHerder){
				for(Field field1 : fields){
	            	String fieldName1 = field1.getName();
	            	
	            	if(herderStr.equals(fieldName1)){
	            		String getMethodName1 = "get"
	                            + fieldName1.substring(0, 1).toUpperCase()
	                            + fieldName1.substring(1);//拿到属性的get方法
	        			Method getMethod1 = vo.getClass().getMethod(getMethodName1, new Class[] {});//通过JavaBean对象拿到该属性的get方法，从而进行操控
	        			Object objValue = getMethod1.invoke(vo, new Object[] {});//操控该对象属性的get方法，从而拿到属性值
	        			HSSFCell cellTitleBus0 = rowTitleBus.createCell(cdCount);
	        			if (objValue instanceof String) {
	        				String textVal = null;
	                        if (objValue!= null) {
	                            textVal = String.valueOf(objValue);//转化成String
	                        }else{
	                            textVal = null;
	                        }
	                        cellTitleBus0.setCellValue(StringUtil.isNull(textVal));
	        			} else {
	                        if (objValue!= null) {
	                        	Double textVal = new Double(0);
	                            textVal = Double.parseDouble(String.valueOf(objValue));//转化成String
	                            cellTitleBus0.setCellValue(textVal);
	                        }
	        			}
        		        cellTitleBus0.setCellStyle(style);
        		        cdCount ++;
        		        break;
	            	}
	            }
			}
		}
	}

	/**
	 * 导出excel,模板形式(流方式直接下载)
	 * @param response
	 * @param templatePath 模板excel的路径
	 * @param fileName 导出excel文件名
	 * @param data 数据map
	 */
//	public static void exportExcel(HttpServletResponse response,
//			String templatePath,String fileName, Map data) throws Exception {
//		ByteArrayOutputStream os = new ByteArrayOutputStream();;
//		PoiTemplate template = new PoiTemplate(templatePath,os);
//		template.addMap(data);
//		template.writeExcel();
//		os.flush();
//		byte[] buf = os.toByteArray();
//		InputStream is = new ByteArrayInputStream(buf, 0, buf.length);
//		FileUtils.downloadFile(response, is, fileName);
//		is.close();
//		os.close();
//	}
	
	/**
	 * 导出excel,模板形式(先保存一份到服务器，之后从服务器下载)
	 * @param response
	 * @param templatePath 模板excel的路径
	 * @param outPath 保存到服务器的位置
	 * @param fileName 导出excel文件名
	 * @param data 数据map
	 */
//	public static void exportExcel(HttpServletResponse response,
//			String templatePath,String outPath,String fileName, Map data) throws Exception{
//		PoiTemplate template = new PoiTemplate(templatePath,outPath);
//		template.addMap(data);
//		template.writeExcel();
//		FileUtils.downloadFile(response, outPath, fileName);
//	}

}
