package main

import (
	"fmt"
	"io"
	"os"

	"wechat/mp/domain"
	"wechat/mp/router"

	"github.com/gin-gonic/gin"
)

func main() {
	f, _ := os.Create("gin.log")
	gin.DefaultWriter = io.MultiWriter(f)
	r := router.InitRouter()
	db, err := domain.InitDB()
	if err != nil {
		fmt.Println("error db")
		return
	}
	defer db.Close()
	r.Run()
}
