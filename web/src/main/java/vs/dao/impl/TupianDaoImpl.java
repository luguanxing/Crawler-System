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
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

import vs.dao.TupianDao;
import vs.enity.es.tupian.TupianPojo;

@Repository
public class TupianDaoImpl implements TupianDao {

	@Value("${es.index}")
	private String INDEX;

	@Value("${es.type.tupian}")
	private String TYPE_TUPIAN;

	@Autowired
	private TransportClient client;

	@Override
	public Long getAllCount() {
		SearchRequestBuilder srb = client.prepareSearch(INDEX).setTypes(TYPE_TUPIAN);
		SearchResponse response = srb.setQuery(QueryBuilders.matchAllQuery()).execute().actionGet();
		SearchHits hits = response.getHits();
		return hits.getTotalHits();
	}

	@Override
	public List<TupianPojo> getList(Integer startRow, Integer pageSize) {
		SearchRequestBuilder srb = client.prepareSearch(INDEX).setTypes(TYPE_TUPIAN);
		SearchResponse response = srb.setQuery(QueryBuilders.matchAllQuery()).setFrom(startRow).setSize(pageSize)
				.execute().actionGet();
		SearchHits hits = response.getHits();
		List<TupianPojo> list = new ArrayList<>();
		for (SearchHit hit : hits) {
			String json = hit.getSourceAsString();
			TupianPojo object = new Gson().fromJson(json, TupianPojo.class);
			list.add(object);
		}
		return list;
	}

	@Override
	public Map<String, Long> getFileSize(Integer[] xAxis) {
		SearchRequestBuilder srb = client.prepareSearch(INDEX).setTypes(TYPE_TUPIAN);
		RangeAggregationBuilder rangeAggs = AggregationBuilders.range("size_range").field("picSize");
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

	@Override
	public Map<String, Long> getPicPixel() {
		SearchRequestBuilder srb = client.prepareSearch(INDEX).setTypes(TYPE_TUPIAN);
		TermsAggregationBuilder rateAggs = AggregationBuilders.terms("group_by_picSize")
				.field("picPixel")
				.order(Order.term(true));
		srb.addAggregation(rateAggs).setSize(0);
		SearchResponse response = srb.execute().actionGet();
		Map<String, Aggregation> aggMap = response.getAggregations().asMap();
		StringTerms rateAgg = (StringTerms) aggMap.get("group_by_picSize");
		List<org.elasticsearch.search.aggregations.bucket.terms.StringTerms.Bucket> buckets = rateAgg.getBuckets();
		// 使用LinkedHashMap保证键有序，且键唯一
		Map<String, Long> result = new LinkedHashMap<>();
		for (org.elasticsearch.search.aggregations.bucket.terms.StringTerms.Bucket bucket : buckets) {
			Long count = bucket.getDocCount();
			String name = bucket.getKeyAsString();
			result.put(name, count);
		}
		return result;
	}

}
