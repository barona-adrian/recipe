package com.deliciousapp.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;

@Component
@Slf4j
public class CommonUtils {

    public void dynamicInsertIntoHBase(HTable table, String key, Class cl, Object obj, String columnFamily) throws IllegalAccessException, IOException {
        Put put = new Put(Bytes.toBytes(key));
        Field[] fields = cl.getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);
            String value = String.valueOf(field.get(obj));
            String name = field.getName();
            put.addColumn(Bytes.toBytes(columnFamily),Bytes.toBytes(name),Bytes.toBytes(value));
        }
        table.put(put);
    }

}
