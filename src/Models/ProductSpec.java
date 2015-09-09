package Models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Remco on 8-9-2015.
 */
public class ProductSpec {

    private Map specification;

    public ProductSpec(Map specification) {
        if (specification == null){
            this.specification = new HashMap();
        }
        else{
            this.specification = new HashMap(specification);
        }
    }

    public Map getSpecification() {
        return specification;
    }

    public Object getSpec(String spec){
        return specification.get(spec);

    }
}