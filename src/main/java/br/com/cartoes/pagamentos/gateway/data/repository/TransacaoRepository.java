package br.com.cartoes.pagamentos.gateway.data.repository;

import br.com.cartoes.pagamentos.gateway.data.TransacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<TransacaoEntity, Long> {
}
