package router

import (
	v1 "wechat/mp/controller/v1"

	"github.com/gin-gonic/gin"
)

func InitRouter() *gin.Engine {
	r := gin.New()
	r.Use(gin.Logger())
	r.Use(gin.Recovery())
	apiv1 := r.Group("/api/v1")
	{
		apiv1.POST("/event", v1.RedcordEvent)
		apiv1.GET("/test", v1.RedcordEvent2)
	}

	return r
}
