package fingeso.backend.services;

import fingeso.backend.dao.ProposalDao;
import fingeso.backend.dto.ProposalDto;
import fingeso.backend.mappers.ProposalMapper;
import fingeso.backend.models.Proposal;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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

    public ProposalDto createProposal(ProposalDto proposalDto){
        return proposalMapper.mapToDto(proposalDao.save(proposalMapper.mapToModel(proposalDto)));
    }
}
