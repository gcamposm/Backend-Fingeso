package fingeso.backend.controllers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import fingeso.backend.dao.ClientDao;
import fingeso.backend.dao.ProposalDao;
import fingeso.backend.dao.UserDao;
import fingeso.backend.models.Client;
import fingeso.backend.models.Proposal;
import fingeso.backend.models.User;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pdfreport")
public class PdfController {

    @Autowired
    private ProposalDao proposalDao;

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<String> generatePdf(@RequestParam("proposalId") String proposalId) throws IOException {
        //System.out.println(proposalId);
        Proposal proposal = proposalDao.findProposalByIdStr(proposalId);
        Client client = clientDao.findBy_id(proposal.getClientId());
        User user = userDao.findBy_id(proposal.getUserId());
        String absoluteFilePath = "../Symbiose-Front/public/static/";
        String fileName = "generatedPdf_" + proposalId + ".pdf";
        File convertFile = new File(absoluteFilePath + fileName);
        FileOutputStream generated = new FileOutputStream(convertFile);
        ByteArrayInputStream bis = reporte(proposal, client, user);
        IOUtils.copy(bis, generated);
        generated.close();
        absoluteFilePath = "/static/" + fileName;
        return ResponseEntity.ok(absoluteFilePath);
    }
    private static final Logger logger = LoggerFactory.getLogger(PdfController.class);
    public static ByteArrayInputStream reporte(Proposal proposal, Client client, User user) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfPTable table = new PdfPTable(9);
            //table.setTotalWidth(60);
            //table.setWidths(new int[]{10, 5, 5, 10, 5, 5, 5, 10, 5});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Id", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Nombre", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Archivos Asociados", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Descripción", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Cliente", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Presupuesto", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Tamaño de equipo", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Creada", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Creador", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase(proposal.getIdStr()));
            hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase(proposal.getName()));
            hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase(String.valueOf(proposal.getFiles())));
            hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase(proposal.getDescription()));
            hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase(client.getName()));
            hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
            //hcell.setPaddingRight(5);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase(proposal.getBudget().toString()));
            hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase(proposal.getTeamSize().toString()));
            hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase(proposal.getCreated().toString()));
            hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase(user.getFirstName()+" "+user.getLastName()));
            hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);

            document.close();

        } catch (DocumentException ex) {

            logger.error("Error occurred: {0}", ex);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

}