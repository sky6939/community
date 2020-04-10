package cn.lxg.community.service;

import cn.lxg.community.dto.PageDTO;
import cn.lxg.community.dto.QuestionDTO;
import cn.lxg.community.exception.CustomizeErrorCode;
import cn.lxg.community.exception.CustomizeException;
import cn.lxg.community.mapper.QuestionMapper;
import cn.lxg.community.mapper.UserMapper;
import cn.lxg.community.model.Question;
import cn.lxg.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public PageDTO findAll(Integer currPage, Integer size) {
        int totalCount = questionMapper.findCount();
        int totalPage = totalCount % size == 0 ? totalCount/size: totalCount/size+1;
        if(totalPage == 0) {
            return null;
        }
        if(currPage > totalPage) {
            currPage = totalPage;
        } else if(currPage < 1) {
            currPage = 1;
        }
        List<QuestionDTO> questionDTOs = new ArrayList<>();
        //查询出所有的问题，然后根据问题，查找到是谁发布的
        List<Question> questions = questionMapper.findAll((currPage-1)*size, size);
        traverseDTO(questions,questionDTOs);
        PageDTO pageDTO = new PageDTO();
        pageDTO.setQuestions(questionDTOs);
        pageDTO.setData(currPage, size, totalCount);
        return pageDTO;
    }

    public PageDTO findAllById(Integer id,Integer currPage, Integer size) {
        int totalCount = questionMapper.findCountById(id);
        int totalPage = totalCount % size == 0 ? totalCount/size: totalCount/size+1;
        if(currPage > totalPage) {
            currPage = totalPage;
        } else if(currPage < 1) {
            currPage = 1;
        }
        List<QuestionDTO> questionDTOs = new ArrayList<>();
        //查询出所有的问题，然后根据问题，查找到是谁发布的
        List<Question> questions = questionMapper.findAllById(id,(currPage-1)*size, size);
        traverseDTO(questions,questionDTOs);
        PageDTO pageDTO = new PageDTO();
        pageDTO.setQuestions(questionDTOs);
        pageDTO.setData(currPage, size, totalCount);
        return pageDTO;
    }

    public QuestionDTO findById(Integer id) {
        QuestionDTO questionDTO = new QuestionDTO();
        Question question = questionMapper.findById(id);
        if(question == null) {
            throw new  CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setUser(userMapper.selectOneById(question.getCreator()));
        return questionDTO;
    }

    public void traverseDTO(List<Question> questions,List<QuestionDTO> questionDTOs) {
        for (Question question : questions) {
            //创建QuestionDTO对象
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            User user = userMapper.selectOneById(question.getCreator());
            questionDTO.setUser(user);
            questionDTOs.add(questionDTO);
        }
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null) {//保存
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.save(question);
        } else {//更新
            question.setGmtModified(System.currentTimeMillis());
            Integer count = questionMapper.update(question);
            if(count != 1) {
                throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }
}
