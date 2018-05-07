/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splendidworks.notepad_server_side.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import splendidworks.notepad_server_side.model.ImageInfo;

/**
 *
 * @author Savvas
 */
@Controller
@RequestMapping("upload")
public class FileuploadController {

    private final ImageInfo image = new ImageInfo();

    public ImageInfo getImage() {
        return image;
    }
    
    @Autowired
    ServletContext context;

    @RequestMapping(value = "/image", headers = ("Accept=application/json"), method = RequestMethod.POST)
    public ResponseEntity<ImageInfo> singleFileUpload(@RequestParam("file") MultipartFile file)
            throws IOException {

        
        HttpHeaders headers = new HttpHeaders();

        // Save file on system
        if (!file.getOriginalFilename().isEmpty()) {
            BufferedOutputStream outputStream = new BufferedOutputStream(
                    new FileOutputStream(
                            new File("C:\\xampp\\tomcat\\webapps\\uploads", file.getOriginalFilename())));
            outputStream.write(file.getBytes());
            outputStream.flush();
            outputStream.close();
            image.setFileName(file.getOriginalFilename());
            image.setFileSize(file.getSize());

            return new ResponseEntity<ImageInfo>(image, headers, HttpStatus.OK);

        } else {
            return new ResponseEntity<ImageInfo>(HttpStatus.BAD_REQUEST);

        }

    }
}
