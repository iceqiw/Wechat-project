export default defineAppConfig({
  pages: ["pages/index/index", "pages/home/index"],
  tabBar: {
    list: [
      {
        pagePath: "pages/index/index",
        text: "最新",
      },
      {
        pagePath: "pages/home/index",
        text: "热门",
      },
    ],
    color: "#000",
    selectedColor: "#56abe4",
    backgroundColor: "#fff",
    borderStyle: "white",
  },
  window: {
    backgroundTextStyle: "light",
    navigationBarBackgroundColor: "#fff",
    navigationBarTitleText: "V2EX",
    navigationBarTextStyle: "black",
  },
});
