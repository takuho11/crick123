package cn.topcheer.pms2.biz.utils;

import org.hibernate.transform.BasicTransformerAdapter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * createSqlQuery toTist转list<Map> map key值保持小写
 */
public class AliasToLowerToEntityMapResultTransformer extends BasicTransformerAdapter implements Serializable {

    /**
     * Disallow instantiation of AliasToEntityMapResultTransformer.
     */
    public AliasToLowerToEntityMapResultTransformer() {
    }

    /**
     * {@inheritDoc}
     */
    public Object transformTuple(Object[] tuple, String[] aliases) {
        Map result = new HashMap(tuple.length);
        for ( int i=0; i<tuple.length; i++ ) {
            String alias = aliases[i];
            if ( alias!=null ) {
                result.put( alias.toLowerCase(), tuple[i] );
            }
        }
        return result;
    }


}