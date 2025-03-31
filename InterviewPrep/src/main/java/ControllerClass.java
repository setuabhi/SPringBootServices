import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class ControllerClass {

    private final CachingExample cachingExample;

    public ControllerClass(CachingExample cachingExample) {
        this.cachingExample = cachingExample;
    }

    @GetMapping("/{id}")
    public String getEmployee(@PathVariable String empName) {
        return cachingExample.getEmpId(empName);
    }
}