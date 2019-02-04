package vs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
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

}
