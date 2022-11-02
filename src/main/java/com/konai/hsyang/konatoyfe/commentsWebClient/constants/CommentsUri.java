package com.konai.hsyang.konatoyfe.commentsWebClient.constants;

public class CommentsUri {

    public static final String COMMENTS_FIND_LIST = "/api/v2/comments/list";
    public static final String COMMENTS_SAVE = "/api/v2/comments/save";
    public static final String COMMENTS_DELETE = "/api/v2/comments/delete/{commentID}";
    public static final String COMMENTS_UPDATE = "/api/v2/comments/update/{commentID}";
    public static final String POSTS_BY_COMMENT = "/api/v2/comments/click/{commentID}";
    public static final String COMMENTS_BY_ID = "/api/v2/comments/{userID}";
}
