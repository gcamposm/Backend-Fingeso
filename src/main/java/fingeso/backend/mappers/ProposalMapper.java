package fingeso.backend.mappers;

import fingeso.backend.dto.ProposalDto;
import fingeso.backend.models.Proposal;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProposalMapper {
    public Proposal mapToModel(ProposalDto proposalDto){

        Proposal proposal = new Proposal();
        proposal.setIdStr(proposalDto.getIdStr());
        proposal.setName(proposalDto.getName());
        proposal.setDescription(proposalDto.getDescription());
        proposal.setCreated(proposalDto.getCreated());
        proposal.setUserId(proposalDto.getUserId());
        proposal.setClientId(proposalDto.getClientId());
        proposal.setClientIdStr(proposalDto.getClientIdStr());
        proposal.setBudget(proposalDto.getBudget());
        proposal.setTeamSize(proposalDto.getTeamSize());
        proposal.setFiles(proposalDto.getFiles());
        return proposal;
    }

    public List<ProposalDto> mapToDtoArrayList(List<Proposal> proposalArrayList) {
        int i;

        List<ProposalDto> proposalDtoArrayList = new ArrayList<>();
        for(i=0;i<proposalArrayList.size();i++){
            proposalDtoArrayList.add(mapToDto(proposalArrayList.get(i)));
        }

        return proposalDtoArrayList;
    }

    public ProposalDto mapToDto (Proposal proposal){

        ProposalDto proposalDto = new ProposalDto();
        proposalDto.setIdStr(proposal.getIdStr());
        proposalDto.setName(proposal.getName());
        proposalDto.setDescription(proposal.getDescription());
        proposalDto.setCreated(proposal.getCreated());
        proposalDto.setClientId(proposal.getClientId());
        proposalDto.setClientIdStr(proposal.getClientIdStr());
        proposalDto.setUserId(proposal.getClientId());
        proposalDto.setFiles(proposal.getFiles());
        proposalDto.setBudget(proposal.getBudget());
        proposalDto.setTeamSize(proposal.getTeamSize());
        return proposalDto;
    }
}
