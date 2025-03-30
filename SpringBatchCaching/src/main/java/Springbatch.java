import Pojo.Person;
import Pojo.PersonMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class Springbatch {
    @Bean
    public JdbcCursorItemReader<Person> readerForDataSource(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Person>()
                .dataSource(dataSource)
                .name("personReader")
                .sql("SELECT id, name FROM person")
                .rowMapper(new PersonMapper())
                .build();
    }

    @Bean
    public ItemReader<String> reader()
    {
        return new ReaderClass();
    }

    @Bean
    public ItemProcessor<String, String> processor() {
        return new ProcessorClass();
    }

    @Bean
    public ItemWriter<String> writer() {
        return new WriterClass();
    }

    @Bean
    public Job importUserJob(Step step) {
        return new JobBuilder("importUserJob")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public Step step(JdbcCursorItemReader<String> reader,
                     ItemProcessor<String, String> processor,
                     ItemWriter<String> writer) {
        return new StepBuilder("step")
                .<String, String>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }


}
