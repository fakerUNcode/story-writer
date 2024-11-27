package com.easylive.service.impl;

import com.easylive.entity.constants.Constants;
import com.easylive.entity.enums.PageSize;
import com.easylive.entity.enums.ResponseCodeEnum;
import com.easylive.entity.enums.UserActionTypeEnum;
import com.easylive.entity.po.UserAction;
import com.easylive.entity.po.VideoInfo;
import com.easylive.entity.query.SimplePage;
import com.easylive.entity.query.UserActionQuery;
import com.easylive.entity.query.VideoInfoQuery;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.exception.BusinessException;
import com.easylive.mappers.UserActionMapper;
import com.easylive.mappers.UserInfoMapper;
import com.easylive.mappers.VideoInfoMapper;
import com.easylive.service.UserActionService;
import com.easylive.utils.StringTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * 用户行为 点赞、评论 业务接口实现
 */
@Service("userActionService")
public class UserActionServiceImpl implements UserActionService {

	@Resource
	private VideoInfoMapper<VideoInfo, VideoInfoQuery> videoInfoMapper;
	@Resource
	private UserActionMapper<UserAction, UserActionQuery> userActionMapper;
	@Resource
	private UserInfoMapper userInfoMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<UserAction> findListByParam(UserActionQuery param) {
		return this.userActionMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(UserActionQuery param) {
		return this.userActionMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<UserAction> findListByPage(UserActionQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<UserAction> list = this.findListByParam(param);
		PaginationResultVO<UserAction> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(UserAction bean) {
		return this.userActionMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<UserAction> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.userActionMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<UserAction> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.userActionMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(UserAction bean, UserActionQuery param) {
		StringTools.checkParam(param);
		return this.userActionMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(UserActionQuery param) {
		StringTools.checkParam(param);
		return this.userActionMapper.deleteByParam(param);
	}

	/**
	 * 根据ActionId获取对象
	 */
	@Override
	public UserAction getUserActionByActionId(Integer actionId) {
		return this.userActionMapper.selectByActionId(actionId);
	}

	/**
	 * 根据ActionId修改
	 */
	@Override
	public Integer updateUserActionByActionId(UserAction bean, Integer actionId) {
		return this.userActionMapper.updateByActionId(bean, actionId);
	}

	/**
	 * 根据ActionId删除
	 */
	@Override
	public Integer deleteUserActionByActionId(Integer actionId) {
		return this.userActionMapper.deleteByActionId(actionId);
	}

	/**
	 * 根据VideoIdAndCommentIdAndActionTypeAndUserId获取对象
	 */
	@Override
	public UserAction getUserActionByVideoIdAndCommentIdAndActionTypeAndUserId(String videoId, Integer commentId, Integer actionType, String userId) {
		return this.userActionMapper.selectByVideoIdAndCommentIdAndActionTypeAndUserId(videoId, commentId, actionType, userId);
	}

	/**
	 * 根据VideoIdAndCommentIdAndActionTypeAndUserId修改
	 */
	@Override
	public Integer updateUserActionByVideoIdAndCommentIdAndActionTypeAndUserId(UserAction bean, String videoId, Integer commentId, Integer actionType, String userId) {
		return this.userActionMapper.updateByVideoIdAndCommentIdAndActionTypeAndUserId(bean, videoId, commentId, actionType, userId);
	}

	/**
	 * 根据VideoIdAndCommentIdAndActionTypeAndUserId删除
	 */
	@Override
	public Integer deleteUserActionByVideoIdAndCommentIdAndActionTypeAndUserId(String videoId, Integer commentId, Integer actionType, String userId) {
		return this.userActionMapper.deleteByVideoIdAndCommentIdAndActionTypeAndUserId(videoId, commentId, actionType, userId);
	}

	//保存用户操作（点赞、投币、收藏）
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveAction(UserAction bean) {
		VideoInfo videoInfo = videoInfoMapper.selectByVideoId(bean.getVideoId());
		if(videoInfo==null){
			throw new BusinessException(ResponseCodeEnum.CODE_600);
		}
		bean.setVideoUserId(videoInfo.getUserId());

		UserActionTypeEnum actionTypeEnum = UserActionTypeEnum.getByType(bean.getActionType());
		if(actionTypeEnum==null){
			throw new BusinessException(ResponseCodeEnum.CODE_600);
		}
		UserAction dbAction = userActionMapper.selectByVideoIdAndCommentIdAndActionTypeAndUserId(bean.getVideoId(),bean.getCommentId(),bean.getActionType(), bean.getUserId());
		bean.setActionTime(new Date());
		switch (actionTypeEnum){
			/*点赞和收藏操作*/
			case VIDEO_LIKE:
			case VIDEO_COLLECT:
				//取消操作则删除该action
				if(dbAction!=null){
					userActionMapper.deleteByActionId(dbAction.getActionId());
				}else {
					userActionMapper.insert(bean);
				}
				//取消操作则从数据库中的操作数减去1，比如取消点赞则点赞数减1
				Integer changeCount = dbAction == null ? Constants.ONE : -Constants.ONE;
				videoInfoMapper.updateCountInfo(bean.getVideoId(), actionTypeEnum.getField(), changeCount);

				if(actionTypeEnum == UserActionTypeEnum.VIDEO_COLLECT.VIDEO_LIKE){
					//TODO 更新es收藏数量
				}
				break;
			/*投币操作*/
			case VIDEO_COIN:
				//每个用户都不可以给自己投币
				if(videoInfo.getUserId().equals(bean.getUserId())){
					throw new BusinessException("自己不能给自己投币哦！");
				}
				//一个用户给一个视频仅可投币一次
				if(dbAction!=null){
					throw new BusinessException("对本稿件的投币次数已用完！");
				}
				//减少自己的硬币（使用SQL锁保证并发下硬币数不会出现错误）
				Integer updateCount = userInfoMapper.updateCoinCountInfo(bean.getUserId(), -bean.getActionCount());
				//更新行数返回为0说明更新操作失败
				if(updateCount==0){
					throw new BusinessException("硬币不足！");
				}
				//给up主增加硬币
				updateCount = userInfoMapper.updateCoinCountInfo(videoInfo.getUserId(), bean.getActionCount());
				if(updateCount==0){
					throw new BusinessException("投币失败！");
				}
				userActionMapper.insert(bean);
				videoInfoMapper.updateCountInfo(bean.getVideoId(), actionTypeEnum.getField(), bean.getActionCount());
				break;

		}
	}
}