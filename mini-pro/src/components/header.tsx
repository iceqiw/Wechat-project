import React from "react";
import Taro from "@tarojs/taro";
import { View, Text } from "@tarojs/components";

import  "./header.scss"
function CustomHeader({ title }) {
    const handleGoBack = () => {
        Taro.navigateBack();
    };

    const handleGoToHome = () => {
        Taro.redirectTo({ url: "pages/index/index" });
    };

    return (
        <View className="custom-header">
            <View className="header-left" onClick={handleGoBack}>
                <Text>&lt; Back</Text>
            </View>
            <View className="header-title">{title}</View>
            <View className="header-right" onClick={handleGoToHome}>
                <Text>Home</Text>
            </View>
        </View>
    );
}

export default CustomHeader;
