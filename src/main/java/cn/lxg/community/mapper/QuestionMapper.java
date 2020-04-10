package cn.lxg.community.mapper;

import cn.lxg.community.model.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title, description, gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag}) ")
    void save(Question question);

   /* @Select("select * from question order by gmt_create desc")
    List<Question> findAll();*/

    @Select("select * from question order by gmt_create desc limit #{currPage},#{size}")
    List<Question> findAll(@Param("currPage")Integer currPage, @Param("size")Integer size);

    @Select("select * from question where creator = #{id} order by gmt_create desc limit #{currPage},#{size}")
    List<Question> findAllById(@Param("id")Integer id,@Param("currPage")Integer currPage, @Param("size")Integer size);

    @Select("select count(1) from question")
    int findCount();

    @Select("select count(1) from question where creator = #{id}")
    int findCountById(@Param("id") Integer id);

    @Select("select * from question where id = #{id}")
    Question findById(@Param("id")Integer id);

    @Update("update question set title = #{title}, description = #{description}, gmt_modified = #{gmtModified}, tag = #{tag} where id = #{id}")
    Integer update(Question question);
}
