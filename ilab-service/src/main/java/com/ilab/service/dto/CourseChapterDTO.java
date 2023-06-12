package com.ilab.service.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class CourseChapterDTO {

    /**
     * 课程章节id
     */
    @JSONField(name = "chapter_id")
    private Integer chapterId;

    /**
     * $column.columnComment
     */
    @JSONField(name = "course_id")
    private Long courseId;

    /**
     * $column.columnComment
     */
    @JSONField(name = "title")
    private String title;

    /**
     * $column.columnComment
     */
    @JSONField(name = "user_id")
    private Long userId;
}
