package models

type UserDomain struct {
	ID       int
	Name     string
	UserName string
	Email    string
	Password string
}

func UserTableName() string {
	return "user_domains"
}
