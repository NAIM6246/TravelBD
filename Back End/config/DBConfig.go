package config

import "sync"

type DBConfig struct {
	Server   string
	Port     int
	User     string
	Password string
	DbName   string
}

var dbConfig *DBConfig

func mapDBConfig() {
	dbConfig = &DBConfig{
		Server:   "localhost",
		Port:     1433,
		User:     "",
		Password: "",
		DbName:   "TravelBD",
	}
}

func NewDBConfig() *DBConfig {
	var loadDBOnce sync.Once
	loadDBOnce.Do(mapDBConfig)
	return dbConfig
}
