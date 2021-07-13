package services

import (
	"fmt"
	"travelBD/auth"
	"travelBD/models"
	"travelBD/models/dtos"
	"travelBD/repositories"
)

type IAuthService interface {
	Login(loginDto *dtos.LoginDto) (*dtos.AccessTokenDto, error)
}

type AuthService struct {
	auth           auth.IAuth
	userRepository repositories.IUserRepository
}

func NewAuthService(
	auth auth.IAuth,
	userRepository repositories.IUserRepository,
) IAuthService {
	return &AuthService{
		auth:           auth,
		userRepository: userRepository,
	}
}

func (that *AuthService) Login(loginDto *dtos.LoginDto) (*dtos.AccessTokenDto, error) {
	user, err := that.userRepository.GetByFilter("email=?", loginDto.Email)
	if err != nil {
		return nil, err
	}
	fmt.Println(user)
	token, err := that.auth.GenerateToken(&models.UserDomain{ID: user.ID})
	if err != nil {
		fmt.Println(err)
		return nil, err
	}
	return &dtos.AccessTokenDto{Bearer: token}, nil
}
