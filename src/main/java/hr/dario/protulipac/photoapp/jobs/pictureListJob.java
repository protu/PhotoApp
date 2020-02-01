package hr.dario.protulipac.photoapp.jobs;

import hr.dario.protulipac.photoapp.domain.Picture;
import hr.dario.protulipac.photoapp.repository.PictureRepo;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class pictureListJob extends QuartzJobBean {

    private Logger log = LoggerFactory.getLogger(pictureListJob.class);
    private final PictureRepo pictureRepo;

    public pictureListJob(PictureRepo pictureRepo) {
        this.pictureRepo = pictureRepo;
    }


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Iterable<Picture> pictures = pictureRepo.findAll();
        if (pictures.iterator().hasNext()) {
            log.info("Pictures in database:");
            pictures.forEach(pic -> log.info(pic.getName()));
        }
        else {
            log.info("There are no pictures in database");
        }
    }
}
