package dtos

type LoginDto struct {
	Email    string `json:"email"`
	Password string `json:"password"`
}

type UserDto struct {
	Name     string `json:"name"`
	UserName string `json:"user_name"`
	Email    string `json:"email"`
}

type LoginResponseDto struct {
	Bearer string  `json:"token"`
	User   UserDto `json:"user"`
}
