package fingeso.backend.services;

import fingeso.backend.dao.ClientDao;
import fingeso.backend.dto.ClientDto;
import fingeso.backend.mappers.ClientMapper;
import fingeso.backend.models.Client;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientDao clientDao;

    @Autowired
    private ClientMapper clientMapper;

    public List<ClientDto> getAllClients(){
        List<Client> clientList = clientDao.findAll();
        return clientMapper.mapToDtoArrayList(clientList);
    }

    public ClientDto getClientById(ObjectId id){
        if(clientDao.existsBy_id(id)){
            return clientMapper.mapToDto(clientDao.findBy_id(id));
        }
        else{
            return  null;
        }
    }
    public void updateClient(ClientDto clientDto, ObjectId id){
        if(clientDao.existsBy_id(id)){
            Client clientFinded = clientDao.findBy_id(id);
            clientFinded.setName(clientDto.getName());
            clientFinded.setCompany(clientDto.getCompany());
            clientFinded.setProposals(clientDto.getProposals());
            clientDao.save(clientFinded);

        }

    }
    public void deleteClient(ObjectId id){
        Client client = clientDao.findBy_id(id);
        clientDao.delete(client);

    }

    public ClientDto createClient(ClientDto clientDto){
        return clientMapper.mapToDto(clientDao.save(clientMapper.mapToModel(clientDto)));
    }
}
