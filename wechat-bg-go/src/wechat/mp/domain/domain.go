package domain

import (
	"fmt"
	"time"

	"wechat/mp/envutil"

	"github.com/gin-gonic/gin"
	"github.com/jinzhu/gorm"
	_ "github.com/jinzhu/gorm/dialects/mysql"
)

type BaseModel struct {
	ID        int `gorm:"primary_key;auto_increment"`
	CreatedAt time.Time
	UpdatedAt time.Time
}

type Event struct {
	BaseModel
	XEvent   string `json:"event"`
	XWho     string `json:"who"`
	XWhen    string `json:"when"`
	XContext string `json:"context"`
	XApp     string `json:"app"`
}

var DB *gorm.DB

func InitDB() (*gorm.DB, error) {
	var dbHOST = envutil.GetEnv("DB_HOST", "wei.service")
	var dbUSERNAME = envutil.GetEnv("DB_USERNAME", "scm")
	var dbPWD = envutil.GetEnv("DB_PWD", "Bigdata@123")
	var dbNAME = envutil.GetEnv("DB_NAME", "scm")
	dbConnString := fmt.Sprintf("%s:%s@tcp(%s:3306)/%s",
		dbUSERNAME, dbPWD, dbHOST, dbNAME)
	fmt.Println(dbConnString)
	db, err := gorm.Open("mysql", dbConnString)
	if err == nil {
		DB = db
		fmt.Fprintln(gin.DefaultWriter, "foo bar")
		db.AutoMigrate(&Event{})
		return db, err
	}
	return nil, err
}

func (event *Event) Insert() error {
	return DB.Create(event).Error
}
