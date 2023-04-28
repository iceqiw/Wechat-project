import { observable, action } from "mobx";

class counterStore {
  @observable count = 0;

  increase() {
    this.count += 1;
  }

  decrease() {
    this.count -= 1;
  }
}

export default counterStore;
