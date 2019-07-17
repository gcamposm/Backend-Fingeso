package fingeso.backend.controllers;

import fingeso.backend.dao.ProposalDao;
import fingeso.backend.models.Proposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    private Integer index = 0;

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Proposal uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("proposalId") String proposalId) throws IOException
    {
        String absoluteFilePath = "../Symbiose-Front/public/static/";
        String nameFile = proposalId+ "_" + index.toString() + ".pdf";
        File convertFile = new File(absoluteFilePath + nameFile);
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();
        Proposal proposal = proposalDao.findProposalByIdStr(proposalId);
        List<String> files = proposal.getFiles();
        files.add(nameFile);
        proposal.setFiles(files);
        return proposalDao.save(proposal);
    }
    @RequestMapping(value = "/getfile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> downloadFile(@RequestParam("fileName") String fileName, @RequestParam("proposalId") String proposalId) throws IOException
    {
        String absoluteFilePath = "/static/" + fileName;
        return ResponseEntity.ok(absoluteFilePath);
    }
}
