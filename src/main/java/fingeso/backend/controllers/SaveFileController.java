package fingeso.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.FileDataSource;
import javax.servlet.ServletContext;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/upload")
public class SaveFileController {
    @Autowired
    ServletContext context;

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) throws IOException
    {
        String probarPdf = file.getOriginalFilename();
        if(isPDF(probarPdf)){
            String absoluteFilePath = "src/main/resources/static/";
            File convertFile = new File(absoluteFilePath + name + ".pdf");
            FileOutputStream fout = new FileOutputStream(convertFile);
            fout.write(file.getBytes());
            fout.close();
            return new ResponseEntity<>("file is uploaded successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("file isn't pdf", HttpStatus.NOT_ACCEPTABLE);
        }
    }
    public boolean isPDF(String fileName) {
        String[] words = fileName.split("\\.");
        String extension = words[1];
        return extension.equalsIgnoreCase("pdf");
    }
}
