# Required 
## golang

brew install golang

## glide

go get github.com/Masterminds/glide


# Structure 

- Makefile 构建文件
- Dockerfile
- docker-compose
- src 程序目录
  - vendor 依赖文件
  - daimler.com/collect  业务代码
    - controller
    - domain
    - envutil 环境变量读取工具
    - router 路由
    - app.go 主入口程序
  - glide.yaml 依赖包

# install 下载依赖

```
make deps
```

删除docker 镜像

```
docker rmi $(docker images -f "dangling=true" -q)
```

# build 构建应用

```
make build
```

# run 运行

```
make run
```

# environment 环境变量

```
  DB_HOST: postgres  #数据库地址
  DB_USERNAME: postgres #数据库用户
  DB_PWD: 111111 #数据库密码
  DB_NAME: data_service #数据名称
```

# 默认端口 8080 

url：http://127.0.0.1:8080/api/v1/event

# 测试用例

```
curl -X POST \
  http://127.0.0.1:8080/api/v1/event \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{
	"event":"test",
	"who":"test",
	"when":"123123123",
	"context":"{\"test\":\"123\"}",
	"app":"sss"
}'

```