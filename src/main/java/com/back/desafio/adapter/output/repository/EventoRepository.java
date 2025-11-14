package com.back.desafio.adapter.output.repository;

import com.back.desafio.adapter.output.entity.EventoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository<EventoEntity, Long> {

    @Query("SELECT e FROM EventoEntity e WHERE e.id = :id AND e.ativo = true")
    Optional<EventoEntity> findByIdAndAtivoTrue(@Param("id") Long id);

    @Query("SELECT COUNT(e) FROM EventoEntity e WHERE e.ativo = true")
    Integer buscarQuantidadeEventosAtivos();

    Page<EventoEntity> findByAtivoTrue(Pageable pageable);

    @Modifying
    @Query("UPDATE EventoEntity e SET e.ativo = false WHERE e.id = :id")
    void desativarEvento(@Param("id") Long id);

}
