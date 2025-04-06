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
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

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
    public FlatFileItemReader<Person> readerFlatFile() {
        FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("input.csv"));
        reader.setLinesToSkip(1); // skip header


        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("id", "name");

        BeanWrapperFieldSetMapper<Person> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Person.class);

        DefaultLineMapper<Person> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        reader.setLineMapper(lineMapper);
        return reader;
    }

    @Bean
    public ItemReader<String> reader() {
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

    @Bean
    public Step taskletStep() {
        return new StepBuilder("taskletStep")
                .tasklet(new TaskeletClass())
                .build();
    }

    @Bean
    public Job importUserJob(Step step, Step taskletStep) {
        return new JobBuilder("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(new JobListener())
                .start(step)
                .next(taskletStep)
                .build();
    }


}
