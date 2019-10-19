package gk.latepost.scheduler.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import java.time.LocalDateTime;
import javax.persistence.Entity;

/**
 * Publication class.
 * <p>
 * Date: Oct 19, 2019
 * <p>
 *
 * @author Gleb Kosteiko
 */
@Entity
public class Publication extends PanacheEntity {
    private String text;
    private LocalDateTime datetime;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "[id=" + this.id + ", datetime=" + this.datetime + ", text=" + this.text + "]";
    }
}
