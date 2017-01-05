package com.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.demo.common.model.MsgBean;
import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;

/**
 * ClassName: UploadImgeController
 * 
 * @author 杨雄
 * @date 2016年12月29日11:23:35
 * @version V1.0
 */
public class UploadController extends Controller {

	/**
	 *
	 *  type 归类用--文档--头像--
	 *  杨雄
	 *  2016年12月29日11:23:35
	 */
	public void uploadFile() {
		MsgBean s = new MsgBean();
		try {
			String type = getPara("type");
			UploadFile uploadFile = getFile();
			String path = saveFile(uploadFile, type);
			String url = "/upload" + path;
			s.setStatus(1);
			s.setMsg("上传成功！");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("path", url);
			s.setData(map);

		} catch (Exception e) {
			s.setStatus(0);
			s.setMsg("上传失败！请重试");
			e.printStackTrace();
			return;

		} finally {
			renderJson(s);
		}
	}

	public void upload(){
		
	}

	private String saveFile(UploadFile uploadFile, String dir)
			throws IOException {
		String filePath = "";
		if (uploadFile != null) {
			filePath = uploadFile.getUploadPath();
		}
		File uploadfile = uploadFile.getFile();
		String name = uploadfile.getName();
		String fileType = name.substring(name.lastIndexOf('.') + 1);

		String path = "/" + fileType;
		if (dir != null) {
			path += "/" + dir;
		}
		File file = new File(filePath + path);
		if (!file.isDirectory()) {
			file.mkdirs();
		}
		path += "/"+ name;
		uploadfile.renameTo(new File(filePath + path));
		System.out.println("服务器地址：" + file.getPath());
		return path;

	}

}
