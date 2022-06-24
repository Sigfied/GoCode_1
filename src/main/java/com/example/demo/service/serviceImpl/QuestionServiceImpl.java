package com.example.demo.service.serviceImpl;

import com.example.demo.entity.Question;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.service.QuestionService;
import com.example.demo.tools.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Admin
 */
@Service("QuestionService")
public class QuestionServiceImpl implements QuestionService {

    private final QuestionMapper questionMapper;

    @Autowired
    public QuestionServiceImpl(QuestionMapper questionMapper){
        this.questionMapper = questionMapper;
    }

    @Override
    public int deleteQuestionFromTopicSet(String id) {
        return questionMapper.deleteById(id);
    }

    /**
     * 添加一道题到题目集内
     *
     * @param tid          题目集id
     * @param qtype        题目类型（此处枚举，选择题，填空题，编程题）
     * @param qdescribtion 题目描述
     * @param qpoint       题目的分数
     * @param qinput       输入样例（选择题的输入样例为选项，填空题的数据样例可以为空）
     * @param qoutput      输出样例（选择题的输出样例为答案，填空题的输出样例为答案）
     * @return int 1成功 0失败
     */
    @Override
    public int insertQuestionToTopicSet(String tid, int qtype, String qdescribtion, double qpoint, String qinput, String qoutput) {
        Question question = new Question();
        String qid = MathUtils.getPrimaryKey();
        question.setQid(qid);
        question.setQinput(qinput);
        question.setQoutput(qoutput);
        question.setQdescribtion(qdescribtion);
        question.setQpoint(BigDecimal.valueOf(qpoint));
        question.setQtype(qtype);
        question.setTid(tid);
        return questionMapper.insert(question);
    }
}
