import React from "react";
import Taro from "@tarojs/taro";
import { View} from "@tarojs/components";
import { AtNavBar} from "taro-ui";

function CustomHeader({ title }) {


  const handleGoToHome = () => {
    Taro.redirectTo({ url: "pages/index/index" });
  };

  return (
    <View className="custom-header">
      <AtNavBar
        title={title}
        rightFirstIconType="home"
        onClickRgIconSt={handleGoToHome} // Use the same function for both buttons
      />
    </View>
  );
}

export default CustomHeader;
