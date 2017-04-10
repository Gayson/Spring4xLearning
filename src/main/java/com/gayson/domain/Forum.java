package com.gayson.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.io.Serializable;

public class Forum implements Serializable {
    @Id
    @Column(name = "forum_id")
    private String forumId;
    @Column(name = "forum_name")
    private String forumName;
    @Column(name = "forum_desc")
    private String forumDesc;
    @Lob
    private String context;

    @Lob
    @Column(name = "post_attach")
    private byte[] postAttach;

    public String getForumId() {
        return forumId;
    }

    public void setForumId(String forumId) {
        this.forumId = forumId;
    }

    public String getForumName() {
        return forumName;
    }

    public void setForumName(String forumName) {
        this.forumName = forumName;
    }

    public String getForumDesc() {
        return forumDesc;
    }

    public void setForumDesc(String forumDesc) {
        this.forumDesc = forumDesc;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public byte[] getPostAttach() {
        return postAttach;
    }

    public void setPostAttach(byte[] postAttach) {
        this.postAttach = postAttach;
    }
}
