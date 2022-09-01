package com.qin.controller;

import com.qin.Utils.SecurityUtils;
import com.qin.constants.SystemConstants;
import com.qin.domain.ResponseResult;
import com.qin.domain.entity.Comment;
import com.qin.enums.AppHttpCodeEnum;
import com.qin.exception.SystemException;
import com.qin.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @GetMapping("commentList")
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize){
        return commentService.commentList(SystemConstants.ARTICLE_COMMENT,articleId, pageNum, pageSize);
    }

    @PostMapping
    public ResponseResult addComment(@RequestBody Comment comment){
//        if(SecurityUtils.getAuthentication().getPrincipal()){
//            throw new SystemException(AppHttpCodeEnum.NEED_LOGIN);
//        }
        try {
            Long a = SecurityUtils.getUserId();
        }catch (Exception e){
            throw new SystemException(AppHttpCodeEnum.NEED_LOGIN);
        }
        return commentService.addComment(comment);
    }

    @GetMapping("linkCommentList")
    public ResponseResult linkCommentList(Integer pageNum, Integer pageSize){
        return commentService.commentList(SystemConstants.LINK_COMMENT,null, pageNum, pageSize);
    }
}
