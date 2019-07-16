package fingeso.backend.controllers;

import fingeso.backend.dao.ProposalDao;
import fingeso.backend.models.Proposal;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("proposalId") String proposalId) throws IOException
    {
        String nameFile = file.getOriginalFilename();
        String absoluteFilePath = "src/main/resources/static/";
        File convertFile = new File(absoluteFilePath + nameFile);
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();
        Proposal proposal = proposalDao.findProposalByIdStr(proposalId);
        List<String> files = proposal.getFiles();
        files.add(nameFile);
        proposal.setFiles(files);
        proposalDao.save(proposal);
        return new ResponseEntity<>("file is uploaded successfully", HttpStatus.OK);
    }
}
