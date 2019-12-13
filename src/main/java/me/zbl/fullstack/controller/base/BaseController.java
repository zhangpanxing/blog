package me.zbl.fullstack.controller.base;

import me.zbl.fullstack.entity.dto.response.factory.GeneralJsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器基类
 *
 * @author James
 */
public class BaseController {

  @Autowired
  protected GeneralJsonFactory mJsonFactory;

  /**
   * Controller 中方法异常处理器
   *
   * @param e 异常
   *
   * @return 视图名
   */
  @ExceptionHandler
  public String handleException(HttpServletRequest request, Exception e) {
    return "/error";
  }

  /**
   * 给页面添加标题
   */
  public void setPageTitle(Model model, String title) {
    model.addAttribute("title", title);
  }

  /**
   * 根据名称获取 Session 中的属性值
   */
  public Object getSessionAttr(HttpServletRequest request, String attrName) throws NullPointerException {
    return request.getSession().getAttribute(attrName);
  }

  /**
   * 向 Model 添加数据
   */
  public void addModelAtt(Model model, String attribute, Object value) throws Exception {
    if (null != value) {
      model.addAttribute(attribute, value);
    } else {
      throw new Exception();
    }
  }

  /**
   * 向 Session 中添加对象
   */
  public void addSessionAtrr(HttpServletRequest request, String attribute, Object value) {
    request.getSession().setAttribute(attribute, value);
  }

  /**
   * 返回 成功 json
   */
  public Object responseSimpleOK() {
    return mJsonFactory.createSimpleResponse();
  }

  /**
   * 返回 失败 json
   */
  public Object responseSimpleError() {
    return mJsonFactory.createtSimpleErrorResponse();
  }
}
