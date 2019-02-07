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
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Order;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

import vs.dao.RuanjianDao;
import vs.enity.es.ruanjian.RuanjianPojo;

@Repository
public class RuanjianDaoImpl implements RuanjianDao {

	@Value("${es.index}")
	private String INDEX;

	@Value("${es.type.ruanjian}")
	private String TYPE_RUANJIAN;

	@Autowired
	private TransportClient client;

	@Override
	public List<RuanjianPojo> getList(Integer startRow, Integer pageSize) {
		SearchRequestBuilder srb = client.prepareSearch(INDEX).setTypes(TYPE_RUANJIAN);
		SearchResponse response = srb.setQuery(QueryBuilders.matchAllQuery()).setFrom(startRow).setSize(pageSize)
				.execute().actionGet();
		SearchHits hits = response.getHits();
		List<RuanjianPojo> list = new ArrayList<>();
		for (SearchHit hit : hits) {
			String json = hit.getSourceAsString();
			RuanjianPojo object = new Gson().fromJson(json, RuanjianPojo.class);
			list.add(object);
		}
		return list;
	}

	@Override
	public Long getAllCount() {
		SearchRequestBuilder srb = client.prepareSearch(INDEX).setTypes(TYPE_RUANJIAN);
		SearchResponse response = srb.setQuery(QueryBuilders.matchAllQuery()).execute().actionGet();
		SearchHits hits = response.getHits();
		return hits.getTotalHits();
	}

	@Override
	public Map<String, Long> getRate() {
		SearchRequestBuilder srb = client.prepareSearch(INDEX).setTypes(TYPE_RUANJIAN);
		TermsAggregationBuilder rateAggs = AggregationBuilders.terms("group_by_rate")
				.field("softRate")
				.order(Order.term(true));
		srb.addAggregation(rateAggs).setSize(0);
		SearchResponse response = srb.execute().actionGet();
		Map<String, Aggregation> aggMap = response.getAggregations().asMap();
		LongTerms rateAgg = (LongTerms) aggMap.get("group_by_rate");
		List<Bucket> buckets = rateAgg.getBuckets();
		// 使用LinkedHashMap保证键有序，且键唯一
		Map<String, Long> result = new LinkedHashMap<>();
		for (Bucket bucket : buckets) {
			Long count = bucket.getDocCount();
			String name = bucket.getKeyAsString();
			result.put(name, count);
		}
		return result;
	}

	@Override
	public Map<String, Long> getUpdateTime() {
		SearchRequestBuilder srb = client.prepareSearch(INDEX).setTypes(TYPE_RUANJIAN);
		DateHistogramAggregationBuilder updateTimeAggs = AggregationBuilders.dateHistogram("group_by_updateTime")
				.field("softUpdateTime").dateHistogramInterval(DateHistogramInterval.YEAR);
		srb.addAggregation(updateTimeAggs).setSize(0);
		SearchResponse response = srb.execute().actionGet();
		Map<String, Aggregation> aggMap = response.getAggregations().asMap();
		Histogram updateTimeAgg = (Histogram) aggMap.get("group_by_updateTime");
		Map<String, Long> result = new LinkedHashMap<>();
		for (Histogram.Bucket entry : updateTimeAgg.getBuckets()) {
			String name = entry.getKeyAsString();
			Long count = entry.getDocCount();
			result.put(name, count);
		}
		return result;
	}

	@Override
	public Map<String, Long> getFileSize(Integer[] xAxis) {
		SearchRequestBuilder srb = client.prepareSearch(INDEX).setTypes(TYPE_RUANJIAN);
		RangeAggregationBuilder rangeAggs = AggregationBuilders.range("size_range").field("softSize");
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
	public Map<String, Double> getRateAvgDownloadTimes() {
		SearchRequestBuilder srb = client.prepareSearch(INDEX).setTypes(TYPE_RUANJIAN);
		TermsAggregationBuilder rateAggs = AggregationBuilders.terms("group_by_rate_avg_downloadTimes")
				.field("softRate")
				.order(Order.term(true));
		AvgAggregationBuilder rateAvgDownloadTimesAggs = AggregationBuilders.avg("avg_downloadTimes")
				.field("softDownloadTimes");
		rateAggs.subAggregation(rateAvgDownloadTimesAggs);
		srb.addAggregation(rateAggs).setSize(0);
		SearchResponse response = srb.execute().actionGet();
		Map<String, Aggregation> rateAggMap = response.getAggregations().asMap();
		LongTerms rateAgg = (LongTerms) rateAggMap.get("group_by_rate_avg_downloadTimes");
		List<Bucket> buckets = rateAgg.getBuckets();
		// 使用LinkedHashMap保证键有序，且键唯一
		Map<String, Double> result = new LinkedHashMap<>();
		for (Bucket bucket : buckets) {
			Map<String, Aggregation> rateAvgDownloadTimesMap = bucket.getAggregations().asMap();
			InternalAvg rateAvgDownloadTimesAvg = (InternalAvg) rateAvgDownloadTimesMap.get("avg_downloadTimes");
			Double count = rateAvgDownloadTimesAvg.getValue();
			String name = bucket.getKeyAsString();
			result.put(name, count);
		}
		return result;
	}

	@Override
	public Map<String, Long> getCategory() {
		SearchRequestBuilder srb = client.prepareSearch(INDEX).setTypes(TYPE_RUANJIAN);
		TermsAggregationBuilder rateAggs = AggregationBuilders.terms("group_by_category")
				.field("softCategory.keyword")
				.size(100) // 分组的结果最多显示前100个
				.order(Order.term(true));
		srb.addAggregation(rateAggs).setSize(0);
		SearchResponse response = srb.execute().actionGet();
		Map<String, Aggregation> aggMap = response.getAggregations().asMap();
		StringTerms rateAgg = (StringTerms) aggMap.get("group_by_category");
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

	@Override
	public Map<String, Long> getType() {
		SearchRequestBuilder srb = client.prepareSearch(INDEX).setTypes(TYPE_RUANJIAN);
		TermsAggregationBuilder rateAggs = AggregationBuilders.terms("group_by_type")
				.field("softType.keyword")
				.size(100) // 分组的结果最多显示前100个
				.order(Order.term(true));
		srb.addAggregation(rateAggs).setSize(0);
		SearchResponse response = srb.execute().actionGet();
		Map<String, Aggregation> aggMap = response.getAggregations().asMap();
		StringTerms rateAgg = (StringTerms) aggMap.get("group_by_type");
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
