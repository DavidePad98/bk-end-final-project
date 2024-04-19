package bkendfinalproject.finalpj.services;

import bkendfinalproject.finalpj.dao.PrenotationsDAO;
import bkendfinalproject.finalpj.entities.Events;
import bkendfinalproject.finalpj.entities.Prenotations;
import bkendfinalproject.finalpj.exceptions.BadRequestException;
import bkendfinalproject.finalpj.exceptions.NotFoundException;
import bkendfinalproject.finalpj.payloads.PrenotazioniDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrenotationsService {
    @Autowired
    private PrenotationsDAO pDAO;
    @Autowired
    private UserServices us;
    @Autowired
    private EventsServices es;

    public Prenotations findById(long id) {
        return pDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Prenotations save(PrenotazioniDTO payload) {
        Events event = es.findById(payload.eventId());
        if (event.getPrenotazioni().size() < event.getPostiDisponibili()) {
            Prenotations newPren = new Prenotations(us.findById(payload.userId()), event);
            return pDAO.save(newPren);
        } else throw new BadRequestException("Event is full.");
    }

    public void delete(long id) {
        Prenotations found = this.findById(id);
        pDAO.delete(found);
    }
}
