package com.ggy.resource;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ggy.fegin.UserClient;
import com.ggy.pojo.Comment;
import com.ggy.pojo.Resource;
import com.ggy.resource.mapper.ResourceMapper;
import com.ggy.resource.service.CommentService;
import com.ggy.resource.service.RedisService;
import com.ggy.resource.service.ResourceService;

import com.ggy.resource.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ResourceserviceApplicationTests {
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserClient userClient;
    @Autowired
    private CommentService commentService;

    @Autowired
    private RedisService redisService;





    @Test
    void contextLoads() {
    }

    /***
     * 向数据库插入各种数据
     */
    @Test
    void insert(){

//        List<Resource> resources = resourceService.FindAll();
//        resources.forEach(resource -> {
//            Comment comment = new Comment();
//            comment.setResourceId(resource.getId());
//            comment.setContent("啥也不说了，楼主就是给力！");
//            comment.setReceiverId(1L);
//            comment.setUlike(0L);
//            comment.setCreatedUser("曲德宏");
//            comment.setCreatedTime(LocalDateTime.now());
//            comment.setModifyUser("曲德宏");
//            comment.setModifyTime(LocalDateTime.now());
//            comment.setStatus(1l);
//            commentService.addComment(comment);
//        });






    }


    @Test
    void text2(){
        //测试redis数据库
//        System.out.println(redisUtil.set("t1","22222"));
//        System.out.println(redisUtil.get("t1"));
//        stringRedisTemplate.opsForValue().set("t1","2222");
//        System.out.println("--------------");
//        System.out.println(stringRedisTemplate.opsForValue().get("t1"));
        System.out.println(redisService.saveLiked2Redis("1","1644207729066844161"));
    }

    @Test
    public void text1(){
//        System.out.println(resourceMapper.selectById(1l));
//        System.out.println(resourceService.FindById(1l));
//        System.out.println(resourceService.FindByType("人文历史"));
//        System.out.println(resourceService.TestFindAll();
//        Page<Resource> objects = new Page<>(4, 2);
//        IPage iPage = resourceService.TestFindAll(objects);
//        System.out.println("ipage:"+iPage);
//        iPage.getRecords().forEach(System.out::println);
        System.out.println(commentService.getComments(1623255820957040642l));



    }
   /***
    *
    * @author GaoGuiYun
    * @date 2023/2/7 15:03
    * 用于批量上传数据库文件
    */
    @Test
    public void pull()throws IOException{
        String basePath = "C:\\Users\\gaoguiyun\\Documents\\综合书籍";    //本地文件地址
        File dir = new File(basePath);

        List<File> allFileList = new ArrayList<>();

        // 判断文件夹是否存在
        if (!dir.exists()) {
            System.out.println("目录不存在");
            return;
        }

        getAllFile(dir, allFileList);  //读取本地文件内容，封装fileList

        //遍历文件list根据业务逻辑存储数据到数据库

        for (File file : allFileList) {
            //  获取 文件内容 及  文件名
//            String content = readFile(file);
//            System.out.println(content);
//            System.out.println(file.getName());
            String name=file.getName();
            char c='.';
            if(name.contains(String.valueOf(c))){
                int i=name.lastIndexOf(String.valueOf(c));
                System.out.println(file.getName());
                System.out.println(name.substring(i+1));
                System.out.println(name.substring(0,i));
                //业务处理，存储数据
                Resource resource = new Resource();
                resource.setContent(file.getPath());
                resource.setCreatedUser("admin");
                resource.setResourceType(name.substring(i+1));
                resource.setTitle(name.substring(0,i));
                resource.setCreatedTime(LocalDateTime.now());
                resource.setModifyTime(LocalDateTime.now());
                resource.setModifyUser("admin");
                resource.setStatus(1l);
                resource.setPicture("null");
                resourceMapper.insert(resource);

            }



        }
        System.out.println("该文件夹下共有" + allFileList.size() + "个文件");

    }
    public static void getAllFile(File fileInput, List<File> allFileList) {
        // 获取文件列表
        File[] fileList = fileInput.listFiles();
        assert fileList != null;
        for (File file : fileList) {
            if (file.isDirectory()) {
                // 递归处理文件夹
                // 如果不想统计子文件夹则可以将下一行注释掉
                getAllFile(file, allFileList);
            } else {
                // 如果是文件则将其加入到文件数组中
                allFileList.add(file);
            }
//            System.out.println(file.getPath());
//            System.out.println(file.getName());
        }
    }

    public static String readFile(File path) throws IOException{
        InputStreamReader inputFileReader = null;
        BufferedReader reader = null;
        //创建一个输入流对象
        InputStream file = new FileInputStream(path);
        inputFileReader = new InputStreamReader(file, "utf-8");

        reader = new BufferedReader(inputFileReader);
        //定义一个缓冲区
        byte[] bytes = new byte[1024];// 1kb
        //通过输入流使用read方法读取数据
        String tempString = "";
        String str = "";
        while((tempString = reader.readLine()) != null){
            //把数据转换为字符串
            str += tempString;
        }
        //释放资源
        reader.close();
        return str;
    }

}
