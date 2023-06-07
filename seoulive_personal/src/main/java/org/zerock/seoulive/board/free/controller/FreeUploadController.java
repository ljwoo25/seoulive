package org.zerock.seoulive.board.free.controller;

import lombok.Getter;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.seoulive.board.free.domain.AttachFileDTO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@Log4j2
@RequestMapping("/board/free/*")
public class FreeUploadController {

    @GetMapping("/uploadAjax")
    public String uploadAjax() {
        log.info("upload Ajax");
        return "board/free/uploadAjax";
    }


    private String getFolder() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();

        String str = sdf.format(date);

        return str.replace("-", File.separator);
    }

//     @PostMapping("/uploadAjaxAction")
//     public void uploadAjaxPost(MultipartFile[] uploadFile) {
//
//        String uploadFolder = "/Users/leedongyoung/upload";
//
//         // make folder --------
//         File uploadPath = new File(uploadFolder, getFolder());
//         log.info("upload path: " + uploadPath);
//
//         if (uploadPath.exists() == false) {
//         uploadPath.mkdirs();
//         }
//         // make yyyy/MM/dd folder
//
//         for (MultipartFile multipartFile : uploadFile) {
//
//         log.info("-------------------------------------");
//         log.info("Upload File Name: " + multipartFile.getOriginalFilename());
//         log.info("Upload File Size: " + multipartFile.getSize());
//
//        String uploadFileName = multipartFile.getOriginalFilename();
//
//        // IE has file path
//        uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
//        log.info("only file name: " + uploadFileName);
//
//         UUID uuid = UUID.randomUUID();
//         uploadFileName = uuid.toString() + "_" + uploadFileName;
//
//         File saveFile = new File(uploadPath,uploadFileName);
//
//
//         try {
//
//         multipartFile.transferTo(saveFile);
//         } catch (Exception e) {
//
//         log.error(e.getMessage());
//
//         } // end catch
//
//         } // end for
//
//     }

    @PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {

        List<AttachFileDTO> list = new ArrayList<>();
        String uploadFolder = "/Users/leedongyoung/upload";

        String uploadFolderPath = getFolder();
        // make folder --------
        File uploadPath = new File(uploadFolder, uploadFolderPath);

        if (uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }
        // make yyyy/MM/dd folder

        for (MultipartFile multipartFile : uploadFile) {

            AttachFileDTO attachDTO = new AttachFileDTO();

            String uploadFileName = multipartFile.getOriginalFilename();

            // IE has file path
            uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
            log.info("only file name: " + uploadFileName);
            attachDTO.setFileName(uploadFileName);

            UUID uuid = UUID.randomUUID();

            uploadFileName = uuid.toString() + "_" + uploadFileName;

            try {
                File saveFile = new File(uploadPath, uploadFileName);
                multipartFile.transferTo(saveFile);

                attachDTO.setUuid(uuid.toString());
                attachDTO.setUploadPath(uploadFolderPath);

                // check image type file
                if (checkImageType(saveFile)) {

                    attachDTO.setImage(true);

                    FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));

                    Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);

                    thumbnail.close();
                }

                // add to List
                list.add(attachDTO);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } // end for
        return new ResponseEntity<>(list, HttpStatus.OK);
    }




    private boolean checkImageType(File file) {

        try {
            String contentType = Files.probeContentType(file.toPath());

            return contentType.startsWith("image");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }

    @GetMapping("/display")
    @ResponseBody
    public ResponseEntity<byte[]> getFile(String fileName) {
        log.info("fileName : {}",fileName);

        File file = new File("/Users/leedongyoung/upload/"+fileName);

        log.info("file: " + file);

        ResponseEntity<byte[]> result = null;

        try {
            HttpHeaders headers = new HttpHeaders();

            headers.add("Content-Type",Files.probeContentType(file.toPath()));
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),headers,HttpStatus.OK );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping(value = "/download",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(String fileName)     {
        log.info("download file: {}",fileName);
        Resource resource = new FileSystemResource("/Users/leedongyoung/upload/"+fileName);

        log.info("resource : {}",resource);

        String resourceName = resource.getFilename();

        HttpHeaders headers = new HttpHeaders();
        try {
            headers.add("Content-Disposition","attachment; filename=" + new String(resourceName.getBytes("UTF-8"),"ISO-8859-1"));
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);
    }



}
