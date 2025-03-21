package com.backend.mapper;

import com.backend.entity.Question;
import com.backend.entity.Img;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    /**
     * 查询
     * @return
     */
    @Select("SELECT * FROM question_list")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "type", column = "type"),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "tags", column = "id", javaType = List.class,
                    many = @Many(select = "selectTagsByQuestionId")),
            @Result(property = "imgs", column = "id", javaType = List.class,
                    many = @Many(select = "selectImgsByQuestionId")),
            @Result(property = "displayComments", column = "id", javaType = List.class,
                    many = @Many(select = "selectCommentsByQuestionId"))
    })
    List<Question> getAllQuestions();

    // 查询每个问题的标签
    @Select("SELECT tag FROM question_tags WHERE question_id = #{questionId}")
    List<String> selectTagsByQuestionId(Long questionId);

    // 查询每个问题的图片，返回图片对象（包括 name 和 url）
    @Select("SELECT name, url FROM question_imgs WHERE question_id = #{questionId}")
    List<Img> selectImgsByQuestionId(Long questionId);

    // 查询每个问题的评论
    @Select("SELECT comment FROM question_comments WHERE question_id = #{questionId}")
    List<String> selectCommentsByQuestionId(Long questionId);

    /**
     * 插入题目
     * @param question
     */
    @Insert("INSERT INTO question_list (type, title, description) VALUES (#{type}, #{title}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertQuestion(Question question);


    // 插入 tags
    void insertTags(@Param("questionId") Long questionId, @Param("tags") List<String> tags);

    // 插入 imgs
    void insertImgs(@Param("questionId") Long questionId, @Param("imgs") List<Img> imgs);

    // 插入 displayComments
    void insertComments(@Param("questionId") Long questionId, @Param("comments") List<String> comments);


    /**
     * 根据 id 查询题目详情
     * @param id 题目 ID
     * @return 题目对象
     */
    @Select("SELECT * FROM question_list WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "type", column = "type"),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "tags", column = "id", javaType = List.class,
                    many = @Many(select = "selectTagsByQuestionId")),
            @Result(property = "imgs", column = "id", javaType = List.class,
                    many = @Many(select = "selectImgsByQuestionId")),
            @Result(property = "displayComments", column = "id", javaType = List.class,
                    many = @Many(select = "selectCommentsByQuestionId"))
    })
    Question getQuestionById(Long id);
    /**
     * 根据 ID 删除题目
     * @param id 题目 ID
     */
    @Delete("DELETE FROM question_list WHERE id = #{id}")
    void deleteQuestionById(Long id);
}
