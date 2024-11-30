package com.easylive.mappers;

import org.apache.ibatis.annotations.Param;

/**
 *  数据库操作接口
 */
public interface UserFocusMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据UserIdAndFocusUserId更新
	 */
	 Integer updateByUserIdAndFocusUserId(@Param("bean") T t,@Param("userId") String userId,@Param("focusUserId") String focusUserId);


	/**
	 * 根据UserIdAndFocusUserId删除
	 */
	 Integer deleteByUserIdAndFocusUserId(@Param("userId") String userId,@Param("focusUserId") String focusUserId);


	/**
	 * 根据UserIdAndFocusUserId获取对象
	 */
	 T selectByUserIdAndFocusUserId(@Param("userId") String userId,@Param("focusUserId") String focusUserId);

	/*获取粉丝数和关注数*/
	Integer selectFansCount(@Param("userId")String userId);

	Integer selectFocusCount(@Param("userId")String userId);
}
