import { observable, action } from "mobx";
import Taro from "@tarojs/taro";

class cc2 {
  @observable st = 0;

  increase1() {
    Taro.request({
      url: "/api/v1", //仅为示例，并非真实的接口地址
      dataType: "text",
    }).then((res) => {
      console.log(res);
    });
  }

  decrease1() {
    this.st -= 10;
  }
}

export default cc2;
