package bkendfinalproject.finalpj.dao;

import bkendfinalproject.finalpj.entities.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsDAO extends JpaRepository<Events, Long> {
}
