package gk.latepost.scheduler.service;

import gk.latepost.scheduler.model.Publication;
import gk.latepost.scheduler.quartz.PublicationJob;

import gk.latepost.scheduler.repository.PublicationRepository;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobDataMap;
import org.quartz.TriggerKey;
import org.quartz.TriggerBuilder;
import org.quartz.CronScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static gk.latepost.scheduler.constant.Constants.PUBLICATION;
import static gk.latepost.scheduler.constant.Constants.REPOSITORY;

/**
 * ScheduleService class.
 * <p>
 * Date: Oct 19, 2019
 * <p>
 *
 * @author Gleb Kosteiko
 */
@ApplicationScoped
public class ScheduleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleService.class);
    private static final String CRON_TEMPLATE = "%s %s %s %s %s ? %s";
    private static final String TRIGGER_PREFIX = "trigger_";
    private static final String GROUP_PREFIX = "group_";
    private Scheduler scheduler;
    @Inject
    private PublicationRepository repository;

    @PostConstruct
    private void startScheduler() throws SchedulerException {
        scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
    }

    public void schedule(Publication publication) {
        Trigger trigger = buildTrigger(publication);
        Map<String, Object> jobData = new HashMap<String, Object>() {{
            put(PUBLICATION, publication);
            put(REPOSITORY, repository);
        }};
        JobDetail jobDetail = JobBuilder.newJob(PublicationJob.class)
                .setJobData(new JobDataMap(jobData))
                .withIdentity(TRIGGER_PREFIX + publication.id, GROUP_PREFIX + publication.id)
                .build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            LOGGER.error(e.toString());
        }
    }

    public void unschedule(long id) {
        try {
            scheduler.unscheduleJob(TriggerKey.triggerKey(TRIGGER_PREFIX + id, GROUP_PREFIX + id));
        } catch (SchedulerException e) {
            LOGGER.error(e.toString());
        }
    }

    private Trigger buildTrigger(Publication publication) {
        LocalDateTime datetime = publication.getDatetime();
        String cronExpression = String.format(CRON_TEMPLATE,
                datetime.getSecond(),
                datetime.getMinute(),
                datetime.getHour(),
                datetime.getDayOfMonth(),
                datetime.getMonth(),
                datetime.getYear());
        return TriggerBuilder.newTrigger()
                .withIdentity(TRIGGER_PREFIX + publication.id, GROUP_PREFIX + publication.id)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();
    }
}
