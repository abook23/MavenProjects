package com.grp.ln.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.grp.ln.core.util.IdUtils;
import com.grp.ln.web.dao.ResourcesMapper;
import com.grp.ln.web.model.Resources;

/**
 * 文件上传
  * @ClassName: UploadServlet
  * @Description: TODO
  * @author Comsys-abook23@163.com
  * @date 2015年5月6日 上午9:03:08
  * 资源文件上传
 */
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ResourcesMapper resourcesMapper;
	public String physicalPath;

	private ServletContext servletContext;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("userId");
		String classify = request.getParameter("classify");//分类
		if(classify==null){
			classify = "other";
		}
		//String userId = (String) ActionContext.getContext().getSession().get("moblie_user_id");
		//HttpSession session=ServletActionContext.getRequest().getSession();
		//String userId = (String) session.getAttribute("moblie_user_id");
		/** 获得容器中上传文件夹所在的物理路径 **/
		//String physicalPath = ServletActionContext.getServletContext().getRealPath("");// 上传完后文件存放位置
		String physicalPath = servletContext.getRealPath("");// 上传完后文件存放位置 E:\Program Files\apache-tomcat-7.0.39\webapps\groupline
//		physicalPath = properties.getInstance().getProperty("res.path");
		//系统路径
		String path = "/uploads/" + userId+"/"+classify;
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		List<Object>  list_f = new ArrayList<Object>();
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			/*
			 * int file_max = 4; upload.setSizeMax(file_max*1024*1024);
			 */
			try {
				List<FileItem> list = upload.parseRequest(request);
				Iterator<FileItem> iterator = list.iterator();
				//iterator = upload.parseRequest(request).iterator();
				Resources res;
				while (iterator.hasNext()) {
					FileItem item = (FileItem) iterator.next();
					if (!item.isFormField()) {
						res = new Resources();
						String resId = IdUtils.getId();
						String name = item.getName();
						String fileType = name.substring(name.lastIndexOf('.') + 1);
						String resAliasAme = resId+"."+fileType;
						
						path += "/"+fileType;
						res.setPath(path);//
						File file = new File(physicalPath+path);
						if (!file.isDirectory()) {
							file.mkdirs();
						}
						path +="/"+resAliasAme;
						file = new File(physicalPath+path);
						item.write(file);
						
						System.out.println("服务器地址：" + file.getPath());
						//resAction.Resource(resId,name,describe,path,request);
						
						res.setResId(resId);
						//res.setFileName(name);
						res.setName(name);
						res.setType(fileType);
						res.setDateTime(new Date());
						
						resourcesMapper.insert(res);
											
						list_f.add(res);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(list_f);
			out.println(json);
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void init() throws ServletException {
		// Put your code here
		servletContext = this .getServletContext();   
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);   
        resourcesMapper = (ResourcesMapper) ctx.getBean("resourcesMapper");                  
	}

}
