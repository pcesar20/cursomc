package com.pc.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pc.cursomc.domain.Categoria;
import com.pc.cursomc.domain.Cidade;
import com.pc.cursomc.domain.Cliente;
import com.pc.cursomc.domain.Endereco;
import com.pc.cursomc.domain.Estado;
import com.pc.cursomc.domain.Produto;
import com.pc.cursomc.domain.enums.TipoCliente;
import com.pc.cursomc.repositories.CategoriaRepository;
import com.pc.cursomc.repositories.CidadeRepository;
import com.pc.cursomc.repositories.ClienteRepository;
import com.pc.cursomc.repositories.EnderecoRepository;
import com.pc.cursomc.repositories.EstadoRepository;
import com.pc.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository catRepo;


	@Autowired
	private ProdutoRepository prodRepo;
	
	@Autowired
	private CidadeRepository cidadeRepo;

	@Autowired
	private EstadoRepository estadoRepo;
	
	@Autowired
	private EnderecoRepository enderecoRepo;
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Escritorio");
		Categoria cat2 = new Categoria(null, "Informatica");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Mesa", 1000.00);
		Produto p3 = new Produto(null, "Mouse", 100.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null, "Goiás");
		Estado est2 = new Estado(null, "Minas Gerais");
		
		Cidade cid1 = new Cidade(null, "Aparecida", est1);
		Cidade cid2 = new Cidade(null, "Montes Claros", est2);
		Cidade cid3 = new Cidade(null, "BH", est2);
		
		Cliente cli1 = new Cliente(null, "Paulo Cesar", "test@PC.com", "0000000000000", TipoCliente.PESSOAFISICA);
		Cliente cli2 = new Cliente(null, "Thais Rocha", "test@PC.com", "0000000000000", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("3535353535", "555555555"));
		
		Endereco e1 = new Endereco(null, "Rua l21a", "1", "QD 57 LT 25", "papillon", "74950270", cli1, cid1);
		Endereco e2 = new Endereco(null, "Rua l21a", "1", "QD 57 LT 25", "papillon", "74950270", cli2, cid2);
		
		cli1.getEndereco().addAll(Arrays.asList(e1));
		cli2.getEndereco().addAll(Arrays.asList(e2));
		
		estadoRepo.saveAll(Arrays.asList(est1, est2));
		cidadeRepo.saveAll(Arrays.asList(cid1, cid2, cid3));		
		catRepo.saveAll(Arrays.asList(cat1, cat2));
		prodRepo.saveAll(Arrays.asList(p1, p2, p3));
		clienteRepo.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepo.saveAll(Arrays.asList(e1, e2));

	}

}
