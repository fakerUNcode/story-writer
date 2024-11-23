package com.easylive.entity.constants;

public class Constants {
    //主题值
    public static final Integer ONE = 1;
    public static final Integer ZERO = 0;
    //ID长度
    public static final Integer LENGTH_10 = 10;
    public static final Integer LENGTH_15 = 15;

    public static final Integer LENGTH_30 = 30;
    public static final Long MB_SIZE = 1024*1024L;

    //用于效验密码是否符合规范的正则表达式（必须包含数字字母和特殊符号，8-18位）
    public static final String REGEX_PASSWORD =  "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,18}$";
    //将REDIS失效时间常数设为一分钟（60000毫秒）
    public static final Integer REDIS_KEY_EXPIRES_ONE_MIN = 60000;
    //REDIS存储token的失效期为1天
    public static final Integer REDIS_KEY_EXPIRES_ONE_DAY = REDIS_KEY_EXPIRES_ONE_MIN*60*24;
    //定义一个以秒为单位的1天失效期
    public static final Integer TIME_SECONDS_DAY = REDIS_KEY_EXPIRES_ONE_DAY/1000;

    public static final String FILE_FOLDER = "file/";
    public static final String FILE_COVER = "cover/";
    public static final String FILE_VIDEO = "video/";
    public static final String FILE_FOLDER_TEMP = "temp/";

    public static final String IMAGE_THUMBNAIL_SUFFIX = "_thumbnail.jpg";

    //作为 Redis 键的公共前缀，标识所有与当前项目相关的 Redis 键。避免键名冲突，尤其是多项目共享一个 Redis 数据库的场景。
    //在定义 Redis 键时，将其作为前缀拼接具体的业务键。
    public static final String REDIS_KEY_PREFIX = "easylive:";

    //用于验证码相关的 Redis 键。
    //键名格式示例：easylive:checkCode:{用户唯一标识}。
    public static String REDIS_KEY_CHECK_CODE = REDIS_KEY_PREFIX + "checkCode:";

    public static final String REDIS_KEY_TOKEN_WEB = REDIS_KEY_PREFIX + "token:web:";
    public static final String REDIS_KEY_TOKEN_ADMIN = REDIS_KEY_PREFIX + "token:admin:";
    public static final String TOKEN_WEB = "token";
    public static final String TOKEN_ADMIN = "adminToken";

    public static final String REDIS_KEY_CATEGORY_LIST = REDIS_KEY_PREFIX + "category:list:";

    public static final String REDIS_KEY_UPLOADING_FILE = REDIS_KEY_PREFIX + "uploading:";

    //系统设置
    public static final String REDIS_KEY_SYS_SETTING = REDIS_KEY_PREFIX + "sysSetting:";

}
