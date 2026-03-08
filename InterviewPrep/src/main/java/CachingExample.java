import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
@EnableCaching
public class CachingExample {
    @Autowired
    CacheManager cacheManager;

    HashMap<String, String> hm = new HashMap<>(); //Assume it has data, empName and it's country


    //here empName is key
    //Second Time if same empName passed it will return from Cache
    @Cacheable("employeesCache")
    String getEmpId(String empName) {
        return hm.get(empName);
    }

    //Method to clean cache
    @CacheEvict("employeesCache")
    public void removeEmployeeCache(String empName) {
        System.out.println("Cache evicted for employee name: " + empName);
    }

    //handling manually
    String getId(String empName) {
        Cache cache = cacheManager.getCache("employeesCache");
        if (cache.get(empName) != null) {
            return cache.get(empName).toString();
        } else {
            cache.put(empName, hm.get(empName));
            return hm.get(empName);
        }
    }
}

