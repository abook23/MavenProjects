package com.grp.ln.web.controller;

import com.grp.ln.core.util.FileUtils;
import com.grp.ln.core.util.IdUtils;
import com.grp.ln.core.util.properties;
import com.grp.ln.web.dao.ResourcesMapper;
import com.grp.ln.web.model.Resources;
import com.grp.ln.web.model.ResourcesExample;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

@Controller
@RequestMapping("/fileUpload")
public class FileUploadController {

    private String physicalPath;
    @Resource
    private ResourcesMapper resourcesMapper;

    /**
     * 多文件上传
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/uploadFiles")
    @ResponseBody
    public List<Resources> uploadFiles(HttpServletRequest request, HttpServletResponse response) {
        List<Resources> resources = new ArrayList<>();
        String type = (String) request.getAttribute("type");
        // 转型为MultipartHttpRequest
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        // 获得上传的文件（根据前台的name名称得到上传的文件）
        // MultipartFile file2 = multipartRequest.getFile("clientFile");

        MultiValueMap<String, MultipartFile> multiValueMap = multipartRequest
                .getMultiFileMap();
        //List<MultipartFile> file = multiValueMap.get("clientFile");
        Iterator<Entry<String, List<MultipartFile>>> iterator = multiValueMap
                .entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, List<MultipartFile>> entry = iterator.next();
            //String name = entry.getKey();
            List<MultipartFile> multipartFiles = entry.getValue();

            // 在这里就可以对file进行处理了，可以根据自己的需求把它存到数据库或者服务器的某个文件夹
            try {
                Resources r = saveFile(multipartFiles.get(0), type);
                resourcesMapper.insert(r);
                resources.add(r);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return resources;
    }

    // @RequestMapping("/uploadFile")
    // public String uploadFile(String fileName,
    // @RequestParam("clientFile") MultipartFile clientFile,
    // HttpSession session) {

    /**
     * 单文件上传
     */
    @RequestMapping("/uploadFile")
    @ResponseBody
    public Resources uploadFile(String fileName, String type,
                                @RequestParam("clientFile") MultipartFile clientFile,
                                HttpSession session) {
        Resources resources = null;
        if (!clientFile.isEmpty()) {
            // 在这里就可以对file进行处理了，可以根据自己的需求把它存到数据库或者服务器的某个文件夹
            try {
                resources = saveFile(clientFile, type);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return resources;
    }

    private Resources saveFile(MultipartFile multipartFile, String dir)
            throws IOException {

        if (physicalPath == null) {
            physicalPath = properties.getInstance().getProperty("uploadFiles");
        }
        String name = multipartFile.getOriginalFilename();
        String resAliasAme = IdUtils.getId();
        String fileType = name.substring(name.lastIndexOf('.') + 1);

        String path = "/" + fileType;
        if (dir != null) {
            path += "/" + dir;
        }
        File file = new File(physicalPath + path);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        path += "/" + resAliasAme + "." + fileType;
        file = new File(physicalPath + path);

//		FileOutputStream fos = FileUtils.openOutputStream(file);
//		IOUtils.copy(multipartFile.getInputStream(), fos);
//		fos.close();
        inputStreamToFile(multipartFile.getInputStream(), file.getPath());
        String fileMD5 = FileUtils.getMd5ByFile(file);

        Resources resources = new Resources();
        resources.setResId(resAliasAme);
        resources.setMd5(fileMD5);
        resources.setName(name);
        resources.setType(fileType);
        resources.setDateTime(new Date());
        resources.setPath(path);
        resources.setStatus(1);


        System.out.println("服务器地址：" + file.getPath() + "\nMD5=" + fileMD5);
        // resAction.Resource(resId,name,describe,path,request);
        return resources;

    }

    private void inputStreamToFile(InputStream stream, String filePaht) throws IOException {
        FileOutputStream fs = new FileOutputStream(filePaht);
        byte[] buffer = new byte[1024 * 1024];
        int byteread;
        while ((byteread = stream.read(buffer)) != -1) {
            fs.write(buffer, 0, byteread);
            fs.flush();
        }
        fs.close();
        stream.close();
    }

}