package bkendfinalproject.finalpj.dao;

import bkendfinalproject.finalpj.entities.Prenotations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrenotazioniDAO extends JpaRepository<Prenotations, Long> {
}
