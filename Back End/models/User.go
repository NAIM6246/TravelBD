package models

type UserDomain struct {
	ID       int
	Name     string
	Password string
}

func UserTableName() string {
	return "user_domains"
}
