import counterStore from "./counter";
import cc2 from "./cc2";

export interface Stores {
  counter: counterStore;
  c: cc2;
}

const stores: Stores = {
  counter: new counterStore(),
  c: new cc2(),
};

export default stores;
