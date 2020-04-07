package cn.lxg.community.service;

import cn.lxg.community.dto.QuestionDTO;
import cn.lxg.community.mapper.QuestionMapper;
import cn.lxg.community.mapper.UserMapper;
import cn.lxg.community.model.Question;
import cn.lxg.community.model.User;
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

    public List<QuestionDTO> findAll() {
        List<QuestionDTO> questionDTOs = new ArrayList<>();
        //查询出所有的问题，然后根据问题，查找到是谁发布的
        List<Question> questions = questionMapper.findAll();
        for (Question question : questions) {
            //创建QuestionDTO对象
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            User user = userMapper.selectOneById(question.getCreator());
            questionDTO.setUser(user);
            questionDTOs.add(questionDTO);
        }
        return questionDTOs;
    }


}
