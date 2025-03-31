import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * A Tasklet is a simple interface in Spring Batch used for single-task steps (e.g., file cleanup, sending notifications, database updates).
 * Unlike chunk-based processing, a Tasklet runs once per step execution.
 */
public class TaskeletClass implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("Tasklet executed: Performing a simple task!");
        return RepeatStatus.FINISHED; // Indicating task completion
    }
}
