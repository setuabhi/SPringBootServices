import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Do something needs to run before JOB");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

    }
}
