package com.easylive.service.impl;

import com.easylive.component.EsSearchComponent;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.enums.PageSize;
import com.easylive.entity.enums.ResponseCodeEnum;
import com.easylive.entity.enums.SearchOrderTypeEnum;
import com.easylive.entity.enums.UserActionTypeEnum;
import com.easylive.entity.po.VideoDanmu;
import com.easylive.entity.po.VideoInfo;
import com.easylive.entity.query.SimplePage;
import com.easylive.entity.query.VideoDanmuQuery;
import com.easylive.entity.query.VideoInfoQuery;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.exception.BusinessException;
import com.easylive.mappers.VideoDanmuMapper;
import com.easylive.mappers.VideoInfoMapper;
import com.easylive.service.VideoDanmuService;
import com.easylive.utils.StringTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * 视频弹幕 业务接口实现
 */

@Service("videoDanmuService")
public class VideoDanmuServiceImpl implements VideoDanmuService {
	@Resource
	private VideoInfoMapper<VideoInfo, VideoInfoQuery> videoInfoMapper;
	@Resource
	private VideoDanmuMapper<VideoDanmu, VideoDanmuQuery> videoDanmuMapper;
	@Resource
	private EsSearchComponent esSearchComponent;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<VideoDanmu> findListByParam(VideoDanmuQuery param) {
		return this.videoDanmuMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(VideoDanmuQuery param) {
		return this.videoDanmuMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<VideoDanmu> findListByPage(VideoDanmuQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<VideoDanmu> list = this.findListByParam(param);
		PaginationResultVO<VideoDanmu> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(VideoDanmu bean) {
		return this.videoDanmuMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<VideoDanmu> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.videoDanmuMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<VideoDanmu> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.videoDanmuMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(VideoDanmu bean, VideoDanmuQuery param) {
		StringTools.checkParam(param);
		return this.videoDanmuMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(VideoDanmuQuery param) {
		StringTools.checkParam(param);
		return this.videoDanmuMapper.deleteByParam(param);
	}

	/**
	 * 根据DanmuId获取对象
	 */
	@Override
	public VideoDanmu getVideoDanmuByDanmuId(Integer danmuId) {
		return this.videoDanmuMapper.selectByDanmuId(danmuId);
	}

	/**
	 * 根据DanmuId修改
	 */
	@Override
	public Integer updateVideoDanmuByDanmuId(VideoDanmu bean, Integer danmuId) {
		return this.videoDanmuMapper.updateByDanmuId(bean, danmuId);
	}

	/**
	 * 根据DanmuId删除
	 */
	@Override
	public Integer deleteVideoDanmuByDanmuId(Integer danmuId) {
		return this.videoDanmuMapper.deleteByDanmuId(danmuId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveVideoDanmu(VideoDanmu videoDanmu) {
		VideoInfo videoInfo = videoInfoMapper.selectByVideoId(videoDanmu.getVideoId());
		if(videoInfo==null){
			throw new BusinessException(ResponseCodeEnum.CODE_600);
		}
		//若up主关闭弹幕功能则不允许发送弹幕
		if(videoInfo.getInteraction()!=null && videoInfo.getInteraction().contains(Constants.ONE.toString())){
			throw new BusinessException("up主已经关闭了弹幕！");
		}
/*	•	事务保证：
	•	如果 insert 操作成功，但 update 操作失败，整个事务会回滚，插入的弹幕记录也会被撤销。
	•	事务管理通过 Spring 和数据库共同完成。*/
		this.videoDanmuMapper.insert(videoDanmu);
		//使用动态拼接sql解决并发问题，此处第二个参数传入弹幕field，更新弹幕数量
		this.videoInfoMapper.updateCountInfo(videoDanmu.getVideoId(),UserActionTypeEnum.VIDEO_DANMU.getField(),1);
		// 更新ES 弹幕数量
		esSearchComponent.updateDocCount(videoDanmu.getVideoId(), SearchOrderTypeEnum.VIDEO_DANMU.getField(), 1);
	}

	@Override
	public void deleteDanmu(String userId, Integer danmuId) {
		VideoDanmu videoDanmu = videoDanmuMapper.selectByDanmuId(danmuId);
		if(videoDanmu==null){
			throw new BusinessException(ResponseCodeEnum.CODE_600);
		}
		VideoInfo videoInfo = videoInfoMapper.selectByVideoId(videoDanmu.getVideoId());
		if(videoInfo==null){
			throw new BusinessException(ResponseCodeEnum.CODE_600);
		}
		if(userId!=null && !videoInfo.getUserId().equals(userId)){
			throw new BusinessException(ResponseCodeEnum.CODE_600);
		}
		videoDanmuMapper.deleteByDanmuId(danmuId);
	}
}