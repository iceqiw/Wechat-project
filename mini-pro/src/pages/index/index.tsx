import { Component, PropsWithChildren } from "react";
import { View, Button, Text } from "@tarojs/components";
import { observer, inject } from "mobx-react";

import { AtButton, AtAvatar } from "taro-ui";
import "./index.scss";

@inject("store")
@observer
class Index extends Component<PropsWithChildren> {
  componentDidMount() {}

  componentWillUnmount() {}

  componentDidShow() {}

  componentDidHide() {}

  increment = () => {
    console.log(this.props);
    const { counter } = this.props.store;
    const { c } = this.props.store;
    counter.increase();
    c.increase1();
  };

  decrement = () => {
    const { counter } = this.props.store;
    counter.decrease();
  };

  render() {
    const {
      counter: { count },
      c: { st },
    } = this.props.store;

    return (
      <View className="index">
        <AtButton type="primary" onClick={this.increment}>
          +
        </AtButton>
        <AtButton type="secondary" onClick={this.decrement}>
          -
        </AtButton>
        <Text>{count}</Text>

        <Text>{st}</Text>
      </View>
    );
  }
}

export default Index;
