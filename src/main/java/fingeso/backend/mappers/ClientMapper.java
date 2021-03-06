package fingeso.backend.mappers;

import fingeso.backend.dto.ClientDto;
import fingeso.backend.models.Client;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientMapper {
    public Client mapToModel(ClientDto clientDto){

        Client client = new Client();
        client.setIdStr(clientDto.getIdStr());
        client.setName(clientDto.getName());
        client.setCompany(clientDto.getCompany());
        client.setProposals(clientDto.getProposals());
        client.setScore(clientDto.getScore());
        return client;
    }

    public List<ClientDto> mapToDtoArrayList(List<Client> clientArrayList) {
        int i;

        List<ClientDto> clientDtoArrayList = new ArrayList<>();
        for(i=0;i<clientArrayList.size();i++){
            clientDtoArrayList.add(mapToDto(clientArrayList.get(i)));
        }

        return clientDtoArrayList;
    }

    public ClientDto mapToDto (Client client){

        ClientDto clientDto = new ClientDto();
        clientDto.setIdStr(client.getIdStr());
        clientDto.setName(client.getName());
        clientDto.setCompany(client.getCompany());
        clientDto.setProposals(client.getProposals());
        clientDto.setScore(client.getScore());
        return clientDto;
    }
}
