package me.zbl.fullstack.consts;

public enum MessageConsts {

    /** 成功消息 */
    OK(1),

    /** 对象不存在消息 */
    NOT_EXIST(19998),

    /** 失败消息 未知原因 */
    ERROR_UNKNOW(9999),

    /** 商品上架的门店不匹配 */
    ERROR_TAGS_NOT_MATCH_STORE(11001),

    /** paycode未提交 */
    ERROR_PAY_CODE_NOT_COMMIT(19980),

    /** paycode未设置 */
    ERROR_PAY_CODE_NOT_SET(19981),

    /** paycode 不一致 */
    ERROR_PAY_CODE_NOT_EQUAL(19982),

    /** 盘点操作，盘点任务ID为空*/
    ERROR_INVENTORY_ID_IS_NULL(18000),

    /** 盘点操作，上传商品数量为空*/
    ERROR_INVENTORY_NO_PRODUCT(18001),

    /** 订单已经完成，请勿重复操作 */
    ERROR_ORDER_COMPLETED(30001),

    /** 无效的用户ID */
    ERROR_INVALID_PERSON_ID(17001),

    /** 该用户不存在 */
    ERROR_EMPTY_PERSON(17002),

    /** 门店编码不能为空  */
    ERROR_INVALID_STORE_SN(17003),

    /** 该门店不存在 */
    ERROR_EMPTY_STORE(17004),

    /** 解析标签信息出错 */
    ERROR_TAGS(17005),

    /** 未检测到商品，或商品都已经支付 */
    ERROR_DETECTED_PAYED_TAGS(17006),

    /** 无效的支付码 */
    ERROR_INVALID_PAY_CODE(17007),

    /** 未绑定手机 */
    ERROR_INVALID_MOBILE(17008),

    /** 余额不足，请先充值 */
    ERROR_INSUFFICIENT_BALANCE(17009),

    /** 余额支付失败 */
    ERROR_FACE_PAY(17010);

    private Integer num;

   private MessageConsts(Integer num){
       this.num = num;
   }

    public Integer value(){
        return this.num;
    }

}
