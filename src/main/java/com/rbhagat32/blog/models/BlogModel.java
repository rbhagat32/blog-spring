package com.rbhagat32.blog.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection="blogs")
@Getter
@Setter
//@Data -> includes Getter, Setter, Required Args Constructor, ToString, EqualsAndHashCode, Value
public class BlogModel {
    @Id
    private ObjectId id;
    private String title;
    private String content;
    private Date createdAt;
}