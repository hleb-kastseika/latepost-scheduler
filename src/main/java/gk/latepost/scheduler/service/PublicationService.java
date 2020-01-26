package gk.latepost.scheduler.service;

import gk.latepost.scheduler.model.Publication;
import gk.latepost.scheduler.repository.PublicationRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * PublicationService class.
 * <p>
 * Date: Oct 19, 2019
 * <p>
 *
 * @author Gleb Kosteiko
 */
@ApplicationScoped
public class PublicationService {
//    @Inject
//    private ScheduleService scheduleService;
    @Inject
    private PublicationRepository repository;

    public Publication create(Publication publication) {
        publication = repository.save(publication);
//        scheduleService.schedule(publication);
        return publication;
    }

    public Publication update(Publication publication) {
        publication = repository.update(publication);
//        scheduleService.unschedule(publication.id);
//        scheduleService.schedule(publication);

        return publication;
    }

    public void delete(long id) {
        repository.delete(id);
//        scheduleService.unschedule(id);
    }

    public Publication get(long id) {
        return repository.get(id);
    }

    public List<Publication> getAll() {
        return repository.getAll();
    }
}
