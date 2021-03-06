package xyz.sanshan.main.pojo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ueditor_blog")
@Data
@NoArgsConstructor
public class UEditorBlogDO extends BaseBlogEditorDO {

    private static final long serialVersionUID = 4108703926461957019L;

    @Id
    private Long id;

    private String extra;
}
