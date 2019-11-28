package com.xkcoding.test.test40;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import lombok.extern.slf4j.Slf4j;
import org.bson.BasicBSONObject;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 测试 MongoDB 的 MongoTemplate
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/11/27 10:17
 */
@Slf4j
public class Test40 {
    private final static Snowflake SNOWFLAKE = IdUtil.createSnowflake(1, 1);

    private final static MongoTemplate MONGO_TEMPLATE = new MongoTemplate(new MongoClient("192.168.241.14"), "test");

    public static void main(String[] args) {
        Test40 test40 = new Test40();

        // test40.initData();

        // test40.querySimpleData();

        // test40.queryCount();

        // test40.queryGroupByAndCount();

        // test40.queryDuplicate();

        // test40.queryData1JoinData2();

        // test40.queryData2JoinData1();

        // test40.queryData2JoinData1_2();

        // test40.queryDuplicateData();

        test40.advanceQuery();

        // test40.deleteData();

        // test40.drop();
    }

    /**
     * 比较 value 字段中间 2 位和 relation 字段后 2 位是否相等
     */
    private void advanceQuery() {
        CriteriaDefinition cd = new CriteriaDefinition() {
            @Override
            public Document getCriteriaObject() {
                Document document = new Document();
                document.put("$where", "this.value.substr(2, 2) === this.relation.substr(this.relation.length - 2, 2)");
                return document;
            }

            @Override
            public String getKey() {
                return null;
            }
        };
        List<JoinDataVO> joinDataVOS = MONGO_TEMPLATE.find(Query.query(cd), JoinDataVO.class);

        System.out.println(JSONUtil.toJsonStr(joinDataVOS));
    }

    private void queryDuplicateData() {
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.lookup("data1", "key", "key", "relation"), Aggregation.project().andExclude("data2.relation._id", "data2.relation._class", "data2.relation.key"), Aggregation.unwind("relation"));

        AggregationResults<BasicBSONObject> aggregate = MONGO_TEMPLATE.aggregate(aggregation, Data2.class, BasicBSONObject.class);

        List<BasicBSONObject> mappedResults = aggregate.getMappedResults();

        MONGO_TEMPLATE.dropCollection(JoinDataVO.class);

        mappedResults.forEach(v -> {
            JSONObject parse = JSONUtil.parseObj(v);

            JoinDataVO joinDataVO = new JoinDataVO();
            joinDataVO.setId(SNOWFLAKE.nextId());
            joinDataVO.setSourceId(parse.getLong("_id"));
            joinDataVO.setKey(parse.getStr("key"));
            joinDataVO.setValue(parse.getStr("value"));
            joinDataVO.setRelation(parse.getByPath("relation.value", String.class));

            MONGO_TEMPLATE.save(joinDataVO);
        });

        BasicQuery query = new BasicQuery("{ $expr: { $ne: [ \"$value\" , \"$relation\" ] } } ");
        List<JoinDataVO> joinDataVOS = MONGO_TEMPLATE.find(query, JoinDataVO.class);

        System.out.println(JSONUtil.toJsonStr(joinDataVOS));

    }

    private void queryData2JoinData1_2() {
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.lookup("data1", "key", "key", "relation"), Aggregation.project().andExclude("data2.relation._id", "data2.relation._class", "data2.relation.key"), Aggregation.unwind("relation"));
        AggregationResults<JoinDataVO> aggregate = MONGO_TEMPLATE.aggregate(aggregation, Data2.class, JoinDataVO.class);

        List<JoinDataVO> mappedResults = aggregate.getMappedResults();
        System.out.println(JSONUtil.toJsonStr(mappedResults));
    }

    private void queryData2JoinData1() {
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.lookup("data1", "key", "key", "data1"));
        AggregationResults<BasicDBObject> aggregate = MONGO_TEMPLATE.aggregate(aggregation, Data2.class, BasicDBObject.class);

        List<BasicDBObject> mappedResults = aggregate.getMappedResults();
        System.out.println(JSONUtil.toJsonStr(mappedResults));
    }

    private void queryData1JoinData2() {
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.lookup("data2", "key", "key", "data2"));
        AggregationResults<BasicDBObject> aggregate = MONGO_TEMPLATE.aggregate(aggregation, Data1.class, BasicDBObject.class);

        List<BasicDBObject> mappedResults = aggregate.getMappedResults();
        System.out.println(JSONUtil.toJsonStr(mappedResults));
    }

    private void queryDuplicate() {
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.group("key").count().as("count"), Aggregation.match(Criteria.where("count").gt(1)));
        AggregationResults<HashMap> results = MONGO_TEMPLATE.aggregate(aggregation, Data1.class, HashMap.class);

        List<HashMap> mappedResults = results.getMappedResults();

        for (HashMap mappedResult : mappedResults) {
            System.out.println("mappedResult = " + mappedResult);
            Object id = mappedResult.get("_id");
            System.out.println(MONGO_TEMPLATE.find(new Query(Criteria.where("key").is(id)), Data1.class));
        }
    }

    private void queryGroupByAndCount() {
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.group("key").count().as("count"));
        AggregationResults<Map> results = MONGO_TEMPLATE.aggregate(aggregation, Data1.class, Map.class);
        results.getMappedResults().forEach(System.out::println);

    }

    private void queryCount() {
        System.out.println(MONGO_TEMPLATE.count(new Query(Criteria.where("key").regex("1")), Data1.class));
    }

    private void drop() {
        MONGO_TEMPLATE.dropCollection(Data1.class);
        MONGO_TEMPLATE.dropCollection(Data2.class);
    }

    private void deleteData() {
        Query query = new Query();
        query.addCriteria(Criteria.where("key").is("1234"));
        MONGO_TEMPLATE.remove(query, Data1.class);
        MONGO_TEMPLATE.remove(query, Data2.class);
    }

    private void querySimpleData() {
        System.out.println(MONGO_TEMPLATE.findAll(Data1.class));
        System.out.println(MONGO_TEMPLATE.findAll(Data2.class));
    }

    private void initData() {

        MONGO_TEMPLATE.insert(new Data1(SNOWFLAKE.nextId(), "1234", "dhajdklaj"));
        MONGO_TEMPLATE.insert(new Data1(SNOWFLAKE.nextId(), "1235", "dhajdklajdsa"));

        MONGO_TEMPLATE.insert(new Data1(SNOWFLAKE.nextId(), "1236", "dhajdklaj"));
        MONGO_TEMPLATE.insert(new Data1(SNOWFLAKE.nextId(), "1236", "dhajdklajdsa"));


        MONGO_TEMPLATE.insert(new Data2(SNOWFLAKE.nextId(), "1234", "klajdsa"));
        MONGO_TEMPLATE.insert(new Data2(SNOWFLAKE.nextId(), "1235", "dhajdklajdsa"));
        MONGO_TEMPLATE.insert(new Data2(SNOWFLAKE.nextId(), "1236", "dhajdklajdsa"));

    }
}
