package conn

import (
	"fmt"
	"log"
	"sync"
	"travelBD/config"
	"travelBD/models"

	"github.com/jinzhu/gorm"
	//mssql
	_ "github.com/jinzhu/gorm/dialects/mssql"
)

const dbtype = "mssql"

type DB struct {
	*gorm.DB
}

func NewDB() *DB {
	return &DB{}
}

var dbInstance *DB
var connDBOnce sync.Once

func ConnectDB(config *config.DBConfig) *DB {
	connDBOnce.Do(func() {
		_ = connectDB(config)
	})
	return dbInstance
}

func connectDB(config *config.DBConfig) error {
	connString := fmt.Sprintf("server=%s; port=%d; database=%s;", config.Server, config.Port, config.DbName)
	conn, err := gorm.Open(dbtype, connString)
	if err != nil {
		log.Fatal("Open connection failed : ", err.Error())
		panic(err)
	}
	fmt.Println("Database connected succesfully")
	dbInstance = &DB{conn}
	return nil
}

func (db *DB) Migration() {
	db.AutoMigrate(&models.UserDomain{}, &models.Place{})
}
