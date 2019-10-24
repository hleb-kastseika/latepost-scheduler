package gk.latepost.scheduler.repository;

import gk.latepost.scheduler.model.Publication;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

/**
 * PublicationRepository class.
 * <p>
 * Date: Oct 19, 2019
 * <p>
 *
 * @author Gleb Kosteiko
 */
@ApplicationScoped
public class PublicationRepository {

    @Transactional
    public Publication save(Publication publication) {
        publication.persist();
        return publication;
    }

    @Transactional
    public Publication update(Publication publication) {
        Publication entity = Publication.findById(publication.id);
        entity.setText(publication.getText());
        entity.setDatetime(publication.getDatetime());
        return publication;
    }

    @Transactional
    public void delete(long id) {
        Publication publication = Publication.findById(id);
        if (publication != null) {
            publication.delete();
        }
    }

    public Publication get(long id) {
        return Publication.findById(id);
    }

    public List<Publication> getAll() {
        return Publication.listAll();
    }
}
