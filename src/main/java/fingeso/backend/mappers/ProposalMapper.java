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
        proposal.setName(proposalDto.getName());
        proposal.setDescription(proposalDto.getDescription());
        proposal.setCreated(proposalDto.getCreated());
        proposal.setUserId(proposalDto.getUserId());
        proposal.setClientId(proposalDto.getClientId());
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
        proposalDto.setName(proposal.getName());
        proposalDto.setDescription(proposal.getDescription());
        proposalDto.setCreated(proposal.getCreated());
        proposalDto.setClientId(proposal.getClientId());
        proposalDto.setUserId(proposal.getClientId());
        return proposalDto;
    }
}
