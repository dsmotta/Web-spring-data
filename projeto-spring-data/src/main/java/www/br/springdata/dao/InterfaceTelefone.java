package www.br.springdata.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import www.br.springdata.model.Telefone;

@Repository
public interface InterfaceTelefone extends CrudRepository<Telefone, Long> {
	
	
	

}
