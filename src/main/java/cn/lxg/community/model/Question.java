package cn.lxg.community.model;

import lombok.Data;

import java.io.Serializable;

/**
 * (Question)实体类
 */
@Data
public class Question implements Serializable {
    private static final long serialVersionUID = -29311715351876455L;
    
    private Integer id;
    
    private String title;
    
    private String description;
    
    private Long gmtCreate;
    
    private Long gmtModified;
    
    private Integer creator;
    
    private Integer commentCount;
    
    private Integer viewCount;
    
    private Integer likeCount;
    
    private String tag;


}