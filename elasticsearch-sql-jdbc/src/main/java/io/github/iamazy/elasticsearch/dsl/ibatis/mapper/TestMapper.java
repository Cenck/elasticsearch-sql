package io.github.iamazy.elasticsearch.dsl.ibatis.mapper;

import java.util.Map;

public interface TestMapper {


    /*
    ${returnType} ${methodName}(Map<String, Object> map);
     */
    Object testHello(Map<String, Object> map);

}
