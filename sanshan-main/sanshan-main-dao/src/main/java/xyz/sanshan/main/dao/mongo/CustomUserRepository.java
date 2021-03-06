package xyz.sanshan.main.dao.mongo;

import com.mongodb.WriteResult;
import xyz.sanshan.main.pojo.entity.UserDO;

public interface CustomUserRepository {
    /**
     更改密码
     */
    WriteResult changePassword( final  String username,final  String password);

    /**
     *更改用户信息 包括email BlogLink avatar(头像)
     * @param userDO
     * @return
     */
    WriteResult changeUserInfo(final UserDO userDO);
}
