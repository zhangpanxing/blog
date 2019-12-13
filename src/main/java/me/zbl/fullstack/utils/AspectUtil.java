package me.zbl.fullstack.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 切面操作工具
 *
 * @author James
 */
public class AspectUtil {

  /**
   * 获取连接点的制定类型的注解
   *
   * @param joinPoint 连接点
   * @param clazz     注解类
   *
   * @return 注解
   */
  public static Annotation getAnnotation(ProceedingJoinPoint joinPoint, Class clazz) {
    try {
      // 拦截的对象
      Object object = joinPoint.getTarget();
      Signature signature = joinPoint.getSignature();
      // 拦截方法名
      String methodName = signature.getName();
      Class[] parameterTypes = ((MethodSignature) signature).getMethod().getParameterTypes();

      try {
        // 反射目标方法
        Method method = object.getClass().getDeclaredMethod(methodName, parameterTypes);
        // 获取注解
        return method.getDeclaredAnnotation(clazz);
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      }
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    return null;
  }
}
