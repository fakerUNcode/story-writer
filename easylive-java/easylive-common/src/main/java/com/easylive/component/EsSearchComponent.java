package com.easylive.component;

import com.easylive.entity.config.AppConfig;
import com.easylive.entity.dto.VideoInfoEsDto;
import com.easylive.entity.po.VideoInfo;
import com.easylive.exception.BusinessException;
import com.easylive.utils.CopyTools;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

@Component("esSearchComponent")
@Slf4j
public class EsSearchComponent {
    @Resource
    private AppConfig appConfig;

    @Resource
    private RestHighLevelClient restHighLevelClient;

    private Boolean isExistIndex() throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest(appConfig.getEsIndexVideoName());
        return restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        }

    public void createIndex(){
        try {
            if(isExistIndex()){
                return;
            }
            CreateIndexRequest request = new CreateIndexRequest(appConfig.getEsIndexVideoName());
            request.settings( "{ \"analysis\": {\n" +
                    "  \"analyzer\": {\n" +
                    "    \"comma\": {\n" +
                    "      \"type\": \"pattern\",\n" +
                    "      \"pattern\": \",\"\n" +
                    "    }\n" +
                    "  }\n" +
                    "}}", XContentType.JSON);
            request.mapping("{ \"properties\": {\n" +
                    "  \"videoId\": {\n" +
                    "    \"type\": \"text\",\n" +
                    "    \"index\": false\n" +
                    "  },\n" +
                    "  \"userId\": {\n" +
                    "    \"type\": \"text\",\n" +
                    "    \"index\": false\n" +
                    "  },\n" +
                    "  \"videoCover\": {\n" +
                    "    \"type\": \"text\",\n" +
                    "    \"index\": false\n" +
                    "  },\n" +
                    "  \"videoName\": {\n" +
                    "    \"type\": \"text\",\n" +
                    "    \"analyzer\": \"ik_max_word\"\n" +
                    "  },\n" +
                    "  \"tags\": {\n" +
                    "    \"type\": \"text\",\n" +
                    "    \"analyzer\": \"comma\"\n" +
                    "  },\n" +
                    "  \"playCount\": {\n" +
                    "    \"type\": \"integer\",\n" +
                    "    \"index\": false\n" +
                    "  },\n" +
                    "  \"danmuCount\": {\n" +
                    "    \"type\": \"integer\",\n" +
                    "    \"index\": false\n" +
                    "  },\n" +
                    "  \"collectCount\": {\n" +
                    "    \"type\": \"integer\",\n" +
                    "    \"index\": false\n" +
                    "  },\n" +
                    "  \"createTime\": {\n" +
                    "    \"type\": \"date\",\n" +
                    "    \"format\": \"yyyy-MM-dd HH:mm:ss\",\n" +
                    "    \"index\": false\n" +
                    "  }\n" +
                    "}}", XContentType.JSON);
            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request,RequestOptions.DEFAULT);
            Boolean acknowledge = createIndexResponse.isAcknowledged();
            if(!acknowledge){
                throw new BusinessException("初始化es失败");


            }
        }catch (Exception e){
            log.error("初始化es失败",e);
            throw new BusinessException("初始化es失败");
        }
    }

    public void saveDoc(VideoInfo videoInfo){
        VideoInfoEsDto videoInfoEsDto = CopyTools.copy(videoInfo, VideoInfoEsDto.class);
        videoInfoEsDto.setCollectCount(0);
        
    }
}
