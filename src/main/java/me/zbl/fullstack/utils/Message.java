package me.zbl.fullstack.utils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import me.zbl.fullstack.consts.MessageConsts;

import java.io.Serializable;



@SuppressWarnings("serial")
public class Message implements Serializable {

    /** 返回消息代码 */
    @JsonProperty
    private Integer code;

    /** 消息内容*/
    @JsonProperty
    private String message;

    /** 消息具体描述 */
    @JsonIgnore
    private String description;

    /** 返回结果对象*/
    @JsonProperty
    private Object entity;

    public Message(){
    }

    /**
     * 初始化一个新创建的 Message 对象
     * @param msgCode
     * @param message
     * @param description
     */
    public Message(MessageConsts msgCode, String message, String description){
        this.code = msgCode.value();
        this.message = message;
        this.description = description;
    }
    public Message(MessageConsts msgCode, String message, String description,Object entity){
        this.code = msgCode.value();
        this.message = message;
        this.description = description;
        this.entity =  entity;
    }

    /**
     * 返回成功消息
     * @param message 内容
     * @return 成功消息
     */
    public static Message success(String message) {
        return new Message(MessageConsts.OK, message, message);
    }

    /**
     * 返回成功消息
     * @param obj
     * @return
     */
    public static Message success(Object obj) {
        if(obj instanceof String){

            return new Message(MessageConsts.OK, (String)obj, (String)obj);
        }else{
            return new Message(MessageConsts.OK, "操作成功.", "操作成功.",obj);
        }
    }

    public static Message success(){
        return new Message(MessageConsts.OK, "操作成功", "操作成功");
    }
    public static Message get(boolean result){
        if(result){
            return Message.success("操作成功.");
        }else{
            return Message.error("操作失败.");
        }
    }

    /**
     * 返回成功消息
     * @param message 内容
     * @param description 具体详情
     * @return 成功消息
     */
    public static Message success(String message, String description,Object obj) {
        return new Message(MessageConsts.OK, message, description,obj);
    }

    /**
     * 返回成功消息
     * @param message 内容
     * @param  具体详情
     * @return 成功消息
     */
    public static Message success(String message,Object obj) {
        return new Message(MessageConsts.OK, message, message,obj);
    }

    /**
     * 返回对象不存在消息
     * @param message 内容
     * @return 成功消息
     */
    public static Message notExist(String message) {
        return new Message(MessageConsts.NOT_EXIST, message, message);
    }

    /**
     * 返回对象不存在消息
     * @param message 内容
     * @param description 具体详情
     * @return 成功消息
     */
    public static Message notExist(String message, String description) {
        return new Message(MessageConsts.NOT_EXIST, message, description);
    }

    /**
     * 返回错误消息
     * @param
     * @param message
     * @return 错误消息
     */
    public static Message error(String message){
        return new Message(MessageConsts.ERROR_UNKNOW, message, message);
    }

    /**
     * 返回错误消息
     * @param
     * @param message
     * @param
     * @return 错误消息
     */
    public static Message error(MessageConsts msgCode, String message){
        return new Message(msgCode, message, message);
    }

    /**
     * 返回错误消息
     * @param
     * @param message
     * @param description
     * @return 错误消息
     */
    public static Message error(String message, String description){
        return new Message(MessageConsts.ERROR_UNKNOW, message, description);
    }

    /**
     * 返回错误消息
     * @param msgCode 错误消息编码
     * @param message
     * @param description
     * @return 错误消息
     */
    public static Message error(MessageConsts msgCode,String message, String description){
        return new Message(msgCode, message, description);
    }

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Message{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", description='" + description + '\'' +
                ", entity=" + entity +
                '}';
    }
}
