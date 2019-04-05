package v1

import (
	"bufio"
	"encoding/json"
	"fmt"
	"github.com/gin-gonic/gin"
	"io"
	"net/http"
	"os"
	"wechat/mp/domain"
)

func RedcordEvent(c *gin.Context) {
	var event domain.Event
	err := c.BindJSON(&event)
	if err != nil {
		c.JSON(http.StatusOK, gin.H{"errcode": 400, "description": "Post Data Err"})
		return
	}
	go func() {
		errDb := event.Insert()
		println(errDb)
	}()

	c.JSON(http.StatusOK, gin.H{"description": "OK"})
}

func RedcordEvent2(c *gin.Context) {
	data, err := ReadLine("/data/2019-01-19/zg.json")
	if err != nil {
		println(err.Error)
	}
	c.JSON(http.StatusOK, gin.H{"description": data})
}

type Config struct {
	Name string `json:name`
	Date string `json:date`
}

func ReadLine(fileName string) (cf []Config, err2 error) {
	f, err := os.Open(fileName)
	if err != nil {
		err2 = err
		return
	}
	defer f.Close()
	buf := bufio.NewReader(f)
	for {
		line, err := buf.ReadString('\n')
		if err != nil || io.EOF == err {
			fmt.Println(err)
			break
		}
		cs := Config{}
		err = json.Unmarshal([]byte(line), &cs)
		fmt.Println(cs)
		cf = append(cf, cs)
	}
	return

}
