package cn.lxg.community.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import cn.lxg.community.dto.PageDTO;
import cn.lxg.community.dto.QuestionDTO;
import cn.lxg.community.mapper.QuestionMapper;
import cn.lxg.community.mapper.UserMapper;
import cn.lxg.community.model.Question;
import cn.lxg.community.model.User;
import com.xiaoleilu.hutool.db.Page;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public PageDTO findAll(Integer currPage, Integer size) {
        int totalCount = questionMapper.findCount();
        int totalPage = totalCount % size == 0 ? totalCount/size: totalCount/size+1;
        if(currPage > totalPage) {
            currPage = totalPage;
        } else if(currPage < 1) {
            currPage = 1;
        }
        List<QuestionDTO> questionDTOs = new ArrayList<>();
        //查询出所有的问题，然后根据问题，查找到是谁发布的
        List<Question> questions = questionMapper.findAll((currPage-1)*size, size);
        for (Question question : questions) {
            //创建QuestionDTO对象
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            User user = userMapper.selectOneById(question.getCreator());
            questionDTO.setUser(user);
            questionDTOs.add(questionDTO);
        }
        PageDTO pageDTO = new PageDTO();
        pageDTO.setQuestions(questionDTOs);
        pageDTO.setData(currPage, size, totalCount);
        return pageDTO;
    }


}
