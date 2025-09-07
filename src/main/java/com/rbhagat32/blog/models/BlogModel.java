package com.rbhagat32.blog.models;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection="blogs")
@Data
public class BlogModel {
    @Id
    private ObjectId id;

    @NonNull
    private String title;

    private String content;

    private Date createdAt;
}