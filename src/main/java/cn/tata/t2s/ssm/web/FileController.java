package cn.tata.t2s.ssm.web;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
@RequestMapping("/{identity}")
public class FileController {  
  
  
    /** 
     * 文件上传 
     *  
     * @author：tuzongxun 
     * @Title: uploadFile 
     * @param @param file 
     * @param @param request 
     * @param @throws IllegalStateException 
     * @param @throws IOException 
     * @return void 
     * @date Apr 28, 2016 1:22:00 PM 
     * @throws 
     */  
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)  
    public void upLoadFile(
    		HttpServletRequest request, 
    		@PathVariable("identity") String identity)  
            throws IllegalStateException, IOException {  
        // @RequestParam("file") MultipartFile file,  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(  
                request.getSession().getServletContext());  
        // 判断 request 是否有文件上传,即多部分请求  
        if (multipartResolver.isMultipart(request)) {  
            // 转换成多部分request  
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;  
            // 取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            while (iter.hasNext()) {  
                // 取得上传文件  
                MultipartFile f = multiRequest.getFile(iter.next());  
                if (f != null) {  
                    // 取得当前上传文件的文件名称  
                    String myFileName = f.getOriginalFilename();  
                    // 如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if (myFileName.trim() != "") {  
                        // 定义上传路径  
                        String path = "/home/chan/Documents/"  
                                + identity + "/" +myFileName;
                        System.out.println(path);
                        File localFile = new File(path);  
                        f.transferTo(localFile);  
                    }  
                }  
            }  
        }  
    }
    
    /** 
     * 文件下载，需要文件名和文件地址 
     * 
     * @author：tuzongxun 
     * @Title: download 
     * @param@param name 
     * @param@param path 
     * @param@return 
     * @param@throws IOException 
     * @returnResponseEntity<byte[]> 
     * @date Apr 28,2016 1:21:32 PM 
     * @throws 
     */  
    @RequestMapping(value = "/downloadFile")  
    public ResponseEntity<byte[]> download(@RequestParam("name") String name,  
           @RequestParam("filePath") String path) throws IOException {  
       File file = new File(path);  
       HttpHeaders headers = new HttpHeaders();  
       String fileName = new String(name.getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题  
       headers.setContentDispositionFormData("attachment", fileName);  
       headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
       return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),  
              headers, HttpStatus.CREATED);  
    }  
}  
