package br.com.cartoes.pagamentos.gateway.data.repository;

import br.com.cartoes.pagamentos.gateway.data.TransacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface TransacaoRepository extends JpaRepository<TransacaoEntity, Long> {

    @Query("SELECT SUM(t.liquido) FROM TransacaoEntity t WHERE t.disponivel <= ?1")
    BigDecimal findSomaLiquidoByDisponivelBefore(LocalDate dataAtual);

    @Query("SELECT SUM(t.liquido) FROM TransacaoEntity t WHERE t.disponivel > ?1")
    BigDecimal findSomaLiquidoByDisponivelAfter(LocalDate dataAtual);

}
