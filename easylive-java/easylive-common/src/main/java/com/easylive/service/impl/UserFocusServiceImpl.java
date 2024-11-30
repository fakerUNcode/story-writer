package com.easylive.service.impl;

import com.easylive.entity.enums.PageSize;
import com.easylive.entity.enums.ResponseCodeEnum;
import com.easylive.entity.po.UserFocus;
import com.easylive.entity.po.UserInfo;
import com.easylive.entity.query.SimplePage;
import com.easylive.entity.query.UserFocusQuery;
import com.easylive.entity.query.UserInfoQuery;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.exception.BusinessException;
import com.easylive.mappers.UserFocusMapper;
import com.easylive.mappers.UserInfoMapper;
import com.easylive.service.UserFocusService;
import com.easylive.utils.StringTools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 *  业务接口实现
 */
@Service("userFocusService")
public class UserFocusServiceImpl implements UserFocusService {

	@Resource
	private UserFocusMapper<UserFocus, UserFocusQuery> userFocusMapper;
	@Resource
	private UserInfoMapper<UserInfo, UserInfoQuery> userInfoMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<UserFocus> findListByParam(UserFocusQuery param) {
		return this.userFocusMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(UserFocusQuery param) {
		return this.userFocusMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<UserFocus> findListByPage(UserFocusQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<UserFocus> list = this.findListByParam(param);
		PaginationResultVO<UserFocus> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(UserFocus bean) {
		return this.userFocusMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<UserFocus> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.userFocusMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<UserFocus> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.userFocusMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(UserFocus bean, UserFocusQuery param) {
		StringTools.checkParam(param);
		return this.userFocusMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(UserFocusQuery param) {
		StringTools.checkParam(param);
		return this.userFocusMapper.deleteByParam(param);
	}

	/**
	 * 根据UserIdAndFocusUserId获取对象
	 */
	@Override
	public UserFocus getUserFocusByUserIdAndFocusUserId(String userId, String focusUserId) {
		return this.userFocusMapper.selectByUserIdAndFocusUserId(userId, focusUserId);
	}

	/**
	 * 根据UserIdAndFocusUserId修改
	 */
	@Override
	public Integer updateUserFocusByUserIdAndFocusUserId(UserFocus bean, String userId, String focusUserId) {
		return this.userFocusMapper.updateByUserIdAndFocusUserId(bean, userId, focusUserId);
	}

	/**
	 * 根据UserIdAndFocusUserId删除
	 */
	@Override
	public Integer deleteUserFocusByUserIdAndFocusUserId(String userId, String focusUserId) {
		return this.userFocusMapper.deleteByUserIdAndFocusUserId(userId, focusUserId);
	}

	//关注
	@Override
	public void focusUser(String userId, String focusUserId) {
		if(userId.equals(focusUserId)){
			throw new BusinessException("不能关注自己哦！");
		}
		UserFocus dbInfo = this.userFocusMapper.selectByUserIdAndFocusUserId(userId,focusUserId);
		if(dbInfo!=null){
			return;
		}
		//只能关注真实存在的用户
		UserInfo userInfo = userInfoMapper.selectByUserId(focusUserId);
		if(userInfo==null){
			throw new BusinessException(ResponseCodeEnum.CODE_600);
		}
		UserFocus focus = new UserFocus();
		focus.setUserId(userId);
		focus.setFocusUserId(focusUserId);
		focus.setFocusTime(new Date());
		this.userFocusMapper.insert(focus);

	}

	//取消关注
	@Override
	public void cancelFocus(String userId, String focusUserId) {
		this.userFocusMapper.deleteByUserIdAndFocusUserId(userId,focusUserId);
	}
}