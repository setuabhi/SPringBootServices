import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@EnableBatchProcessing
@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration.class})
public class main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(main.class, args);

        // Get JobLauncher and Job beans from the context
        JobLauncher jobLauncher = context.getBean(JobLauncher.class);
        Job importUserJob = context.getBean("importUserJob", Job.class);

        // Create Job Parameters
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        try {
            JobExecution execution = jobLauncher.run(importUserJob, jobParameters);
            System.out.println("Job Execution Status: " + execution.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Close context if needed
        context.close();
    }
}
