package vs.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

import vs.dao.YinpinDao;
import vs.enity.es.yinpin.YinpinPojo;

@Repository
public class YinpinDaoImpl implements YinpinDao {

	@Value("${es.index}")
	private String INDEX;

	@Value("${es.type.yinpin}")
	private String TYPE_YINPIN;

	@Autowired
	private TransportClient client;

	@Override
	public Long getAllCount() {
		SearchRequestBuilder srb = client.prepareSearch(INDEX).setTypes(TYPE_YINPIN);
		SearchResponse response = srb.setQuery(QueryBuilders.matchAllQuery()).execute().actionGet();
		SearchHits hits = response.getHits();
		return hits.getTotalHits();
	}

	@Override
	public List<YinpinPojo> getList(Integer startRow, Integer pageSize) {
		SearchRequestBuilder srb = client.prepareSearch(INDEX).setTypes(TYPE_YINPIN);
		SearchResponse response = srb.setQuery(QueryBuilders.matchAllQuery()).setFrom(startRow).setSize(pageSize)
				.execute().actionGet();
		SearchHits hits = response.getHits();
		List<YinpinPojo> list = new ArrayList<>();
		for (SearchHit hit : hits) {
			String json = hit.getSourceAsString();
			YinpinPojo object = new Gson().fromJson(json, YinpinPojo.class);
			list.add(object);
		}
		return list;
	}

	@Override
	public Map<String, Long> getFileSize(Integer[] xAxis) {
		SearchRequestBuilder srb = client.prepareSearch(INDEX).setTypes(TYPE_YINPIN);
		RangeAggregationBuilder rangeAggs = AggregationBuilders.range("size_range").field("musicSize");
		rangeAggs.addUnboundedTo(xAxis[0]);
		for (int i = 0; i < xAxis.length - 1; i++) {
			rangeAggs.addRange(xAxis[i], xAxis[i + 1]);
		}
		rangeAggs.addUnboundedFrom(xAxis[xAxis.length - 1]);
		srb.addAggregation(rangeAggs).setSize(0);
		SearchResponse response = srb.execute().actionGet();
		Map<String, Aggregation> aggMap = response.getAggregations().asMap();
		Range rangeAgg = (Range) aggMap.get("size_range");
		List<? extends org.elasticsearch.search.aggregations.bucket.range.Range.Bucket> buckets = rangeAgg.getBuckets();
		Map<String, Long> result = new LinkedHashMap<>();
		for (org.elasticsearch.search.aggregations.bucket.range.Range.Bucket bucket : buckets) {
			Long count = bucket.getDocCount();
			String name = bucket.getKeyAsString();
			result.put(name, count);
		}
		return result;
	}

}
