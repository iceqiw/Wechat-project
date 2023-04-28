import { makeObservable, observable, action } from "mobx";

class counterStore {
  @observable count = 0;

  @action
  increase() {
    this.count += 1;
  }

  @action
  decrease() {
    this.count -= 1;
  }
}

export default counterStore;
