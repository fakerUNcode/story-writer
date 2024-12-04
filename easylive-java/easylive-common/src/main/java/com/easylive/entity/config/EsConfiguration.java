package com.easylive.entity.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

import javax.annotation.Resource;

/*用于配置 Elasticsearch 客户端连接的 Spring 配置类*/
@Configuration
public class EsConfiguration extends AbstractElasticsearchConfiguration implements DisposableBean {

    @Resource
    private AppConfig appConfig;
    private RestHighLevelClient client;
    @Override
    public void destroy() throws Exception {
        if(client!=null){
            client.close();// 关闭客户端连接，释放资源
        }
    }

    /*创建一个与es交互的RestHighLevelClient实例*/
    @Override
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder().connectedTo(appConfig.getEsHostPort()).build();
        client = RestClients.create(clientConfiguration).rest();
        return client;
    }
}
