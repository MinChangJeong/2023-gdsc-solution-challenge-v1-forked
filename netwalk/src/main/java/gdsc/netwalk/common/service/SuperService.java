package gdsc.netwalk.common.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import gdsc.netwalk.common.model.Payload;
import gdsc.netwalk.common.model.PayloadList;

import java.util.List;

public class SuperService {

	// db connection
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public Payload select(String key, Payload payload) {
		return sqlSessionTemplate.selectOne(key, payload);
	}
	
	public PayloadList<Payload> selectList(String key, Payload payload) {
		List<Object> list = sqlSessionTemplate.selectList(key, payload);
		return new PayloadList<Payload>(list);
	}
	
	public int insert(String key, Payload payload) {
		return sqlSessionTemplate.insert(key, payload);
	}

	public int update(String key, Payload payload) {
		return sqlSessionTemplate.update(key, payload);
	}

	public int delete(String key, Payload payload) {
		return sqlSessionTemplate.delete(key, payload);
	}

	// mapper를 위한 StringBuilder
	public String concat(String location, String mapper_name) {
		StringBuilder builder = new StringBuilder();
		builder.append(location).append(mapper_name);
		return new String(builder);
	}
}
