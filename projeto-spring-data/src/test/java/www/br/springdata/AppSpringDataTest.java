package www.br.springdata;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import www.br.springdata.dao.InterfaceSpringDataUser;
import www.br.springdata.dao.InterfaceTelefone;
import www.br.springdata.model.Telefone;
import www.br.springdata.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring-config.xml"})
public class AppSpringDataTest {
	
	@Autowired
	private InterfaceSpringDataUser InterfaceSpringDataUser;
	
	@Autowired
	private InterfaceTelefone interfaceTelefone;
	
	
	@Test
	public void testeInsert() {
		
		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();
		
		usuarioSpringData.setEmail("draff@hotmail.com");
		usuarioSpringData.setIdade(22);
		usuarioSpringData.setLogin("dsm123");
		usuarioSpringData.setSenha("123");
		usuarioSpringData.setNome("Marui Silva");
		
		InterfaceSpringDataUser.save(usuarioSpringData);
		
		System.out.println("Usuarios cadastrados: " + InterfaceSpringDataUser.count());
	}
	
	
	@Test
	public void testeConsulta() {
	
		Optional<UsuarioSpringData> usuarioSpringData = InterfaceSpringDataUser.findById(1L);
		
		System.out.println(usuarioSpringData.get().getEmail());
		System.out.println(usuarioSpringData.get().getIdade());
		System.out.println(usuarioSpringData.get().getLogin());
		System.out.println(usuarioSpringData.get().getSenha());
		System.out.println(usuarioSpringData.get().getNome());
		
		for (Telefone telefones : usuarioSpringData.get().getTelefones()) {
			
			System.out.println(telefones.getTipo());
			System.out.println(telefones.getNumero());
			
		}
		
		
	}
	
	@Test
	public void consultaTodos() {
		
		Iterable<UsuarioSpringData> lista = InterfaceSpringDataUser.findAll();
		
		for (UsuarioSpringData usuarioSpringData : lista) {
			
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println(usuarioSpringData.getNome());
			System.out.println("---------------------------------------------------------------------------");
			
		}
		
	}
	
	@Test
	public void testeUpdate() {
		
		Optional<UsuarioSpringData>  usuarioSpringData = InterfaceSpringDataUser.findById(2L);
		
		UsuarioSpringData usuario = usuarioSpringData.get();
		
		usuario.setNome("Update");
		usuario.setIdade(47);
		
		InterfaceSpringDataUser.save(usuario);
		
	}
	
	@Test
	public void testeDelete() {//Metodo delete pode ser feito de duas formas: direto e consultando o objeto depois excluindo
		
		//metodo direto
		//InterfaceSpringDataUser.deleteById(6L);
		
		//metodo consultando objeto 
		Optional<UsuarioSpringData> usuarioSpringData = InterfaceSpringDataUser.findById(4L);
		
		InterfaceSpringDataUser.delete(usuarioSpringData.get());
		
	}
	
	@Test
	public void testeConsultaNome() {
		
		List<UsuarioSpringData> list = InterfaceSpringDataUser.buscaPorNome("Douglas");
		
		for (UsuarioSpringData usuarioSpringData : list) {
			
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println(usuarioSpringData.getNome());
			System.out.println("---------------------------------------------------------------------------");
			
			
		}
		
	}
	
	@Test
	public void testeConsultaNomeParam() {
		
		UsuarioSpringData usuarioSpringData = InterfaceSpringDataUser.buscaPorNomeParam("Douglas");
		
			
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println(usuarioSpringData.getNome());
			System.out.println("---------------------------------------------------------------------------");
			
			
	}
	
	@Test
	public void testePorNome() {
		
		InterfaceSpringDataUser.deletePorNome("Douglas Souza");
		
	}
	
	@Test
	public void testeUpdateEmailPorNome() {
		
		InterfaceSpringDataUser.updateEmailPorNome("dsmotta@hotmail.com", "Douglas");
		
	}
	
	@Test
	public void testeInsertTelefone() {
		
		Optional<UsuarioSpringData> usuarioSpringData = InterfaceSpringDataUser.findById(2L);
		
		Telefone telefone = new Telefone();
		
		telefone.setTipo("Iphone");
		telefone.setNumero("88888888888888888888888");
		telefone.setUsuarioSpringData(usuarioSpringData.get());
		
		interfaceTelefone.save(telefone);
		
	}

}
