package bkendfinalproject.finalpj.services;

import bkendfinalproject.finalpj.dao.EventsDAO;
import bkendfinalproject.finalpj.dao.UserDAO;
import bkendfinalproject.finalpj.entities.Events;
import bkendfinalproject.finalpj.entities.User;
import bkendfinalproject.finalpj.exceptions.BadRequestException;
import bkendfinalproject.finalpj.exceptions.NotFoundException;
import bkendfinalproject.finalpj.payloads.EventsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EventsServices {

    @Autowired
    private EventsDAO eDAO;

    @Autowired
    private UserServices us;

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

    public Events addUserToEvent(long eventId, long userId) {
        Events event = this.getEvents(eventId);
        User user = this.us.getUsers(userId);
        if (event.getUsers().contains(user)) {
            throw new BadRequestException("ok");
        }
        if (event.getPostiDisponibili() == 0) {
            throw new BadRequestException("Nessun posto rimasto");
        }
        event.getUsers().add(user);
        return eDAO.save(event);
    }

}
