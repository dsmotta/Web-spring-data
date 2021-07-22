package www.br.springdata.dao;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import www.br.springdata.model.UsuarioSpringData;

@Repository
public interface InterfaceSpringDataUser extends CrudRepository<UsuarioSpringData, Long> {
	
	@org.springframework.transaction.annotation.Transactional(readOnly = false) //não deixa fazer modificações no banco
	@Query(value="select p from UsuarioSpringData p where p.nome like %?1%")
	public java.util.List<UsuarioSpringData> buscaPorNome(String nome);
	
	//Este metodo pesquisa o nome como parametro e retorno um objeto obs: o nome tem que ser identico ao do banco
	
	@Lock(LockModeType.READ) //controla acessos simultâneos
	@org.springframework.transaction.annotation.Transactional(readOnly = false) //não deixa fazer modificações no banco
	@Query(value="select p from UsuarioSpringData p where p.nome = :paramnome")
	public UsuarioSpringData buscaPorNomeParam(@Param("paramnome") String paramnome);
	
	/*//Metodo onde pode ser executado alguma rotina antes de salvar no banco
	
	@Override
	default <S extends UsuarioSpringData> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	
	@Modifying
	@Transactional(dontRollbackOn = NullPointerException.class) // ao efetuar a transação se houver um NullPointerException faz um rollback no banco
	@Query(value="delete from UsuarioSpringData u where u.nome = ?1")
	public void deletePorNome(String nome);
	
	
	@Modifying
	@Transactional
	@Query(value="update UsuarioSpringData u set u.email = ?1 where u.nome = ?2")
	public void updateEmailPorNome(String email, String nome);
		
	
	
	
}
