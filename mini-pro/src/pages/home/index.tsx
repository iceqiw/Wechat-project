import { Component, PropsWithChildren } from "react";
import { View } from "@tarojs/components";

import "./index.scss";

class Home extends Component<PropsWithChildren> {
  componentDidMount() {}

  componentWillUnmount() {}

  componentDidShow() {}

  componentDidHide() {}

  render() {
    return <View className="home">hello world!!</View>;
  }
}

export default Home;
