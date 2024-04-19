package bkendfinalproject.finalpj.services;

import bkendfinalproject.finalpj.dao.EventsDAO;
import bkendfinalproject.finalpj.dao.PrenotazioniDAO;
import bkendfinalproject.finalpj.dao.UserDAO;
import bkendfinalproject.finalpj.entities.Events;
import bkendfinalproject.finalpj.entities.Prenotations;
import bkendfinalproject.finalpj.entities.User;
import bkendfinalproject.finalpj.exceptions.NotFoundException;
import bkendfinalproject.finalpj.payloads.EventsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EventsServices {

    @Autowired
    private EventsDAO eDAO;

    @Autowired
    private UserServices us;

    @Autowired
    private PrenotazioniDAO pDAO;

    @Autowired
    private UserDAO uDAO;

    public Page<Events> getEvents(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable p = PageRequest.of(page, size, Sort.by(sortBy));
        return eDAO.findAll(p);
    }

    public Events save(EventsDTO newEvent){
        return eDAO.save(new Events(
                newEvent.titolo(),
                newEvent.descrizione(),
                newEvent.data(),
                newEvent.luogo(),
                newEvent.postiDisponibili()
                ));
    }

    public Events findById(long id) {
        return this.eDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(long id) {
        Events found = this.findById(id);
        this.eDAO.delete(found);
    }

    public Events findByIdAndUpdate(long id, EventsDTO newEvents) {
        Events found = this.findById(id);
        found.setTitolo(newEvents.titolo());
        found.setDescrizione(newEvents.descrizione());
        found.setLuogo(newEvents.luogo());
        found.setData(newEvents.data());
        found.setPostiDisponibili(newEvents.postiDisponibili());
        eDAO.save(found);
        return found;
    }

    public void prenotaEvento(Long eventoId, Long userId, int postiPrenotati) {
        Events evento = eDAO.findById(eventoId)
                .orElseThrow(() -> new IllegalArgumentException("Evento non trovato"));

        User utente = uDAO.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));

        int postiDisponibili = evento.getPostiDisponibili();

        if (postiPrenotati > postiDisponibili) {
            throw new IllegalArgumentException("Posti non disponibili");
        }

        Prenotations prenotazione = new Prenotations();
        prenotazione.setEvento(evento);
        prenotazione.setUtente(utente);
        prenotazione.setPostiPrenotati(postiPrenotati);

        evento.setPostiDisponibili(postiDisponibili - postiPrenotati);

        pDAO.save(prenotazione);
        eDAO.save(evento);
    }
}
