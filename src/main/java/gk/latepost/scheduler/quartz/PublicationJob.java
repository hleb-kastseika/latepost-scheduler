//package gk.latepost.scheduler.quartz;
//
//import gk.latepost.scheduler.model.Publication;
//import gk.latepost.scheduler.repository.PublicationRepository;
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import static gk.latepost.scheduler.constant.Constants.PUBLICATION;
//import static gk.latepost.scheduler.constant.Constants.REPOSITORY;
//
///**
// * PublicationJob class.
// * <p>
// * Date: Oct 19, 2019
// * <p>
// *
// * @author Gleb Kosteiko
// */
//public class PublicationJob implements Job {
//    private static final Logger LOGGER = LoggerFactory.getLogger(PublicationJob.class);
//
//    @Override
//    public void execute(JobExecutionContext context) {
//        Publication publication = (Publication) context.getMergedJobDataMap().get(PUBLICATION);
//        LOGGER.info("Publish >> " + publication);
//        PublicationRepository repository = (PublicationRepository) context.getMergedJobDataMap().get(REPOSITORY);
//        repository.delete(publication.id);
//    }
//}
