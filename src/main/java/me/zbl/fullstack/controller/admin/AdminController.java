package me.zbl.fullstack.controller.admin;

import com.alibaba.fastjson.JSONObject;
import me.zbl.fullstack.controller.base.BaseController;
import me.zbl.fullstack.entity.Article;
import me.zbl.fullstack.entity.Resume;
import me.zbl.fullstack.entity.dto.form.AdminUserPwdModifyForm;
import me.zbl.fullstack.entity.dto.request.TableKeyModel;
import me.zbl.fullstack.entity.vo.BlogModifyModel;
import me.zbl.fullstack.entity.dto.form.BlogAddForm;
import me.zbl.fullstack.entity.dto.form.BlogModifyForm;
import me.zbl.fullstack.entity.dto.form.UserLoginForm;
import me.zbl.fullstack.entity.vo.ResumeModifyModel;
import me.zbl.fullstack.service.api.IAdminBlogService;
import me.zbl.fullstack.service.api.IAdminUserService;
import me.zbl.fullstack.service.api.IAdminUserService.ModifyPwdResult;
import me.zbl.fullstack.service.api.IResumeService;
import me.zbl.fullstack.utils.FileUtil;
import me.zbl.fullstack.utils.Message;
import me.zbl.fullstack.utils.OssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static me.zbl.fullstack.consts.ViewConsts.*;

/**
 * 后台控制器
 *
 * @author James
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

  @Autowired
  IAdminBlogService mBlogService;
  @Autowired
  IAdminUserService mAdminUserService;
  @Autowired
  IResumeService mResumeService;


  @Value("${file.uploadFolder}")
  String path;

  /**
   * 后台首页
   */
  @GetMapping("")
  public String pAdminIndex(HttpServletRequest request, Model model) {
    return "admin/index";
  }

  /**
   * 后台首页
   */
  @GetMapping("/index")
  public String pAdminIndex2(HttpServletRequest request, Model model) {
    return "admin/index";
  }

  /**
   * 后台用户登录页面
   */
  @GetMapping("/login")
  public String pAdminLogin(HttpServletRequest request, Model model) {
    return "admin/userlogin";
  }

  /**
   * 写博客页面
   */
  @GetMapping("/blogadd")
  public String pAdminBlogAdd(HttpServletRequest request, Model model) {
    return "admin/blogadd";
  }

  /**
   * 上传图片，图片存放在腾讯OSS
   * @param request
   * @param response
   * @return
   * @throws IOException
   */
  @PostMapping("/blogImage")
  @ResponseBody
  public String addBlogImage(@RequestParam(value = "data", required = false) MultipartFile file,HttpServletRequest request ,HttpServletResponse response) throws IOException {
    BASE64Encoder base64Encoder =new BASE64Encoder();

    Object fileName = file.getOriginalFilename();
    String base64 = base64Encoder.encode(file.getBytes());
    JSONObject json =new JSONObject();
    if(fileName == null || StringUtils.isEmpty(base64) || "".equals(base64)){
      json.put("error","错误");
      return json.toString();
    }

    String ImagePath = path+fileName;
    Boolean isOK = FileUtil.Base64ToImage(base64,ImagePath);


    if(isOK){
      OssUtil.init();
      Message message =OssUtil.putObject(ImagePath);
      if(message.getCode() == 1){
        json.put("imageUrl",message.getMessage());
        FileUtil.delete(new File(ImagePath));
      }
    }else{
      json.put("error","错误");
      return json.toString();
    }


    return json.toString();
  }

  /**
   * 编辑博客页面
   */
  @GetMapping("/blogmodify/{id}")
  public String pAdminBlogModify(HttpServletRequest request, Model model, @PathVariable Integer id) throws Exception {
    Article article = mBlogService.blogSelectByPrimaryKey(id);
    BlogModifyModel modifyModel = new BlogModifyModel(article);
    addModelAtt(model, VIEW_ARTICLE, modifyModel);
    return "admin/blog_modify";
  }

  /**
   * 博客管理页面
   */
  @GetMapping("/blogmanage")
  public String pAdminBlogManage(HttpServletRequest request, Model model) {
    return "admin/blog_manage";
  }

  /**
   * 后台用户登录验证
   */
  @PostMapping("/login.f")
  public String fAdminLogin(UserLoginForm userLoginForm) {
    return "redirect:/admin/blog_manage";
  }

  /**
   * 发布文章
   */
  @PostMapping("/blogadd.f")
  public String fAdminBlogAdd(BlogAddForm blogAddForm) {
    // TODO: 17-12-4 返回 json ，成功则重定向到博客列表
    mBlogService.blogAdd(blogAddForm);
    return "redirect:/admin/index";
  }

  /**
   * 修改文章
   */
  @PostMapping("blog_modify.f")
  public String fAdminBlogModify(BlogModifyForm modifyForm) {
    // TODO: 17-12-4 返回 json ，成功则重定向到博客列表
    mBlogService.blogModify(modifyForm);
    return "redirect:/admin/index";
  }


  /**
   * 获取博客详情列表
   */
  @GetMapping("/blog_list.j")
  @ResponseBody
  public Object jAdminBlogList() {
    return mBlogService.getArticleList();
  }

  /**
   * 批量删除博客
   */
  @DeleteMapping("/blog_delete.j")
  @ResponseBody
  public Object jAdminBlogDelete(@RequestBody TableKeyModel model) {
    mBlogService.blogDelete(model);
    return responseSimpleOK();
  }

  /**
   * 批量隐藏博客
   * @param blogId
   * @return
   */
  @ResponseBody
  @GetMapping("/update_is_delete")
  public String updateIsDelete(Integer blogId){
    BlogModifyForm blogModifyForm = new BlogModifyForm();
    Article article = mBlogService.blogSelectByPrimaryKey(blogId);
    JSONObject json =new JSONObject();
    if(article == null){
      json.put("error",false);
      return json.toJSONString();
    }
    blogModifyForm.setId(blogId);
    if(article.getIsDelete() == 0){
      blogModifyForm.setIsDelete(1);
    }else{
      blogModifyForm.setIsDelete(0);
    }
    mBlogService.blogModify(blogModifyForm);
    json.put("succeed",true);

      return json.toJSONString();

  }

  /**
   * 批量禁止评论
   * @param blogId
   * @return
   */
  @ResponseBody
  @GetMapping("/update_is_comment")
  public Object updateIsComment(Integer blogId){
    BlogModifyForm blogModifyForm = new BlogModifyForm();
    Article article = mBlogService.blogSelectByPrimaryKey(blogId);
    JSONObject json =new JSONObject();
    if(article == null){
      json.put("error",false);
      return json.toJSONString();
    }
    blogModifyForm.setId(blogId);
    if(article.getIsComment() == 0){
      blogModifyForm.setIsComment(1);
    }else{
      blogModifyForm.setIsComment(0);
    }
    mBlogService.blogModify(blogModifyForm);
    json.put("succeed",true);

    return json.toJSONString();

  }

  /**
   * 后台用户管理页面
   */
  @GetMapping("/admin_user_manage")
  public String pAdminUserManage() {
    return "admin/admin_user_manage";
  }

  /**
   * 后台用户管理 json
   */
  @GetMapping("/admin_user.j")
  @ResponseBody
  public Object jAdminUserList() {
    return mAdminUserService.getAdminUserJson();
  }

  /**
   * 后台用户批量删除
   */
  @DeleteMapping("/admin_use_delete.j")
  @ResponseBody
  public Object jAdminUserDelete(@RequestBody TableKeyModel model) {
    mAdminUserService.deleteAdminUser(model);
    return responseSimpleOK();
  }

  /**
   * 后台用户密码修改页面
   */
  @GetMapping("/admin_user_pwd_modify")
  public String pAdminUserPwdModify() {
    return "admin/admin_user_pwd_modify";
  }

  /**
   * 后台用户密码修改
   */
  @PostMapping("/admin_user_pwd_modify.f")
  @ResponseBody
  public Object fAdminUserPwdModify(@Valid AdminUserPwdModifyForm form, BindingResult result, HttpServletRequest request) {
    if (result.hasErrors()) {
      return responseSimpleError();
    }
    ModifyPwdResult result1;
    try {
      result1 = mAdminUserService.modifyUserPwd(form, request);
    } catch (Exception e) {
      e.printStackTrace();
      return responseSimpleError();
    }
    if (result1 == ModifyPwdResult.SUCCESS) {
      return responseSimpleOK();
    } else {
      return responseSimpleError();
    }
  }

  @GetMapping("/resume_modify")
  public String pUpdateResume(Model model) throws Exception {
    Resume article = mResumeService.getResume();
    ResumeModifyModel modifyModel = new ResumeModifyModel(article);
    addModelAtt(model, VIEW_ARTICLE, modifyModel);
    return "admin/resume_modify";
  }
}
