package org.spring.learning.properties;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.Resource;

public class ResourceService {
	/** 定义资源文件接口 */
	private Resource resource;

	public Properties getResource() {
		/** 创建属性集合 */
		Properties props = new Properties();
		/** 通过输入流填充属性集合 */
		try {
			props.load(resource.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
}
