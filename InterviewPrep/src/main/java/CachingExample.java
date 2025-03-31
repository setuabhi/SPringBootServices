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

    HashMap<String, String> hm = new HashMap<>(); //Asssume it has data


    //here empName is key
    //Second Time if same empName passed it will return from Cache
    @Cacheable("employeesCache")
    String getEmpId(String empName)
    {
        return  hm.get(empName);
    }

    //Method to clean cache
    @CacheEvict("employeesCache")
    public void removeEmployeeCache(String empName) {
        System.out.println("Cache evicted for employee name: " + empName);
    }

    //handeling manually
    String [] getId (String [] key)
    {
        String[] output = new String[key.length];
        int i=0;
        Cache cache = cacheManager.getCache("employeesCache");
        for(String s: key)
        {
            if(cache.get(s)!=null)
            {
                output[i++]= String.valueOf(cache.get(s));
            }
            else
            {
                output[i++]= hm.get(s);
                cache.put(s,hm.get(s));
            }
        }
        return output;
    }


}

