package fingeso.backend.services;

import fingeso.backend.dao.ProposalDao;
import fingeso.backend.dto.ProposalDto;
import fingeso.backend.mappers.ProposalMapper;
import fingeso.backend.models.Proposal;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class ProposalService {
    @Autowired
    private ProposalDao proposalDao;

    @Autowired
    private ProposalMapper proposalMapper;

    public List<ProposalDto> getAllProposals(){
        List<Proposal> proposalList = proposalDao.findAll();
        return proposalMapper.mapToDtoArrayList(proposalList);
    }

    public ProposalDto getProposalById(ObjectId id){
        if(proposalDao.existsBy_id(id)){
            return proposalMapper.mapToDto(proposalDao.findBy_id(id));
        }
        else{
            return  null;
        }
    }
    public void updateProposal(ProposalDto proposalDto, ObjectId id){
        if(proposalDao.existsBy_id(id)){
            Proposal proposalFinded = proposalDao.findBy_id(id);
            proposalFinded.setName(proposalDto.getName());
            proposalFinded.setDescription(proposalDto.getDescription());
            proposalFinded.setCreated(proposalDto.getCreated());
            proposalFinded.setClientId(proposalDto.getClientId());
            proposalFinded.setUserId(proposalDto.getUserId());
            proposalDao.save(proposalFinded);

        }

    }
    public void deleteProposal(ObjectId id){
        Proposal proposal = proposalDao.findBy_id(id);
        proposalDao.delete(proposal);

    }

    public Proposal createProposal(){
        Random random = new Random();
        List<Proposal> proposalList = proposalDao.findAll();
        Proposal proposal = proposalList.get(random.nextInt(proposalList.size() - 1));
        Date now = new Date();
        ObjectId id = ObjectId.get();
        proposal.set_id(id);
        proposal.setIdStr(id.toHexString());
        proposal.setDescription("");
        proposal.setName("");
        proposal.setFiles(new ArrayList<>());
        proposal.setUserId(null);
        proposal.setClientId(null);
        proposal.setCreated(now);
        return proposalDao.save(proposal);
    }
}
