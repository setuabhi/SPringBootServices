import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class WriterClass implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> list) throws Exception {
        for(String s: list)
        {
            System.out.println(s);
        }
    }
}
