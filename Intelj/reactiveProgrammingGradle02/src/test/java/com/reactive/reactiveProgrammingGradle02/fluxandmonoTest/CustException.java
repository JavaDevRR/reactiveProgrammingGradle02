package com.reactive.reactiveProgrammingGradle02.fluxandmonoTest;

public class CustException {

  private String msg;

  public CustException(Throwable e){

      this.msg= e.getMessage();
  }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
