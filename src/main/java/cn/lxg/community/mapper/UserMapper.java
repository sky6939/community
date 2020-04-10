package cn.lxg.community.mapper;

import cn.lxg.community.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("insert into user(account_id,name,token,gmt_create,gmt_modified,avatarUrl) values(#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl}) ")
    void insertUser(User user);

    @Select("select * from user where token = #{token}")
    User selectOneByToken(@Param("token")String token);

    @Select("select * from user where id = #{id}")
    User selectOneById(@Param("id")Integer id);

    @Select("select * from user where account_id = #{accountId}")
    User selectOneByAccountId(@Param("accountId")String accountId);

    @Update("update user set name = #{name}, token = #{token},gmt_modified = #{gmtModified},avatarUrl = #{avatarUrl} where id = #{id}")
    void updateUser(User dbUser);
}
