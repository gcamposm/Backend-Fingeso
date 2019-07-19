package fingeso.backend.controllers;

import fingeso.backend.dao.ProposalDao;
import fingeso.backend.models.Proposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/upload")
public class SaveFileController {
    @Autowired
    ServletContext context;
    @Autowired
    private ProposalDao proposalDao;

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Proposal> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("proposalId") String proposalId) throws IOException
    {
        //EN EL SERVER
        String relativeWebPath = "WEB-INF/classes/static";
        String serverPath = context.getRealPath(relativeWebPath);
        //LOCAL

        //FRONT
        //String absoluteFilePath = "../Symbiose-Front/public/static/";
        //BACK
        //String absoluteFilePath = "src/main/resources/static/";

        Proposal proposal = proposalDao.findProposalByIdStr(proposalId);
        Integer numberFile = proposal.getFiles().size();
        String nameFile = proposalId+ "_" + numberFile.toString() + ".pdf";
        File convertFile = new File(serverPath + "/" + nameFile);
        FileOutputStream fout = new FileOutputStream(convertFile);
        System.out.println(convertFile);
        fout.write(file.getBytes());
        fout.close();
        List<String> files = proposal.getFiles();
        files.add(nameFile);
        proposal.setFiles(files);
        return ResponseEntity.ok(proposalDao.save(proposal));
        //return ResponseEntity.ok(serverPath);
    }
    @RequestMapping(value = "/downloadFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> downloadFile(@RequestParam("fileName") String fileName) throws IOException
    {
        String absoluteFilePath = "/static/" + fileName;
        return ResponseEntity.ok(absoluteFilePath);
    }

    ///DOWNLOAD FILE IN THE SERVER

    @RequestMapping(value = "/getfile", method = RequestMethod.GET, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<InputStreamResource> getFile(@RequestParam("fileName") String fileName) throws FileNotFoundException {
        //System.out.println("en get file");
        MediaType mediaType = MediaType.parseMediaType("application/vnd.ms-excel");
        File file = new File(fileName); //the fileUtils is org.apache.commons.io.FileUtils;
        //FileUtils.writeByteArrayToFile(file, fileOptional.get()); // Hope that your get return a byte[] array
        //System.out.println(file.getAbsolutePath());

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(mediaType)
                // Contet-Length
                .contentLength(file.length()) //
                .body(resource);
    }
}
