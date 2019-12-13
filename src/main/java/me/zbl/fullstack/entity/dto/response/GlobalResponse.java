package me.zbl.fullstack.entity.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * 统一 Json 范围实体类
 *
 * @author James
 */
@Getter
@Setter
public class GlobalResponse {

  protected int resultCode;
  protected boolean hasError;

  private GlobalResponse() {

  }

  public GlobalResponse(int resultCode, boolean hasError) {
    this.resultCode = resultCode;
    this.hasError = hasError;
  }
}
