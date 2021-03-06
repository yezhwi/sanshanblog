package xyz.sanshan.main.web.controller.editor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import xyz.sanshan.common.info.EditorTypeEnum;
import xyz.sanshan.common.info.PosCodeEnum;
import xyz.sanshan.common.vo.ResponseMsgVO;
import xyz.sanshan.main.service.editor.BlogIdGenerate;
import xyz.sanshan.main.service.editor.MarkDownBlogService;

@RequestMapping("markdown-editor")
@RestController
public class MarkDownEditorController {

    @Autowired
    private MarkDownBlogService markDownBlogService;

    @Autowired
    private BlogIdGenerate blogIdGenerate;


    @PostMapping(value = "blog", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseMsgVO insertMarkDownBlog(
            @RequestParam(value = "content") String content,
            @RequestParam(value = "tag",required = false) String tag,
            @RequestParam(value = "title") String title) {

        int result = markDownBlogService.saveDO(content, title, tag);
        if (result ==0){
            return new ResponseMsgVO().buildWithMsgAndStatus(PosCodeEnum.INTER_ERROR,"未存入成功");
        }
        ResponseMsgVO responseMsgVO = new ResponseMsgVO().buildOK();
        return responseMsgVO;
    }


    @DeleteMapping(value = "blog/id:{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseMsgVO deleteMarkDownBlog(@PathVariable("id") Long id) {
        if (blogIdGenerate.getType(id)==EditorTypeEnum.MARKDOWN_EDITOR){
            int result = markDownBlogService.deleteDOById(id);
            if (result == 0) {
                return new ResponseMsgVO().buildWithMsgAndStatus(PosCodeEnum.INTER_ERROR,"未删除成功");
            }
        return new ResponseMsgVO().buildOK();
        }
        return new ResponseMsgVO().buildWithMsgAndStatus(PosCodeEnum.INTER_ERROR,"该ID不是MarkdownEditor格式");
    }


    @PostMapping(value = "blog/id:{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseMsgVO update(
            @PathVariable(value = "id")Long id,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "title", required = false) String title) {
        if(blogIdGenerate.getType(id)!=EditorTypeEnum.MARKDOWN_EDITOR){
            return  new ResponseMsgVO().buildWithMsgAndStatus(PosCodeEnum.INTER_ERROR,
                    "该ID对应的不是Markdown格式的文件");
        }
        markDownBlogService.updateSelectiveDO(id,content,title,tag);
        return new ResponseMsgVO().buildOK();
    }

}
