package com.ggy.resource.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ggy.config.AjaxResult;
import com.ggy.config.Result;
import com.ggy.fegin.UserClient;
import com.ggy.pojo.Comment;
import com.ggy.pojo.Resource;
import com.ggy.pojo.User;
import com.ggy.resource.service.CommentService;
import com.ggy.resource.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private CommentService commentService;
    public String ecspath="/home/resources/";

    //发布资源
    @RequestMapping(value = "/public", method = RequestMethod.POST)
    public AjaxResult insertResource(@RequestBody Resource resource, @RequestParam(value = "refile", required = false) MultipartFile refile) {
        if (refile != null) {
            final String originalFilename = UUID.randomUUID().toString() + refile.getOriginalFilename();
            System.out.println("文件名为：" + originalFilename);
//            String savePath = "C:\\Users\\gaoguiyun\\Documents\\综合书籍\\" + originalFilename;
            String savePath = ecspath + originalFilename;
            resource.setContent(originalFilename);
            resourceService.uploadResource(resource);
            try {
                refile.transferTo(new File(savePath));
                return Result.success("上传完成");
            } catch (Exception ex) {
                ex.printStackTrace();
                return Result.error();
            }
        } else {
            int i = resourceService.uploadResource(resource);
            if (i != 0) {
                return Result.success("上传完成");
            } else {
                return Result.error();
            }
        }


//        return Result.success(resource);

    }

    //删除资源
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public AjaxResult deleteResource(@RequestParam Long id) {
        int i = resourceService.DeleteResource(id);

        if (i != 0) {
            return Result.success();
        } else {
            return Result.error();
        }

    }

    ;

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public AjaxResult updateResource(@RequestBody Resource resource) {
        int i = resourceService.UpdateResource(resource);
        // return Result.success(resource);

        if (i != 0) {
            return Result.success();
        } else {
            return Result.error();
        }

    }

    ;

    //获取资源详情
    @RequestMapping(value = "/getresource/{id}", method = RequestMethod.GET)
    public AjaxResult findResourceById(@PathVariable("id") Long id) {
        System.out.println("到了控制层," + "id:" + id);
        Resource resource = resourceService.FindById(id);
        if (resource != null) {
            return Result.success(resource);
        } else {
            return Result.error();
        }
    }

    ;

    //获取资源列表 后期做分页查询  根据各种条件返回
    @RequestMapping(value = "/getresources", method = RequestMethod.GET)
    public AjaxResult findAllResources() {
        List<Resource> resources = resourceService.FindAll();
        if (resources != null) {
            return Result.success(resources);
        } else {
            return Result.error();
        }


    }

    ;

    //获取资源列表 后期做分页查询  根据各种条件返回
    @RequestMapping(value = "/getresourcespage", method = RequestMethod.POST)
    public AjaxResult findResourcesByPage(@RequestBody String s) {
        s = s.replace("\"", "");
        System.out.println("这个字符串的值为" + s);
        //字符串格式 页码::类型::排序方式::升序降序
        String[] slist = s.split("::");
        Page<Resource> objectPage = new Page<>(Long.valueOf(slist[0]), 20);
        IPage<Resource> resources = resourceService.FindResourceByPage(objectPage, slist);

        resources.getRecords().forEach(System.out::println);
        if (resources != null) {
            return Result.success(resources);
        } else {
            return Result.error();
        }


    }

    ;

    //获取某个人发布的资源
    @RequestMapping(value = "/getbyuser/{id}", method = RequestMethod.GET)
    public AjaxResult findResourcesByUser(@PathVariable("id") Long id) {
        List<Resource> resources = resourceService.FindByUser(id);
        if (resources != null) {
            return Result.success(resources);
        } else {
            return Result.error();
        }
    }

    ;

    //获取某类资源
    @RequestMapping(value = "/getbytype", method = RequestMethod.POST)
    public AjaxResult findResourcesByType(@RequestBody String type) {

        List<Resource> resources = resourceService.FindByType(type.replace("\"", ""));
        if (resources != null) {
            return Result.success(resources);
        } else {
            return Result.error();
        }
    }

    ;

    //----------------------------------------------------------------------------------


    //获取资源下评论
    @RequestMapping(value = "/getcomments/{id}", method = RequestMethod.GET)
    public AjaxResult getComments(@PathVariable("id") Long id) {
        HashMap<Object, Object> comments = commentService.getComments(id);
        comments.forEach((key, value) -> {
            System.out.println(key.toString() + value.toString());
        });


        if (comments != null) {
            return Result.success(comments);

        } else {
            return Result.error();
        }


    }

    //添加评论
    @RequestMapping(value = "/addcomment", method = RequestMethod.POST)
    public AjaxResult addComment(@RequestBody Comment comment) {
        int i = commentService.addComment(comment);
        if (i != 0) {
            return Result.success();
        } else {
            return Result.error();
        }


    }

    ;

//    //对评论点赞   应该只获取数量与评论id的  不过暂且这样
//    @RequestMapping(value = "/up",method = RequestMethod.POST)
//    public AjaxResult upComment(@RequestBody Comment comment){
//        int i=commentService.upComment(comment);
//
//        if (i!=0){
//            return Result.success();
//        }else {
//            return Result.error();
//        }
//    };

    //收藏资源  用户信息与资源信息
    @RequestMapping(value = "/collect", method = RequestMethod.POST)
    public AjaxResult collectResource(@RequestBody Resource resource) {
        int i = resourceService.collectResource(resource);
        if (i != 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    ;

    //历史记录
    @RequestMapping(value = "/addhistory", method = RequestMethod.POST)
    public AjaxResult historyResource(@RequestBody Resource resource) {
        int i = resourceService.historyResource(resource);
        if (i != 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    ;

    //对资源点赞
    @RequestMapping(value = "/up", method = RequestMethod.POST)
    public AjaxResult upResource(@RequestBody String msg) {
        msg = msg.replace("\"", "");
        String[] msgs = msg.split("::");
        System.out.println("点赞的信息" + msg);
        if (resourceService.upResource(msgs)) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    ;

    //对资源取消点赞
    @RequestMapping(value = "/down", method = RequestMethod.POST)
    public AjaxResult downResource(@RequestBody String msg) {
        msg = msg.replace("\"", "");
        String[] msgs = msg.split("::");

        if (resourceService.downResource(msgs)) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    ;

    //搜索
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public AjaxResult SearchResource(@RequestParam String msg) {
        List<Resource> resources = resourceService.SearchResource(msg);
        if (resources != null) {
            return Result.success(resources);
        } else {
            return Result.error("没找到搜索内容");
        }
    }

    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    public AjaxResult UploadFile(@RequestParam("refile") MultipartFile refile, @RequestParam("resourceId") Long id) {
        if (refile != null) {
            final String originalFilename = UUID.randomUUID().toString() + refile.getOriginalFilename();
            if (id != null || id != 0) {
                Resource resource = resourceService.FindById(id);
                resource.setContent(originalFilename);
            }
            System.out.println("文件名为：" + originalFilename);
//                    String savePath = "C:\\Users\\gaoguiyun\\Documents\\综合书籍\\" + originalFilename;
            String savePath =  ecspath + originalFilename;
            try {
                refile.transferTo(new File(savePath));
                return Result.success("上传完成");
            } catch (Exception ex) {
                ex.printStackTrace();
                return Result.error();

            }
        } else {
            return Result.error("空文件");
        }
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(@RequestParam("id") Long id, HttpServletResponse response) throws Exception {
// 路径可以指定当前项目相对路径
        Resource resource = resourceService.FindById(id);


//        File file = new File("C:\\Users\\gaoguiyun\\Documents\\综合书籍\\" + resource.getContent());
        File file = new File(ecspath + resource.getContent());
        if (file.exists()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("application/octet-stream");
            // 如果文件名为中文需要设置编码
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(resource.getContent(), "utf8"));
            // 返回前端文件名需要添加
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");

            byte[] bytes = new byte[1024];
            int len;
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
        }
    }
}



