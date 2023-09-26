import { View } from "@tarojs/components";
import Taro, { useLoad } from "@tarojs/taro";
import "./index.scss";
import CustomHeader from "@/components/header";
import { AtGrid } from "taro-ui";

export default function Index() {
  useLoad(() => {
    console.log("Page loaded.");
  });
  const handleGridItemClick = (item, index) => {
    console.log(`Clicked on item ${index + 1}: ${item.link}`);
    // Add your custom logic here
    Taro.redirectTo({ url: item.link });
  };
  return (
    <View className="index">
      <CustomHeader title="Home" />
      <AtGrid
        onClick={handleGridItemClick}
        mode="rect"
        columnNum={2}
        data={[
          {
            image:
              "https://img12.360buyimg.com/jdphoto/s72x72_jfs/t6160/14/2008729947/2754/7d512a86/595c3aeeNa89ddf71.png",
            value: "提词器",
            link: "/pages/scroll/scroll",
          },
          {
            image:
              "https://img20.360buyimg.com/jdphoto/s72x72_jfs/t15151/308/1012305375/2300/536ee6ef/5a411466N040a074b.png",
            value: "首页",
            link: "/pages/index/index",
          },
        ]}
      />
    </View>
  );
}
