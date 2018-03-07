package com.example.luckychuan.courseselect.model;

import com.example.luckychuan.courseselect.bean.BaseBeanArray;
import com.example.luckychuan.courseselect.bean.Reply;

/**
 * Created by Luckychuan on 2018/2/14.
 */

public interface ReplyModel {

    void getReplies(String userKey, int replyId, Callback<BaseBeanArray<Reply>> callback);

    void getDebateReplies(String userKey, int replyId, Callback<BaseBeanArray<Reply>> callback);



}
