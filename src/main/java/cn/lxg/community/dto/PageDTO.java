package cn.lxg.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageDTO {

//    当前页数
    private Integer currPage;

//    总记录数
    private Integer totalPage;

//    每页的记录
    private List<QuestionDTO> questions;

//    页码
    private List<Integer> list = new ArrayList<>();


    public void setData(Integer currPage, Integer size, Integer count) {
        this.currPage = currPage;
        this.totalPage = count % size == 0 ? count/size: count/size+1;

        int start = 1;
        int end = totalPage < 5?totalPage:5;
        if(currPage >= 4) {
            start = currPage-2;
            end = start+(start+4<=totalPage?4: totalPage - start);
        }
        for(; start <= end; start++) {
            list.add(start);
        }
    }


}
