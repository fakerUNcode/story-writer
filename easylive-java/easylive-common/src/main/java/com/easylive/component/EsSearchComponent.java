package com.easylive.component;

import com.easylive.entity.config.AppConfig;
import com.easylive.entity.dto.VideoInfoEsDto;
import com.easylive.entity.enums.SearchOrderTypeEnum;
import com.easylive.entity.po.VideoInfo;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.exception.BusinessException;
import com.easylive.utils.CopyTools;
import com.easylive.utils.JsonUtils;
import com.easylive.utils.StringTools;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

    public void saveDoc(VideoInfo videoInfo) {
        try {
            //若doc已存在则更新，否则作插入
            if(docExist(videoInfo.getVideoId())){
                updateDoc(videoInfo);
            }else {
                // 将 VideoInfo 转换为 VideoInfoEsDto
                VideoInfoEsDto videoInfoEsDto = CopyTools.copy(videoInfo, VideoInfoEsDto.class);

                // 设置默认值
                videoInfoEsDto.setCollectCount(0);
                videoInfoEsDto.setDanmuCount(0);
                videoInfoEsDto.setPlayCount(0);

                // 构建索引请求
                IndexRequest request = new IndexRequest(appConfig.getEsIndexVideoName());
                request.id(videoInfo.getVideoId()).source(JsonUtils.convertObj2Json(videoInfoEsDto),XContentType.JSON);

                // 执行索引请求
                restHighLevelClient.index(request, RequestOptions.DEFAULT);

                // 打印插入的文档 JSON（用于日志记录）
                String json = JsonUtils.convertObj2Json(videoInfoEsDto);
                log.info("插入的文档 JSON: {}", json);
            }

        } catch (Exception e) {
            log.error("保存到 Elasticsearch 失败", e);
            throw new BusinessException("保存到 Elasticsearch 失败");
        }
    }

    //判断doc是否存在的辅助方法
    private Boolean docExist(String id) throws IOException {
        GetRequest getRequest = new GetRequest(appConfig.getEsIndexVideoName(),id);
        GetResponse response = restHighLevelClient.get(getRequest,RequestOptions.DEFAULT);
        return response.isExists();
    }

    //更新doc方法
    private void updateDoc(VideoInfo videoInfo) {
        try {
            videoInfo.setLastUpdateTime(null);
            videoInfo.setCreateTime(null);

            Map<String, Object> dataMap = new HashMap<>();
            Field[] fields = videoInfo.getClass().getDeclaredFields();
            for (Field field : fields) {
                String methodName = "get" + StringTools.upperCaseFirstLetter(field.getName());
                Method method = videoInfo.getClass().getMethod(methodName);

                Object object = method.invoke(videoInfo);
                if(object!=null && object instanceof String && !StringTools.isEmpty(object.toString())
                || object!=null && !(object instanceof String)){
                    dataMap.put(field.getName(),object);
                }
            }
            if(dataMap.isEmpty()){
                return;
            }
            UpdateRequest updateRequest = new UpdateRequest(appConfig.getEsIndexVideoName(),videoInfo.getVideoId());
            updateRequest.doc(dataMap);
            restHighLevelClient.update(updateRequest,RequestOptions.DEFAULT);
        }catch (Exception e){
            log.error("es更新视频失败",e);
            throw new BusinessException("保存视频失败");
        }
    }

    //更新doc数量
    public void updateDocCount(String videoId, String fieldName, Integer count) {
        try {
            UpdateRequest updateRequest = new UpdateRequest(appConfig.getEsIndexVideoName(),videoId);
            Script script = new Script(ScriptType.INLINE,"painless","ctx._source."+fieldName+" += params.count", Collections.singletonMap("count",count));
            updateRequest.script(script);
            restHighLevelClient.update(updateRequest,RequestOptions.DEFAULT);
        }catch (Exception e){
            log.error("更新数量到es失败",e);
            throw new BusinessException("保存到es失败");
        }
    }

    public void delDoc(String videoId){
        DeleteRequest deleteRequest = new DeleteRequest(appConfig.getEsIndexVideoName(),videoId);
        try {
            restHighLevelClient.delete(deleteRequest,RequestOptions.DEFAULT);
        }catch (Exception e){
            log.error("es:删除视频失败",e);
            throw new BusinessException("es:视频删除失败");
        }
    }

    public PaginationResultVO<VideoInfo> search(Boolean highlight, String keyword, Integer orderType,Integer pageNo,Integer pageSize){
        SearchOrderTypeEnum searchOrderTypeEnum = SearchOrderTypeEnum.getByType(orderType);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery(keyword,"videoName","tags"));

        if(highlight){
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("videoName");
        }

        return null;
    }
}
