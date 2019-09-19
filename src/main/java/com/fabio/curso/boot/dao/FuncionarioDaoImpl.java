package com.fabio.curso.boot.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.fabio.curso.boot.domain.Cargo;
import com.fabio.curso.boot.domain.Funcionario;

@Repository
public class FuncionarioDaoImpl extends AbstractDao<Funcionario> implements FuncionarioDao {

	@Override
	public List<Funcionario> findByNome(String nome) {
		List<Funcionario> query = getEntityManager().createQuery("").getResultList();
		
		// TODO Auto-generated method stub
		return null;
	}

}
