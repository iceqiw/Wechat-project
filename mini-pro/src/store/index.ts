import counterStore from "./counter";

export interface Stores {
  counter: counterStore;
}

const stores: Stores = {
  counter: new counterStore(),
};

export default stores;
