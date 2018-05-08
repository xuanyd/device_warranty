package com.base.admin.controller;

import com.baidu.ueditor.ActionEnter;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class UploadController {
    @RequestMapping(value="/uploadimage")
    public
    @ResponseBody
    Map uploadImage(@RequestParam(value = "file", required = false) MultipartFile file,
                           HttpServletRequest request, HttpServletResponse response) throws Exception{
        Map<String, Object> m = new HashMap<String, Object>();
        // 对上传文件夹和临时文件夹进行初始化
        String rootDir = request.getSession().getServletContext().getRealPath("");
        String tmpDir = rootDir + "/tmp";
        File tmpDirPath = new File(tmpDir);
        if (ServletFileUpload.isMultipartContent(request)) {
            request.setCharacterEncoding("utf-8");
            DiskFileItemFactory dff = new DiskFileItemFactory();// 创建该对象
            dff.setRepository(tmpDirPath);// 指定上传文件的临时目录
            dff.setSizeThreshold(2 * 1024 * 1024);// 指定在内存中缓存数据大小,单位为byte
            ServletFileUpload sfu = new ServletFileUpload(dff);// 创建该对象
            sfu.setFileSizeMax(1000000000);// 指定单个上传文件的最大尺寸
            sfu.setSizeMax(1000000000);// 指定一次上传多个文件的总尺寸
            FileItemIterator fii = sfu.getItemIterator(request);// 解析request
            DefaultMultipartHttpServletRequest mfRequest = (DefaultMultipartHttpServletRequest) request;
            MultiValueMap<String, MultipartFile> multipartFiles = mfRequest.getMultiFileMap();
            MultipartFile multipartFile = multipartFiles.get("upfile").get(0);
            if(!multipartFile.isEmpty()) {
                String filename = multipartFile.getOriginalFilename();
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String[] FileName = filename.split("\\.");
                String nowdate = sdf.format(date);
                String preFile = FileName[0];
                String endFile = FileName[1];
                String newFileName = preFile + "_" + nowdate + "." + endFile;
                File appDir = new File(rootDir);
                if (!appDir.isDirectory()) {
                    appDir.mkdir();
                }
                // 创建按月分类的子文件夹
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH )+1;
                String currentMonth = (year + month) + "";
                File appSubDir = new File(appDir + "/" + currentMonth);
                if (!appSubDir.isDirectory()) {
                    appSubDir.mkdir();
                }
                String newFilepath = appSubDir + "/" + newFileName;
                BufferedInputStream in = new BufferedInputStream(multipartFile.getInputStream());// 获得文件输入流
                BufferedOutputStream out =
                        new BufferedOutputStream(new FileOutputStream(new File(newFilepath)));// 获得文件输出流
                Streams.copy(in, out, true);// 开始把文件写到你指定的上传文件夹
                m.put("path", "/" + currentMonth + "/");
                m.put("filename", newFileName);
                m.put("original", filename);
                m.put("name", newFileName);
                m.put("url", "/" + currentMonth + "/"+newFileName);
                m.put("state", "SUCCESS");
                m.put("type", ".jpg");
                m.put("size", "99697");
                out.flush();
                out.close();
            }
        }
        return m;
    }

    public Map<String, Object> saveFile(MultipartFile multipartFile,String rootDir, String subPath) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        String tmpDir = rootDir + "/tmp";
        File tmpDirPath = new File(tmpDir);
        DiskFileItemFactory dff = new DiskFileItemFactory();// 创建该对象
        dff.setRepository(tmpDirPath);// 指定上传文件的临时目录
        dff.setSizeThreshold(2 * 1024 * 1024);// 指定在内存中缓存数据大小,单位为byte
        ServletFileUpload sfu = new ServletFileUpload(dff);// 创建该对象
        sfu.setFileSizeMax(1000000000);// 指定单个上传文件的最大尺寸
        sfu.setSizeMax(1000000000);// 指定一次上传多个文件的总尺寸
        if (!multipartFile.isEmpty()) {
            String filename = multipartFile.getOriginalFilename();
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String[] FileName = filename.split("\\.");
            String nowdate = sdf.format(date);
            String preFile = FileName[0];
            String endFile = FileName[1];
            String newFileName = preFile + "_" + nowdate + "." + endFile;
            File appDir = new File(rootDir);
            if (!appDir.isDirectory()) {
                appDir.mkdir();
            }
            // 创建按月分类的子文件夹
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            String currentMonth = (year + month) + "";
            File appSubDir = new File(appDir + "/" + subPath + "/" + currentMonth);
            if (!appSubDir.isDirectory()) {
                appSubDir.mkdir();
            }
            String newFilepath = appSubDir + "/" + newFileName;
            BufferedInputStream in = new BufferedInputStream(multipartFile.getInputStream());// 获得文件输入流
            BufferedOutputStream out =
                    new BufferedOutputStream(new FileOutputStream(new File(newFilepath)));// 获得文件输出流
            Streams.copy(in, out, true);// 开始把文件写到你指定的上传文件夹
            out.flush();
            out.close();
            m.put("path", subPath + "/" + currentMonth + "/");
            m.put("filename", newFileName);
            m.put("original", filename);
            m.put("name", newFileName);
            m.put("url", subPath + "/" + currentMonth + "/"+newFileName);
        }
        return m;
    }

    @RequestMapping(value="/uploadfile")
    public
    @ResponseBody
    Map uploadFile( HttpServletRequest request, HttpServletResponse response) throws Exception{
        String rootDir = request.getSession().getServletContext().getRealPath("");
        String tmpDir = rootDir + "/tmp";
        DefaultMultipartHttpServletRequest mfRequest = (DefaultMultipartHttpServletRequest) request;
        Enumeration params = mfRequest.getParameterNames();
        String uploadType = null;
        while (params.hasMoreElements()) {
            String name = (String)params.nextElement();
            uploadType = mfRequest.getParameter("uploadType");
        }
        if(uploadType.equals("courseware")) {
            MultiValueMap<String, MultipartFile> multipartFiles = mfRequest.getMultiFileMap();
            Map<String, MultipartFile> file2 = mfRequest.getFileMap();
            MultipartFile multipartFile = multipartFiles.get("file").get(0);
            String filename = multipartFile.getOriginalFilename();
            Map<String, Object> newFileInfo = saveFile(multipartFile, rootDir, "courseware");
            newFileInfo.put("state", "SUCCESS");
            newFileInfo.put("type", ".jpg");
            newFileInfo.put("size", "99697");
            return newFileInfo;
        } else if (uploadType.equals("vedio")) {
            MultiValueMap<String, MultipartFile> multipartFiles = mfRequest.getMultiFileMap();
            Map<String, MultipartFile> file2 = mfRequest.getFileMap();
            MultipartFile multipartFile = multipartFiles.get("file").get(0);
            String filename = multipartFile.getOriginalFilename();
            Map<String, Object> newFileInfo = saveFile(multipartFile, rootDir, "vedio");
            newFileInfo.put("state", "SUCCESS");
            newFileInfo.put("type", ".jpg");
            newFileInfo.put("size", "99697");
            return newFileInfo;
        }
        return null;
    }


    @RequestMapping(value="/ueditorConfig")
    public @ResponseBody
    void ueditorConfig(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("-------config----");
        response.setContentType("application/json");
        String rootPath = request.getSession()
                .getServletContext().getRealPath("/");
        System.out.println(rootPath);
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            System.out.println(exec);
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
