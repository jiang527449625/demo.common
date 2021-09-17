package com.demo.common.utils;

import java.io.File;

/**
 * UploadFile.
 */
public class UploadFile {

	private String parameterName;  //参数名称,表单name值

	private String saveDirectory; //保存路径
	private String fileName;  //文件名字
	private String originalFileName;  //原文件名称
	private String contentType; //类型
	private String fileSurfix; //文件后缀

	/**
	 * 创建 对象
	 * @param parameterName		参数名
	 * @param saveDirectory		存储路径
	 * @param fileName			存储文件名（不带后缀名）
	 * @param originalFileName	原始文件名
	 * @param contentType		文件类型
	 * @param fileSurfix		文件后缀
	 */
	public UploadFile(String parameterName, String saveDirectory, String fileName, String originalFileName,
			String contentType, String fileSurfix) {
		this.parameterName = parameterName;
		this.saveDirectory = saveDirectory;
		this.fileName = fileName;
		this.originalFileName = originalFileName;
		this.contentType = contentType;
		this.fileSurfix = fileSurfix;
	}

	/**
	 * 获取存储文件的全名（文件名加后缀）
	 * @return
	 */
	public String fileFullName() {
		return getFileName() + "." + getFileSurfix();
	}
	
	public String getParameterName() {
		return parameterName;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFileSurfix() {
		return fileSurfix;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public String getContentType() {
		return contentType;
	}

	public String getSaveDirectory() {
		return saveDirectory;
	}

	public File getFile() {
		if (saveDirectory == null || fileName == null) {
			return null;
		} else {
			return new File(saveDirectory + File.separator + fileName);
		}
	}
}
